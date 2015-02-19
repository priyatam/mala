(ns {{name}}.design.styles
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [{{name}}.design.components :as components]
            [{{name}}.design.typography :as typography]
            [{{name}}.design.layout :as layout]))

(def all
  (merge components/styles layout/styles typography/styles))
