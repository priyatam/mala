(ns ringo.components
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [om.core :as om  :include-macros true]
            [cljs.core.async :refer [<! chan put! sliding-buffer]]
            [sablono.core :as html :refer-macros [html]]
            [ringo.client :as client :refer [GET]]))

(enable-console-print!)

;; STATE ;;

(def app-model
  (atom {:devices {:all []}
         :chart {:data []}}))

;; UTILS ;;

(defn get-measurements [cursor owner message]
  (let [host (:url (om/get-shared owner))
        {:keys [id type]} message
        url (str host "api/device/" id "/type/" type "/measurements")]
    (GET url #(om/update! cursor [:data] %))))

(defn- draw-chart [cursor {:keys [div bounds x-axis y-axis plot]}]
  (let [{:keys [id width height]} div
        Chart        (.-chart js/dimple)
        svg          (.newSvg js/dimple (str "#" id) width height)
        data         (get-in cursor [:data])
        dimple-chart (.setBounds (Chart. svg) (:x bounds) (:y bounds)
                                 (:width bounds)
                                 (:height bounds))
        x            (.addCategoryAxis dimple-chart "x" x-axis)
        y            (.addMeasureAxis dimple-chart "y" y-axis)
        s            (.addSeries dimple-chart nil plot (clj->js [x y]))]
    (aset s "data" (clj->js data))
    (.addLegend dimple-chart "5%" "10%" "20%" "10%" "right")
    (.draw dimple-chart)
    (.attr (.selectAll (.-shapes x) "text")
           "transform" "rotate(45,0,12.6015625) translate(5, 0)")))

;; COMPONENTS ;;

(defn chart-figure [cursor owner {:keys [chart] :as opts}]
  (reify
    om/IWillMount
    (will-mount [_]
      (let [event-chan (om/get-state owner [:event-chan])
            event-fn   (:event-fn opts)]
        (go (while true
              (let [v (<! event-chan)]
                (event-fn cursor owner v))))))
    om/IRender
    (render [_]
      (let [{:keys [id width height]} (:div chart)]
        (html
         [:div {:id id :width width :height height}])))
    om/IDidUpdate
    (did-update [_ _ _]
      (let [n (.getElementById js/document "chart")]
        (while (.hasChildNodes n)
          (.removeChild n (.-lastChild n))))
      (when (seq (:data cursor))
        (draw-chart cursor chart)))))

;; Form containing list of devices that can be plotted

(defn form-row [event-chan]
  (fn [the-item owner]
    (om/component
      (let [{:keys [id type description unit]} the-item]
        (html
         [:tr
          [:td [:input {:type "radio"
                        :name "type"
                        :value name
                        :on-change (fn [e]
                                     (put! event-chan {:id id
                                                       :type type}))}]]
          [:td id]
          [:td type]
          [:td description]
          [:td unit]])))))

(defn device-form
  [cursor owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (let [host (:url (om/get-shared owner))
            url (str host "api/devices")]
        (GET url #(om/update! cursor [:all] %))))
    om/IRenderState
    (render-state [_ {:keys [event-chan]}]
      (let [devices (:all cursor)]
        (html
         [:div
          [:table {:class "table table-striped table-bordered table-condensed"
                   :style {:width "100%"}}
           [:thead [:tr
                    [:th "Select"]
                    [:th "ID"]
                    [:th "Type"]
                    [:th "Description"]
                    [:th "Unit"]]]
           [:tbody (om/build-all (form-row event-chan) devices {:key :id})]]])))))

;; Entire application view

(defn chart-http [cursor owner]
  (reify
    om/IInitState
    (init-state [_]
      {:chans {:event-chan (chan)}})

    om/IRenderState
    (render-state [_ {:keys [chans]}]
      (html
       [:div {:class "container"}
        [:h3 {:key "head"} (str "Mastering Clojure & Clojurescript")]
        ;; Builds table with form components for selecting devices
        (om/build device-form (:devices cursor)
                  {:init-state chans})
        ;; Builds chart component
        [:div {:class "well" :style {:width "100%" :height 600}}
         (om/build chart-figure
                   (:chart cursor)
                   {:init-state chans
                    :opts {:event-fn get-measurements
                           :chart {:div {:id "chart" :width "100%" :height 600}
                                   :bounds {:x "5%" :y "15%" :width "80%" :height "50%"}
                                   :x-axis "timestamp"
                                   :y-axis "value"
                                   :plot js/dimple.plot.line}}})]]))))

(defn widget [data]
  (om/component
    (html
     [:div [:h2 "Hello Ringo!"]])))

;; ROOT BINDING ;;

(om/root widget {}
         {:target (.getElementById js/document "hello")})

(om/root chart-http app-model
         {:target (.getElementById js/document "app")
          :shared {:url client/host}})
