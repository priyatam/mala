(ns ringo.server
  (:require [environ.core :refer [env]]
            [ring.middleware.cors :refer :all]
            [ring.middleware.defaults :refer :all]
            [ringo.router :refer :all]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [compojure.handler :refer [site] :as handler]
            [prone.debug :refer [debug]]
            [prone.middleware :as prone]
            [org.httpkit.server :as httpkit]
            [ringo.router :refer :all])
   (:gen-class))


(def app
  (->
   (handler/site routes)
   (wrap-json-body)
   (wrap-json-response)
   ;(wrap-edn-response)
   (wrap-defaults site-defaults)
   (wrap-defaults api-defaults)
   (prone/wrap-exceptions)
   (wrap-cors
    :access-control-allow-origin #"http://localhost:8000"
    :access-control-allow-methods ["GET"]
    :access-control-allow-headers ["Content-Type"])))


(defn -main []
 (let [port (Integer. (or (System/getenv "PORT") 8000))]
  (httpkit/run-server app {:port port})
  (println "server started")))
