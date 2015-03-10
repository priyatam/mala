(ns ui.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType][om.core :as om]
            [om.dom :as dom]
            [secretary.core :as sec :include-macros true]
            [ui.state :as state :refer [app-state]]
            [ui.pages :as pages]
            [ui.utils :as utils])
  (:import goog.History))

;; (sec/set-config! :prefix "#")

(def host "http://localhost:3449/")

(let [history (History.)
      navigation EventType/NAVIGATE]
  (events/listen history
                 navigation
                 #(-> % .-token sec/dispatch!))
  (doto history (.setEnabled true)))

(defn mount [id page]
  (om/root
   page
   app-state
   {:target (.getElementById js/document id)
    :shared {:url host}
    :instrument (utils/with-omi)}))

(defn dispatch-current-route []
  (sec/dispatch! (.-pathname (.-location js/window))))

(defn routes []
  (sec/defroute about "/about" []
    (mount "graph" pages/about)))
