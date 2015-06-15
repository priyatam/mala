(ns {{name}}.layout.index
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [{{name}}.layout.typography :as typography]
            [{{name}}.layout.grid :as grid]))

(def styles
  (merge typography/styles
         grid/styles))
