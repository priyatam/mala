(ns {{name}}.ui.main
  (:require [om.core :as om]
            [om.dom :as dom]
            [sablono.core :as html :refer-macros [html]]
            [{{name}}.ui.state :as state :refer [app-state]]
            [{{name}}.ui.components.hello :as hello]
            [{{name}}.ui.components.typeahead :as typeahead]
            [{{name}}.ui.router :as router]))

(router/routes)
(router/dispatch-current-route)

(router/mount "hello" hello/component)
(router/mount "typeahead" typeahead/component)
