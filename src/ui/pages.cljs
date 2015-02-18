(ns ui.pages
  (:require [om.core :as om]
            [om.dom :as dom]
            [sablono.core :as html :refer-macros [html]]))

(defn about [_ _]
  (om/component
    (html
     [:div
      [:h2 "About Page"]])))
