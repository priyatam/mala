(ns {{name}}.ui.components.typeahead
  (:refer-clojure :exclude [chars])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [clojure.string :as string]
            [mesh.dom :as mesh-dom :refer [insert-styles]]
            [{{name}}.ui.components.typeahead-styles :as styles]))

(defn- hidden [^boolean bool]
  (if bool
    #js {:display "none"}
    #js {:display "block"}))

(defn- word-index [index owner]
  (om/component
    (html
     [:span {:class "word-index"} (om/value index)])))

(defn- word-count [count owner]
  (om/component
    (html
     [:span {:class "word-count"} (om/value count)])))

(defn- word [the-word owner]
  (om/component
    (html
     [:span {:class "word"} (om/value the-word)])))

(defn- item [the-item owner]
  (om/component
    (html
     [:li {:style (hidden (:hidden the-item))}
      (om/build word-index (:index the-item))
      (om/build word (:word the-item))
      (om/build word-count (:count the-item))])))

(defn- stripe [text bgc]
  (let [st #js {:backgroundColor bgc}]
    (html
     [:li {:style st} text])))

(defn- change [e owner]
  (om/set-state! owner :text (.. e -target -value)))

(defn view [data owner]
  (reify
    om/IInitState
    (init-state [_] {:text ""})
    om/IWillMount
    (will-mount [_]
      (println "Widget Mounting")
      (insert-styles styles/index))
    om/IRenderState
    (render-state [_ {:keys [text]}]
      (let [words (:words data)]
        (html
         [:div
          [:h2 {:class "typeahead"} "Search"]
          [:input {:type "text"
                   :ref "text-field"
                   :value text
                   :class "search"
                   :on-change #(change % owner)}]
          [:ul (om/build-all
                item words
                {:fn (fn [x]
                       (if-not (string/blank? text)
                         (cond-> x
                           (not (zero? (.indexOf (:word x) text)))
                           (assoc :hidden true))
                         x))})]])))))
