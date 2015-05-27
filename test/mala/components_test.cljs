(ns mala.components-test
  (:require-macros [cemerick.cljs.test
                    :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]))

(deftest hello-test
  (is (= js/Infinity (/ 1 0) (/ (int 1) (int 0)))))
