(ns dev
  (:require [ringo.components :as main]
            [figwheel.client :as fw]))

(enable-console-print!)

(fw/start
 {:websocket-url "ws://localhost:3449/figwheel-ws"
  :on-jsload (fn []
               (print "Starting Chant ... "))
  :load-warninged-code true})
