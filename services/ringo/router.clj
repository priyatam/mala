(ns ringo.router
  (:require [clojure.java.io :as io]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.nested-params :as nested-params]
            [ring.middleware.session :as session]
            [ring.middleware.session.cookie :as cookie]
            [ring.middleware.content-type :refer :all]
            [ring.util.response :as resp]
            [ring.middleware.edn :refer :all]
            [ring.middleware.basic-authentication :as basic]
            [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.route :as route]
            [cemerick.drawbridge :as drawbridge]
            [environ.core :refer [env]])
  (:use ring.util.response)
  (:use ring.middleware.resource)
  (:use ring.middleware.content-type)
  (:use ring.middleware.not-modified)
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
