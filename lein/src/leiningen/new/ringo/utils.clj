(ns {{name}}.utils
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [environ.core :refer [env]]))


(defmacro dbg [body]
  "Cheap inline debugging"
  `(let [x# ~body]
     (println x#)
     x#))

(defmacro mock [val body]
  val)
