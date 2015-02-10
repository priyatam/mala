(ns {{name}}.components
  (:require-macros
   [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require [cljs.core.async :as async :refer (<! >! put! chan)]
            [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [{{name}}.utils :refer [log guid debug]]))

(defn widget [data]
  (om/component
   (html [:div "Hello world!"
          [:ul (for [n (range 1 10)]
                 [:li n])]
          (html/submit-button "React!")])))

(om/root widget {} {:target (.getElementById js/document "main")})
