(ns ringo.router
  (:require [secretary.core :as sec :include-macros true]
            [om.core :as om]
            [om.dom :as dom]
            [sablono.core :as html :refer-macros [html]]
            [ringo.state :as state]
            [ringo.pages :as pages]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))

;; (sec/set-config! :prefix "#")

(def host "http://localhost:3449/" )

(let [history (History.)
      navigation EventType/NAVIGATE]
  (events/listen history
                 navigation
                 #(-> % .-token sec/dispatch!))
  (doto history (.setEnabled true)))

(defn mount [id page]
  (om/root
   page
   state/app-state
   {:target (.getElementById js/document id)
    :shared {:url host}}))

(defn dispatch-current-route []
  (sec/dispatch! (.-pathname (.-location js/window))))

(defn routes []
  (sec/defroute about "/about" []
    (mount "app" pages/about)))
