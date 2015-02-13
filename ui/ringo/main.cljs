(ns ringo.main
  (:require [om.core :as om]
            [om.dom :as dom]
            [sablono.core :as html :refer-macros [html]]
            [ringo.state :as state :refer [app-state]]
            [ringo.components :as components]
            [ringo.router :as router]))

(router/routes)
(router/dispatch-current-route)
(router/mount "app" components/d3-chart)
