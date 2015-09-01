(ns ui.components.page
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [om.core :as om :include-macros true]
            [cljs.core.async :refer [<! chan put! sliding-buffer]]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [garden.core :refer [css]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [gardener.dom :refer [insert-styles]]
            [ui.state :as state]
            [ui.utils :refer [host]]
            [ui.components.page-styles :as styles]))

(defn undo-button []
  (dom/div nil
    (dom/button #js {:onClick #(state/undo!)}
      "Undo")))

(defn- image [id]
  [:img {:src (str "/img/" id)}] )

(defn layout [{:keys [title issue publisher logo developer-pic
                      interviews essays quotes]
               :as content}]
  [:section
   [:div {:class "grid"}
    [:div {:class "unit whole align-center"}
     [:h1 publisher]]]
   [:div {:class "grid"}
    [:div {:class "unit golden-small"}
     [:h3 issue]]
    [:div {:class "unit golden-large"}
     [:div [:img {:src logo}]]]]
   [:div {:class "grid"}
    [:div {:class "unit half"}
     [:h4 title]
     [:p (:editorial content)]]
    [:div {:class "unit half"}
     [:h4 "Interviews"]
     [:p (map str interviews)]
     [:h5 "Essays"]
     [:p (map str essays)]]]
   [:div {:class "grid"}
    [:div {:class "unit golden-small"} ]
    [:div {:class "unit golden-large" }
     [:blockquote.oval-thought-border
      [:p (get-in content [:quotes :dev-quote])]]]]
   [:div {:class "grid"}
    [:div {:class "unit golden-small photo-medium"} [:img {:src developer-pic}]]
    [:div {:class "unit golden-large"}
     (undo-button)]]])

(defn view [data owner]
  (reify
    om/IInitState
    (init-state [_]
      {:chans {:event-chan (chan)}
       :title "I am not a Hacker"})
    om/IWillMount
    (will-mount [_]
      (println "widget mounting")
      (insert-styles styles/index))
    om/IRenderState
    (render-state [cursor {:keys [chans title]}]
      (println "Render!")
      (html
       [:section {:class "demo"}
        (layout (:content data))]))
    om/IWillUnmount
    (will-unmount [_]
      ;; remove styles
      )))
