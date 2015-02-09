(ns dev
  (:require [ringo.components :as main]
            [figwheel.client :as fw]))

(enable-console-print!)

(fw/start
 {:on-jsload (fn []
               (print "Starting Chant ... "))
  :load-warninged-code true})
