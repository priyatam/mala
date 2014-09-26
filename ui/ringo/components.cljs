(ns ringo.components
  (:require-macros
   [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require [cljs.core.async :as async :refer (<! >! put! chan)]
            [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [ringo.client :as client]
            [ringo.utils :refer [log guid debug]]))


(def libraries-state (atom (client/getData "libraries")))

(defn widget [data]
  (om/component
   (html [:div
          [:ul (for [n @libraries-state]
                 [:li (str n)])]
          (html/submit-button "Download" )])))

(om/root widget {} {:target (.getElementById js/document "libraries")})

(defn animation-view [app owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (js/setInterval
        (fn [] (om/transact! app :count inc))
        16))
    om/IRender
    (render [_]
      (div nil (:count app)))))

