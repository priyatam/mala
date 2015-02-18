(ns api.db
  (:require [environ.core :refer [env]]
            [clojure.java.io :as io]))

;; Dummy Database

(def data
  {:authors [{:name "Chris Granger" :known-for #{:sqlkorma :clojurescript :lighttable}}
             {:name "David Nolen"   :known-for #{:clojurescript :core-logic :om}}
             {:name "James Reeves"  :known-for #{:ring :compojure :hiccup}}]

   :libraries #{:ring :clojure :hiccup :om :sablono :bootstrap}

   :devices [{:id "01"
              :type "clojure"
              :description "the future of programming"
              :unit "MBs"}
             {:id "02"
              :type "clojurescript"
              :description "the future of browser"
              :unit "KBs"}]

   :measurements [{:id "01" :type "clojure" :timestamp "01/01/2011" :value 0.2}
                  {:id "01" :type "clojure" :timestamp "01/02/2011" :value 0.3}
                  {:id "01" :type "clojure" :timestamp "01/03/2011" :value 0.2}
                  {:id "01" :type "clojure" :timestamp "01/04/2011" :value 0.25}
                  {:id "01" :type "clojurescript" :timestamp "01/05/2011" :value 0.95}
                  {:id "02" :type "clojurescript" :timestamp "01/01/2011" :value 6}
                  {:id "02" :type "clojurescript" :timestamp "01/02/2011" :value 10}
                  {:id "02" :type "clojurescript" :timestamp "01/03/2011" :value 12}
                  {:id "02" :type "clojurescript" :timestmap "01/04/2011" :value 15}
                  {:id "02" :type "clojurescript" :timestamp "01/05/2011" :value 18}]})
