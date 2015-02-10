(ns ringo.server
  (:require [environ.core :refer [env]]
            [clojure.java.io :as io]
            [ring.middleware.cors :refer :all]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.edn :refer :all]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.session :as session]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.nested-params :as nested-params]
            [compojure.handler :refer [site] :as handler]
            [prone.debug :refer [debug]]
            [prone.middleware :as prone]
            [org.httpkit.server :as httpkit]
            [ringo.router :as router])
  (:gen-class))

;; Middleware

(defn wrap-error-page [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           {:status 500
            :headers {"Content-Type" "text/html"}
            :body (slurp (io/resource "public/500.html"))}))))

(def app
  (->
   (handler/site router/routes)
   (wrap-json-body)
   (wrap-json-response)
   (wrap-edn-params)
   (wrap-defaults site-defaults)
   (wrap-defaults api-defaults)
   (prone/wrap-exceptions)
   (wrap-error-page)
   (wrap-cors
    :access-control-allow-origin #"http://localhost:3000"
    :access-control-allow-methods ["GET"]
    :access-control-allow-headers ["Content-Type" "X-Requested-With"])))

(defn -main []
 (let [port (Integer. (or (System/getenv "PORT") 3449))]
  (httpkit/run-server app {:port port})
  (println "server started")))
