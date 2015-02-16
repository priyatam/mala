;; credit: https://github.com/omcljs/om/blob/master/examples/typeahead/src/core.cljs

(ns ringo.components.typeahead
  (:refer-clojure :exclude [chars])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [clojure.string :as string]))

(defn- hidden [^boolean bool]
  (if bool
    #js {:display "none"}
    #js {:display "block"}))

(defn- word-index [index owner]
  (om/component
    (dom/span #js {:className "word-index"} (om/value index))))

(defn- word-count [count owner]
  (om/component
    (dom/span #js {:className "word-count"} (om/value count))))

(defn- word [the-word owner]
  (om/component
    (dom/span #js {:className "word"} (om/value the-word))))

(defn- item [the-item owner]
  (om/component
    (dom/li #js {:style (hidden (:hidden the-item))}
      (om/build word-index (:index the-item))
      (dom/span #js {:className "word-index-sep"} " ")
      (om/build word (:word the-item))
      (dom/span #js {:className "word-count-sep"} " ")
      (om/build word-count (:count the-item)))))

(defn- stripe [text bgc]
  (let [st #js {:backgroundColor bgc}]
    (dom/li #js {:style st} text)))

(defn- change [e owner]
  (om/set-state! owner :text (.. e -target -value)))

(defn view [data owner]
  (reify
    om/IInitState
    (init-state [_] {:text ""})
    om/IRenderState
    (render-state [_ {:keys [text]}]
      (let [words (:words data)]
        (dom/div nil
          (dom/h2 #js {:className "typeahead"} "Search")
          (dom/input
            #js {:type "text"
                 :ref "text-field"
                 :value text
                 :className "search"
                 :onChange #(change % owner)})
          (apply dom/ul nil
            (om/build-all item words
              {:fn (fn [x]
                     (if-not (string/blank? text)
                       (cond-> x
                         (not (zero? (.indexOf (:word x) text)))
                         (assoc :hidden true))
                       x))})))))))
