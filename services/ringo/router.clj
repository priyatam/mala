(ns ringo.router
  (:require [environ.core :refer [env]]
            [clojure.java.io :as io]
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

;; Middleware

(defn wrap-edn-response [data & [status]]
  "Generate edn response"
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defn wrap-error-page [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           {:status 500
            :headers {"Content-Type" "text/html"}
            :body (slurp (io/resource "public/500.html"))}))))

;; Routes

(defroutes routes

  (GET "/" []
       (slurp (io/resource "public/index.html")))

  (route/resources "/")
  (route/not-found "Page not found"))
