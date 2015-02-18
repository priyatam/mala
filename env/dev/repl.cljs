(ns dev.repl
  (:require-macros
   [figwheel.client.utils :refer [enable-dev-blocks!]])
  (:require [ringo.main :as main]
            [figwheel.client :as fw]))

(enable-console-print!)
(enable-dev-blocks!)

(fw/start
 {:websocket-url "ws://localhost:3449/figwheel-ws"
  :build-id "dev"
  :on-jsload (fn []
               (print "Starting Figwheel ... "))
  :load-warninged-code true})
