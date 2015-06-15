(ns {{name}}.core
  (:require [om.core :as om]
            [om.dom :as dom]
            [om-i.core :as omi]
            [om-i.hacks :as omi-hacks]
            [sablono.core :as html :refer-macros [html]]
            [{{name}}.ui.state :as state :refer [app-state]]
            [{{name}}.ui.components.typeahead :as typeahead]
            [dev.debug :as debug]
            [{{name}}.ui.router :as router]))

(enable-console-print!)

(router/enable-history)
(router/dispatch-current-route)

(router/mount "debugger" debug/inspector)

(omi/setup-component-stats!)
(omi-hacks/insert-styles)

(defn main []
  (-> js/document .-location (set! "#/")))
