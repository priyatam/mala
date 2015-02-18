(ns design.styles
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [design.components :as components]
            [design.typography :as typography]
            [design.layout :as layout]))

(def all
  (merge components/styles layout/styles typography/styles))
