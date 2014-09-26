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
            [compojure.core :refer [context defroutes GET PUT POST DELETE ANY]]
            [compojure.route :as route]
            [prone.debug :refer [debug]])
  (:import java.net.URI))


;; Utils

(defn edn-response [data & [status]]
  "Generate edn response"
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

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

;; Routes

(defroutes routes

  (GET "/" []
       (slurp (io/resource "public/index.html")))

  (context "/api" []
     (GET "/libraries" []
          (edn-response #{:ring :clojure :om :sablono :secretary :garden}))
     (GET "/authors" []
          ;(debug)
          (edn-response [{:name "James Reeves" :known-for #{:ring :compojure :hiccup}}
                     {:name "Chris Granger" :known-for #{:sqlkorma :clojurescript :lighttable }}
                     {:name "David Nolen"   :known-for #{:clojurescript :core-logic :om}}])))
     (GET "/exception" []  (edn-response (/ 1 0)))

  (route/resources "/")
  (route/not-found "Page not found"))
