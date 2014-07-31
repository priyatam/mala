(ns ringo.router
  (:require [environ.core :refer [env]]
            [clojure.java.io :as io]
            [cemerick.drawbridge :as drawbridge]
            [ring.middleware.basic-authentication :as basic]
            [ring.middleware.content-type :refer :all]
            [ring.middleware.edn :refer :all]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.params :as params]
            [ring.middleware.resource :refer :all]
            [ring.middleware.session :as session]
            [ring.middleware.session.cookie :as cookie]
            [ring.middleware.nested-params :as nested-params]
            [ring.middleware.content-type :refer :all]
            [ring.middleware.not-modified :refer :all]
            [ring.util.response :refer :all]
            [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.route :as route])
  (:import java.net.URI))


;; Middleware

(defn edn-response [data & [status]]
  "Generate edn response"
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

;; Utils

(defn resolve-uri
  [context uri]
  (let [context (if (instance? URI context) context (URI. context))]
    (.resolve context uri)))

(defn context-uri
  "Resolves a [uri] against the :context URI (if found) in the provided
   Ring request.  (Only useful in conjunction with compojure.core/context.)"
  [{:keys [context]} uri]
  (if-let [base (and context (str context "/"))]
    (str (resolve-uri base uri))
    uri))

;; nRepl

(defn authenticated? [user pass]
  (= [user pass] [(env :repl-user false) (env :repl-password false)]))

(def drawbridge
  (-> (drawbridge/ring-handler)
      (session/wrap-session)
      (basic/wrap-basic-authentication authenticated?)))

(def drawbridge-handler
  (-> (cemerick.drawbridge/ring-handler)
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

(defn respond-edn [data & [status]]
  "Generate edn response"
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

;; Routes

(defroutes routes

  (GET "/" []
       (slurp (io/resource (str "index.html")))))
