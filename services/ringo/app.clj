(ns ringo.app
  (:require [environ.core :refer [env]]
            [ring.middleware.cors :refer :all]
            [ring.middleware.defaults :refer :all]
            [ring.adapter.jetty :as jetty]
            [ringo.router :refer :all]
            [compojure.handler :refer [site] :as handler]))

(def app
  (->
   (handler/site routes)
   (wrap-defaults api-defaults)
   (respond-edn)
   (wrap-cors
     :access-control-allow-origin #".+")))

(defn -main []
 (let [port (Integer. (or (System/getenv "PORT") 8082))]
   (jetty/run-jetty (wrap-drawbridge app)
                    {:port port :join? false})))
