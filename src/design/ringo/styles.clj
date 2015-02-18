(ns ringo.styles
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [ringo.components :as components]
            [ringo.typography :as typography]
            [ringo.layout :as layout]))

(def all
  (merge components/styles layout/styles typography/styles))
