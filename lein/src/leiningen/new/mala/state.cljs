(ns {{name}}.ui.state
  (:require [om.core :as om]
            [om.dom :as dom]))

(def chars (into [] "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"))

(defn- rand-char []
  (nth chars (rand-int (count chars))))

(defn- rand-word []
  (apply str (take (inc (rand-int 5)) (repeatedly rand-char))))

(defonce app-state
  (atom {:text "Hello Ringo!"
         :devices {:all []}
         :chart {:data []}
         :words (into []
                      (map (fn [w i] {:index i :word w :count (count w)})
                           (sort (into [] (take 100 (repeatedly rand-word))))
                           (range)))}))


