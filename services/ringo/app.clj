(ns ringo.app
  (:require [environ.core :refer [env]]
            [ring.middleware.cors :refer :all]
            [ring.middleware.defaults :refer :all]
            [ring.adapter.jetty :as jetty]
            [compojure.handler :refer [site] :as handler]
            [ringo.router :refer :all]))

(def app
  (->
   (handler/site routes)
   (wrap-defaults api-defaults)
   (wrap-cors
     :access-control-allow-origin #".+")))

(defn run-jetty [port]
  (jetty/run-jetty (wrap-drawbridge app)
                    {:port port :join? false
                     ;:configurator #(.setThreadPool % (QueuedThreadPool. 5))
                     }))

(defn -main []
 (let [port (Integer. (or (System/getenv "PORT") 8082))]
   (run-jetty port)))
