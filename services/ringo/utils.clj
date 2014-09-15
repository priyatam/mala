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

(defmacro ignore [body & handler]
  "Ingores a body that throws an exception by returning nil.
   If handler is passed, exc is returned to the handler."
  `(let [resp#  (try
                  ~body
                  (catch Exception e# e#))
         thrown-exc# (if (instance? Exception resp#)
                        true nil)
         success# (if (not thrown-exc#)
                    resp#)
         failure# (if thrown-exc#
                    (if (not (nil? ~handler))
                      (apply ~handler (.getMessage resp#))
                      nil)
                    nil)]
     (or success# failure#)))

(defmacro interceptor
  "Simple interceptor"
  [fnc & body]
  `(let [es# (setup)]
     (binding [*out* s#]
       ~@body
       (str s#))))

(defmacro if-lets
  "Bind multiple bindings"
  ([bindings then-exp]
     (let [values (take-nth 2 (rest bindings))]
       `(if (and ~@values) (let ~bindings ~then-exp) false)))
  ([bindings then-exp else-exp]
     (let [values (take-nth 2 (rest bindings))]
       `(if (and ~@values) (let ~bindings ~then-exp) ~else-exp))))
