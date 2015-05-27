(ns layout.typography
  (:refer-clojure :exclude [+ - * / rem])
  (:require [garden.def :refer [defstyles defrule defkeyframes]]
            [garden.core :refer [css]]
            [garden.units :as u :refer [px pt em rem]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-media]]
            [mesh.utils :as utils]
            [mesh.respond :as respond]
            [mesh.typography :as typo :refer [font vr-block scales]]
            [mesh.typesetting :as typesetting :refer [scale-type make-serifs]]))

(def ff-serif ["\"EB Garamond\"" "Georgia" "Times" "serif"])
(def ff-sans ["\"Fira Sans\"" "sans-serif"])
(def ff-mono ["\"Source Code Pro\"" "monospace"])

(defn body-copy [size]
  {:font-family ff-serif
   :font-weight 400
   :font-style "normal"
   :font-size size
   :text-align "left"})

(defn heading-copy [size]
  {:font-family ff-sans
   :font-weight 400
   :font-style "normal"
   :font-size size
   :text-align "left"})

(defn code-copy [size]
  {:font-family ff-mono
   :font-weight 400
   :font-style "normal"
   :font-size size
   :text-align "left"})

(def golden 1000/1618)
(def minor-sixth 8/9)
(def minor-third 5/6)

(def ms
  (let [f (typo/modular-scale-fn 16 minor-third)]
    (fn [n]
      (px (f n)))))

(defrule homepage :section.home)
(defrule body :body)
(defrule h1 :h1)
(defrule h2 :h2)
(defrule h3 :h3)
(defrule h4 :h4)
(defrule h5 :h5)
(defrule h6 :h6)
(defrule small :p.small)
(defrule medium :p.medium)
(defrule large :p.large)

(defn typeset [serif sans mono]
  [[:body :p :a (body-copy (ms 1))]
   [:h1 (heading-copy (ms 5))]
   [:h2 (heading-copy (ms 4))]
   [:h3 (heading-copy (ms 3))]
   [:h4 (heading-copy (ms 2))]
   [:h5 :h6 (code-copy (ms 2))]
   [:header (font serif 4 700 0.3 1.2 "small-caps")]
   [:footer (font sans 1 100 0.3 1.2)]])

(defstyles classic-typeset
  (typeset ff-serif ff-sans ff-mono))

(defstyles type-scales
  (body
   (body-copy (ms 0)))

  (homepage
   (h1
     (respond/tablet
      {:padding  [[0 (ms 0)]]
       :font-size (ms 5)
       :line-height (ms 5.2)})
     (respond/iphone-5
      {:padding  [[0 (ms 1)]]
       :font-size (ms 5)
       :line-height (ms 2)}))
   (h2
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 4)
       :line-height (ms 4)}))
   (h3
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 3)
       :line-height (ms 3)}))
   (h4
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 2)
       :line-height (ms 2)}))
   (h5
     (respond/tablet
      {:padding  [[0 (ms 1)]]
       :font-size (ms 1)
       :line-height (ms 1)}))
   (h6
     (respond/tablet
      {:padding  [[0 (ms 0)]]
       :font-size (ms 0)
       :line-height (ms 2)}))))

(def fonts {:font-size-base (em 1.5)
            :line-height-base (em 1.45)
            :ff-serif ["EB Garamond" "Serif"]
            :ff-sans ["Fira Sans" "sans-serif"]
            :ff-mono ["Source Code Pro" "monospace"]})

(defn headings [declarations]
  [:h1 :h2 :h3 :header declarations])

(def settings
  {:min-width (px 400)
   :max-width (px 1200)
   :min-font (px 12)
   :max-font (px 32)
   :body-font (:eb-garamond typo/font-families)
   :header-font (:eb-garamond typo/font-families)
   :header-font-weight 600
   :header-color "#111"
   :scale :golden-ratio
   :breakpoints {:mobile (px 480)
                 :tablet (px 960)
                 :laptop (px 1440)
                 :monitor (px 1920)}})

;; DSL
(def default-typeset
  (-> headings
      (scale-type settings)
      (make-serifs typo/font-families)))

(defstyles styles
  (list classic-typeset
        type-scales))
