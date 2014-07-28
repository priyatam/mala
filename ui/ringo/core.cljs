(ns ringo.core
  (:require-macros [ringo.utils :refer [dbg mock]]
                   [cljs.core.async.macros :as am :refer [go go-loop alt!]])
  (:require [cljs.core.async :as async :refer [>! <! alts! chan sliding-buffer put! close!]]
            [clojure.string :as string]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.reader :as reader]))

(def $ js/jQuery)

(def *debug* true)

(defn to-clj [data]
  (js->clj data :keywordize-keys true))

(defn in?
  [seq elm]
  (some #(= elm %) seq))

(defn fltten [nested-list]
  "List of List to List"
  (apply concat nested-list))

(defn to-int [str]
  (js/parseInt str))

(defn zenup->cljs [content]
  (reader/read-string content))

(defn string->clj [content]
  (reader/read-string content))

(defn log [x]
  (.log js/console (clj->js x)))

