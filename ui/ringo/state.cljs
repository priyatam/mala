(ns ringo.state
  (:require [om.core :as om]
            [om.dom :as dom]
            [sablono.core :as html :refer-macros [html]]))

(defonce app-state (atom {:text "Hello Ringo!"
                          :devices {:all []}
                          :chart {:data []}}))
