(ns ui.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType][om.core :as om]
            [om.dom :as dom]
            [secretary.core :as sec :refer-macros [defroute]]
            [ui.state :as state :refer [app-state]]
            [ui.components.graph :as graph]
            [ui.components.typeahead :as typeahead]
            [ui.components.navigation :as nav]
            [ui.components.page :as page]
            [ui.utils :as utils])
  (:import goog.History))

;; (sec/set-config! :prefix "#")

(defn enable-history []
  ;;(sec/set-config! :prefix "#")
  (let [history (History.)
        navigation EventType/NAVIGATE]
    (events/listen history
                   navigation
                   #(-> % .-token sec/dispatch!))
    (doto history (.setEnabled true))))

(def history (History.))

(defn mount [id page]
  (om/root
   page
   app-state
   {:target (.getElementById js/document id)
    :shared {:url utils/host}
    :instrument (utils/with-omi)}))

(defn unmount [id]
  (om/root
   empty-comp
   app-state
   {:target (.getElementById js/document id)}))

(defn refresh-navigation []
  (let [token (.getToken history)
        set-active (fn [nav]
                     (assoc nav :active (= (:path nav) token)))]
    (swap! state #(map set-active %))))

(defn on-navigate [event]
  (refresh-navigation)
  (secretary/dispatch! (.-token event)))

(defroute "/" []
  (mount "app" nav/view))
(defroute "/graph" []
  (mount "app" graph/view))
(defroute "/typeahead" []
  (mount "app" typeahead/view))
(defroute "/mesh" []
  (mount "app" page/view))

(defn dispatch-current-route []
  (sec/dispatch! (.-pathname (.-location js/window))))
