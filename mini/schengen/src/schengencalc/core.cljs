(ns schengencalc.core
  (:require
   [om.core :as om :include-macros true]
   [om.dom :as dom :include-macros true]
   [kioo.om :as kioo :include-macros true]))

;; loads from localStorage on page load, save on re-render

(defn store [k obj]
  (.setItem js/localStorage k (js/JSON.stringify (clj->js obj))))

(defn keywordify [m]
  (cond
    (map? m) (into {} (for [[k v] m] [(keyword k) (keywordify v)]))
    (coll? m) (vec (map keywordify m))
    :else m))

(defn ->prn [x]
  (js/console.log x)
  x)

(defn fetch [k default]
  (let [item (.getItem js/localStorage k)]
    (if item
      (-> (.getItem js/localStorage k)
          (or (js-obj))
          (js/JSON.parse)
          (js->clj)
          (keywordify))
      default)))

(defn fmt-date [d]
  (.format (js/moment d) "MMMM Do, YYYY"))

(defn fmt-date-iso [d]
  (.format (js/moment d) "YYYY-MM-DD"))

;; Date Calculations

(defn duration [{exit :exit entry :entry :as in}]
  (-> (js/moment exit)
      (.diff (js/moment entry))
      (js/moment.duration)
      (.asDays)
      (js/Math.round)
      (inc)))

(defn days-used [deadline upcoming-stays]
  (reduce +
          0 (for [{:keys [entry exit] :as upcoming} upcoming-stays]
              (cond
                (.isBefore deadline entry) 0
                (.isBefore deadline exit) (duration {:entry entry :exit deadline})
                :otherwise (duration upcoming)))))

(defn days-left [deadline upcoming-stays]
  (- 90 (days-used deadline upcoming-stays)))

(defn calc-days-left-and-deadline [upcoming-stays date]
  (let [deadline (.add (js/moment date) "days" 180)]
    {:from-date date
     :days-left (days-left deadline upcoming-stays)
     :return-date deadline}))

(defn update-re-dates [upcoming-stays {exit :exit :as stay}]
  (conj upcoming-stays
        (merge stay (calc-days-left-and-deadline upcoming-stays exit))))

(defn re-entry-dates [travel-dates]
  (let [initial (calc-days-left-and-deadline
                 travel-dates
                 (:entry (first travel-dates)))
        other-dates (filter #(not (= (:days-left %) 90))
                            (reduce update-re-dates [] (reverse travel-dates)))]
    (cond
      (empty? travel-dates) []
      (empty? other-dates) [initial]
      :otherwise (apply conj [initial] (reverse other-dates)))))

;; Components

(defn date-input [stay-key]
  (fn [stay owner]
    (reify
      om/IDidMount
      (did-mount [_]
        (->> (om/get-node owner)
             (js-obj "onSelect" #(this-as t (om/update! stay stay-key (.getMoment t)))
                     "format" "YYYY-MM-DD"
                     "minDate" (js/Date. 2009 0 1)
                     "field")
             (js/Pikaday.)
             ;;(.glDatePicker)
             ))
      om/IRender
      (render [_]
        (dom/input #js {:value (.format (js/moment (get stay stay-key)) "YYYY-MM-DD")})))))

;; Templates

(kioo/defsnippet date-row "index.html" [:.date-row]
  [{:keys [entry exit] :as rowdata} travel-dates]
  {[:.duration] (kioo/content (str (duration rowdata) " days"))
   [:input.entry] (kioo/substitute (om/build (date-input :entry) rowdata))
   [:input.exit] (kioo/substitute (om/build (date-input :exit) rowdata))
   [:a] (kioo/listen :on-click
                     (fn [e]
                       (.preventDefault e)
                       (om/transact! travel-dates
                                     #(vec (filter (fn [x]
                                                     (and (not (= (:entry x) entry))
                                                          (not (= (:exit x) exit))))
                                                   %)))))})

(kioo/defsnippet result-item "index.html" [:li.stay-tpl]
  [{:keys [days-left return-date from-date]}]
  {[:.return-date] (kioo/content (fmt-date return-date))
   [:.days-left] (kioo/content days-left)
   [:.deadline] (kioo/content (fmt-date from-date))
   })

(kioo/deftemplate main "index.html"
  [{travel-dates :travel-dates}]
  {[:tbody.travel-dates] (kioo/content (map #(date-row % travel-dates) travel-dates))
   [:.add-stay :a] (kioo/listen
                    :on-click
                    (fn [e]
                      (.preventDefault e)
                      (om/transact! travel-dates #(conj % {:entry (js/moment) :exit (js/moment)}))))
   [:ul.results] (kioo/content (map result-item (re-entry-dates travel-dates)))
   [:.disclaimer] (if (every? #(>= (:days-left %) 0) (re-entry-dates travel-dates))
                    (kioo/do->
                     (kioo/set-class "disclaimer ok")
                     (kioo/content "This schedule is ok!"))
                    (kioo/do->
                     (kioo/set-class "disclaimer warn")
                     (kioo/content "This schedule violates Schengen Visa rules!")))})

(def app-state
  (atom (fetch "schengencalc" {:travel-dates []})))

(defn app [data owner]
  (store "schengencalc" data) ; Persist data
  (om/component (main data)))

(om/root app app-state
         {:target (.-body js/document)})
