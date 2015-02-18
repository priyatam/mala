(ns ringo.main
  (:require [om.core :as om]
            [om.dom :as dom]
            [sablono.core :as html :refer-macros [html]]
            [ringo.state :as state :refer [app-state]]
            [ringo.components.graph :as graph]
            [ringo.components.typeahead :as typeahead]
            [ringo.router :as router]))

(router/routes)
(router/dispatch-current-route)

;;(router/mount "graph" graph/view)
(router/mount "typeahead" typeahead/view)
