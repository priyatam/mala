(ns ringo.main
  (:require [om.core :as om]
            [om.dom :as dom]
            [sablono.core :as html :refer-macros [html]]
            [ringo.state :as state :refer [app-state]]
            [ringo.components :as components]
            [ringo.client :as client]))

(om/root
 components/d3-chart
 app-state
 {:target (.getElementById js/document "app")
  :shared {:url client/host}})
