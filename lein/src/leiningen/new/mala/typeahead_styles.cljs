(ns {{name}}.ui.components.typeahead-styles
  (:require [garden.core :refer [css]]
            [garden.units :as u :refer [px pt]]
            [garden.units :refer (px+ px* px- px-div em)]
            [garden.color :as color :refer [hsl rgb]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]))

(defn partly-rounded
  ([r1] (partly-rounded r1 r1))
  ([r1 r2]
   {:border {:top-right-radius r1
             :bottom-left-radius r2}}))

(def box
  [:.box
   {:-moz {:border-radius "px"
           :box-sizing "border-box"}}])

(def word
  [:span.word {:border-radius (em 1)
               :font-size (px 16)
               :word-spacing (em 2)
               :padding (em 0.5)}])

(def word-index
  [:span.word-index {:border-radius (em 1)
                     :font-size (px 16)
                     :word-spacing (em 2)
                     :padding (em 0.5)}])

(def word-count-sep
  [:span.word-count-sep {:float "left"
                         :padding (em 0.79)}])

(def input-search
  [:input.search
   {:border [[(px 1) "#dbe4e4" "solid"]]
    :height (px 30)
    :width (px 350)
    :overflow "hidden"
    :border-radius (em 10)}])

(def typeahead
  [:h2.typeahead {:color "#666"
                  :padding [["1.5%" "10%" "1%"]]
                  :line-height (em 1)
                  :word-spacing (em 2)}])

(def index
  (css (list
        input-search
        typeahead
        word
        word-index
        word-count-sep)))
