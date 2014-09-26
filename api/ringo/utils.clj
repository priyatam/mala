(ns ringo.utils
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

(defmacro if-lets
  "Bind multiple bindings"
  ([bindings then-exp]
     (let [values (take-nth 2 (rest bindings))]
       `(if (and ~@values) (let ~bindings ~then-exp) false)))
  ([bindings then-exp else-exp]
     (let [values (take-nth 2 (rest bindings))]
       `(if (and ~@values) (let ~bindings ~then-exp) ~else-exp))))
