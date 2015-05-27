(ns core
  (:require [om.core :as om]
            [om.dom :as dom]
            [om-i.core :as omi]
            [om-i.hacks :as omi-hacks]
            [sablono.core :as html :refer-macros [html]]
            [ui.state :as state :refer [app-state]]
            [ui.components.graph :as graph]
            [ui.components.typeahead :as typeahead]
            [ui.router :as router]))

(enable-console-print!)

(router/enable-history)
(router/dispatch-current-route)

(omi/setup-component-stats!)
(omi-hacks/insert-styles)

(defn main []
  (-> js/document .-location (set! "#/")))
