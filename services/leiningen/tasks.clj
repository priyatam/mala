(ns leiningen.tasks
  (:require [clojure.java.io :as io])
  (:use [panoptic.core])
  (:use [clojure.test]))

(defn copy-to-server [source-path dest-path]
  (io/copy (io/file source-path) (io/file dest-path)))
