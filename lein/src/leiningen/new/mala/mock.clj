(ns dev.mock
  (:require [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.edn :refer :all]
            [ring.util.response :refer :all]
            [compojure.handler :refer [site] :as handler]
            [compojure.core :refer [context defroutes GET PUT POST DELETE ANY]]
            [compojure.route :as route]))

(defonce data
  {:libraries #{:ring :clojure :hiccup :om :sablono :bootstrap}
   :devices [{:id "01"
              :type "clojure"
              :description "the future of jvm"
              :unit "MBs"}
             {:id "02"
              :type "clojurescript"
              :description "the future of javascript"
              :unit "KBs"}]
   :measurements [{:id "01" :type "clojure" :timestamp "01/01/2011" :value 1.2}
                  {:id "01" :type "clojure" :timestamp "01/02/2011" :value 0.3}
                  {:id "01" :type "clojure" :timestamp "01/03/2011" :value 0.2}
                  {:id "01" :type "clojure" :timestamp "01/04/2011" :value 0.25}
                  {:id "01" :type "clojurescript" :timestamp "01/05/2011" :value 0.95}
                  {:id "02" :type "clojurescript" :timestamp "01/01/2011" :value 6}
                  {:id "02" :type "clojurescript" :timestamp "01/02/2011" :value 10}
                  {:id "02" :type "clojurescript" :timestamp "01/03/2011" :value 82}
                  {:id "02" :type "clojurescript" :timestmap "01/04/2011" :value 55}
                  {:id "02" :type "clojurescript" :timestamp "01/05/2011" :value 18}]})

(defn edn-response [data & [status]]
  "Generate edn response"
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defroutes routes

  (GET "/libraries" []
       (let [libraries (get-in data [:libraries])]
         (when libraries
           (edn-response libraries))))

  (GET "/authors" []
       ;;(debug)
       (let [authors (get-in data [:authors])]
         (when authors
           (edn-response authors))))

  (GET "/devices" []
       (let [devices (get-in data [:devices])]
         (when devices
           (edn-response devices))))

  (GET "/device/:id/type/:type/measurements" [id type]
       (let [measurements (filter #(and
                                    (= (:id %) id) (= (:type %) type))
                                  (get-in data [:measurements]))]
         (when measurements
           (response measurements)))))

(def api
  (->
   (handler/site routes)
   (wrap-json-body)
   (wrap-json-response)
   (wrap-edn-params)))
