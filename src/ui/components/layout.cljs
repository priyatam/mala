(ns ui.components.layout
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [om.core :as om :include-macros true]
            [cljs.core.async :refer [<! chan put! sliding-buffer]]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [garden.core :refer [css]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [px pt em]]
            [garden.color :as color :refer [hsl rgb]]
            [mesh.dom :as mesh-dom :refer [insert-styles]]
            [mesh.utils :as utils]
            [mesh.images :as images]
            [mesh.respond :as respond :refer [breakpoints]]
            [mesh.grid :as grid]
            [mesh.typography :as typo :refer [typeset]]
            [ui.state :as state]
            [ui.utils :refer [host]]
            [ui.components.page-styles :as styles]))

(defn undo-button []
  (dom/div nil
    (dom/button #js {:onClick #(state/undo!)}
      "Undo")))

(defn create-cells [{:keys [rows columns] :as content}]
  (let [cell (fn [idx] [:div {:class "unit one-fifth"}
                       [:p (nth columns idx)]])
        row-of-cells [:div {:class "grid"}
                      (cell 0) (cell 1) (cell 2) (cell 3) (cell 4)]]
    [:div
     [:h2 {:class "grid-title"} "A simple layout"]
     row-of-cells
     row-of-cells
     row-of-cells
     row-of-cells
     row-of-cells
     [:div {:class "unit golden-large"}
      (undo-button)]]))

(defn- image [id]
  [:img {:src (str "/img/" id)}] )

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
        (create-cells (:layout data))]))
    om/IWillUnmount
    (will-unmount [_]
      ;; remove styles
      )))
