(ns layout.index
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [layout.typography :as typography]
            [layout.grid :as grid]))

(def styles
  (merge typography/styles
         grid/styles))
