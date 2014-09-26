(ns ringo.server
  (:require [environ.core :refer [env]]
            [cemerick.drawbridge :as drawbridge]
            [ring.middleware.cors :refer :all]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.session :as session]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.nested-params :as nested-params]
            [ring.middleware.basic-authentication :as basic]
            [compojure.handler :refer [site] :as handler]
            [prone.debug :refer [debug]]
            [prone.middleware :as prone]
            [org.httpkit.server :as httpkit]
            [ringo.router :as router]
            [ringo.router :refer :all])
  (:gen-class))


;; Drawbridge REPL

(defn authenticated? [user pass]
  ;; NOTE: heroku config:add REPL_USER=[...] REPL_PASSWORD=[...]
  (= [user pass] [(env :repl-user false) (env :repl-password false)]))

(def drawbridge
  (-> (drawbridge/ring-handler)
      (session/wrap-session)
      (basic/wrap-basic-authentication authenticated?)))

(def drawbridge-handler
  (-> (drawbridge/ring-handler)
      (keyword-params/wrap-keyword-params)
      (nested-params/wrap-nested-params)
      (params/wrap-params)
      (session/wrap-session)))

(defn wrap-drawbridge [handler]
  (fn [req]
    (let [handler (if (= "/repl" (:uri req))
                    (basic/wrap-basic-authentication
                     drawbridge-handler authenticated?)
                    handler)]
      (handler req))))

(def app
  (->
   (handler/site router/routes)
   (wrap-json-body)
   (wrap-json-response)
   ;(wrap-edn-response)
   (wrap-defaults site-defaults)
   (wrap-defaults api-defaults)
   (prone/wrap-exceptions)
   (wrap-error-page)
   (wrap-cors
    :access-control-allow-origin #"http://localhost:8000"
    :access-control-allow-methods ["GET"]
    :access-control-allow-headers ["Content-Type"])))


(defn -main []
 (let [port (Integer. (or (System/getenv "PORT") 8000))]
  (httpkit/run-server app {:port port})
  (println "server started")))
