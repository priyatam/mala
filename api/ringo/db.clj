(ns ringo.db
  (:require [environ.core :refer [env]]
            [clojure.java.io :as io]))

;; Dummy Database

(def data {:authors [{:name "Chris Granger" :known-for #{:sqlkorma :clojurescript :lighttable }}
                      {:name "David Nolen"   :known-for #{:clojurescript :core-logic :om}}
                      {:name "James Reeves"  :known-for #{:ring :compojure :hiccup}}]

            :libraries #{:ring :clojure :hiccup :om :sablono :bootstrap}

            :devices [{:id "01"
                       :type "electricityConsumption"
                       :description "I'm a device with id 01"
                       :unit "kWh"}
                      {:id "02"
                       :type "gasConsumption"
                       :description "I'm a device with id 02"
                       :unit "kWh"}]

            :measurements [{:id "01" :type "electricityConsumption" :timestamp "01/01/2011" :value 0.2}
                           {:id "01" :type "electricityConsumption" :timestamp "01/02/2011" :value 0.3}
                           {:id "01" :type "electricityConsumption" :timestamp "01/03/2011" :value 0.2}
                           {:id "01" :type "electricityConsumption" :timestamp "01/04/2011" :value 0.25}
                           {:id "01" :type "electricityConsumption" :timestamp "01/05/2011" :value 0.95}
                           {:id "02" :type "gasConsumption" :timestamp "01/01/2011" :value 6}
                           {:id "02" :type "gasConsumption" :timestamp "01/02/2011" :value 10}
                           {:id "02" :type "gasConsumption" :timestamp "01/03/2011" :value 12}
                           {:id "02" :type "gasConsumption" :timestmap "01/04/2011" :value 15}
                           {:id "02" :type "gasConsumption" :timestamp "01/05/2011" :value 18}]})
