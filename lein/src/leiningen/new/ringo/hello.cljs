;; credit: https://github.com/omcljs/om/blob/master/examples/hello/src/core.cljs

(ns {{name}}.ui.components.hello
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(defn component [data owner]
  (reify
    om/ICheckState
    om/IInitState
    (init-state [_]
      {:count 0})
    om/IWillMount
    (will-mount [_]
      (println "Hello widget mounting"))
    om/IWillUnmount
    (will-unmount [_]
      (println "Hello widget unmounting"))
    om/IRenderState
    (render-state [_ {:keys [count]}]
      (println "Render!")
      (html
       [:div
        [:h2 "Hello world!"]
        [:p (str "Count: " count)]
        [:button {:on-click
                  #(do
                     (println "I can read!" (:foo data))
                     (om/update-state! owner :count inc))} "Bump!"]
        [:button {:onClick
                  #(do
                     (println "I can also read!" (:foo data))
                     (om/update-state! owner :count identity))} "Do Nothing"]]))))
