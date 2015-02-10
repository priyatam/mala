(ns {{name}}.utils
  (:require [om.core :as om :include-macros true]
             [om.dom :as dom :include-macros true]
             [cljs.reader :as reader])
  (:import [goog.ui IdGenerator]))

(def debug true)

(enable-console-print!)

(defn to-clj [data]
  (js->clj data :keywordize-keys true))

(defn log [x]
  (.log js/console (clj->js x)))

(defn guid []
  (-> IdGenerator
      .getInstance
      .getNextUniqueId))

(defn $ [id] (.getElementById js/document id))

