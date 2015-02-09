(ns ringo.client
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [ajax.core :refer [GET POST]]
            [cljs.core.async :refer [<!]]
            [ringo.utils :refer [log guid debug]]))

(def host "http://localhost:3449/" )

(defn handler [response]
  (.log js/console (str response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "Error: " status " " status-text)))

(defn getData [dname]
  (GET (str "/" dname {:handler handler
                       :error-handler error-handler})))
