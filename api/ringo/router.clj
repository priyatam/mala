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
            [prone.debug :refer [debug]]
            [oauth.twitter :refer :all]
            [ringo.db :as db])
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

;; OAuth

(def consumer-key (env :twitter-api-key))
(def consumer-secret (env :twitter-api-secret))

;; Temporary / Maitain a db
(def access-token (atom nil))

(def oauth
  (oauth-client
   consumer-key
   consumer-secret
   (:oauth-token access-token)
   (:oauth-verifier access-token)))

;; Routes

(defroutes routes

  (GET "/" []
       (slurp (io/resource "public/index.html")))

  (GET "/login" []
       (let [token (oauth-request-token consumer-key consumer-secret)]
         (oauth-authorize (:oauth-token token))))

  (GET "/oauth" [oauth-token oauth-verifier]
       (let [token (oauth-access-token consumer-key oauth-token oauth-verifier)]
            (swap! access-token conj token)))

  (POST "/tweet" [status]
       (oauth
        {:method :post
         :url "http://api.twitter.com/1/statuses/update.json"
         :body (str status)}))

  (context "/api" []
     (GET "/libraries" []
          (let [libraries (get-in db/data [:libraries])]
            (when libraries
               (edn-response libraries))))

     (GET "/authors" []
          ;(debug)
          (let [authors (get-in db/data [:authors])]
             (when authors
               (edn-response authors))))

     (GET "/devices" []
          (let [devices (get-in db/data [:devices])]
             (when devices
               (edn-response devices))))

     (GET "/device/:id/type/:type/measurements" [id type]
          (let [measurements (filter #(and (= (:id %) id) (= (:type %) type)) (get-in db/data [:measurements]))]
            (when measurements
              (response measurements)))))

  (GET "/exception" []  (edn-response (/ 1 0)))

  (route/resources "/")

  (route/not-found "Page not found"))
