(ns ringo.client
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [ringo.utils :refer [log guid debug]]))

(defn handler [response]
  (.log js/console (str response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "Error: " status " " status-text)))

(defn GET [res handler]
  (go (let [response (<! (http/get res {:with-credentials? false}))]
        (handler (:body response)))))
