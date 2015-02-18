(ns ringo.utils
  (:require [cljs.reader :as reader]
            [clojure.string :as str])
  (:import [goog.ui IdGenerator]))

(enable-console-print!)

(def debug true)

(defonce base-url (str js/window.location.origin "/"))

;; Logging

(defn log
  "log messages if the browser console is open"
  [& messages]
  (when js/console
    (.apply (.-log js/console) js/console
            (into-array (map clj->js messages)))))

(defn logp "Print given arguments and return the last one"
  [& values]
  (log (apply pr-str values))
  (last values))

;; Interop

(defn $ [id]
  (.getElementById js/document id))

(defn to-clj [data]
  (js->clj data :keywordize-keys true))

;; String

(defn format
  "Format a string using goog.string.format"
  [fmt & args]
  (apply goog.string/format fmt args))

;; Stuff

(defn guid []
  (-> IdGenerator
      .getInstance
      .getNextUniqueId))

(defn uuid
  "Generate a type 4 random UUID: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
  []
  (let [r (repeatedly 30 (fn [] (.toString (rand-int 16) 16)))]
    (apply str (concat (take 8 r) ["-"]
                       (take 4 (drop 8 r)) ["-4"]
                       (take 3 (drop 12 r)) ["-"]
                       [(.toString  (bit-or 0x8 (bit-and 0x3 (rand-int 15))) 16)]
                       (take 3 (drop 15 r)) ["-"]
                       (take 12 (drop 18 r))))))
