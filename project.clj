(defproject ringo "0.1.0"
  :description "A starterkit for building apps with Ring and Om"
  :url "https://github.com/priyatam/ringo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :global-vars {*warn-on-reflection* false *assert* false}

  ;;FIXME: java.lang.IllegalStateException: Can't change/establish root binding of: *warn-on-reflection* with set
  ;:eval-in-leiningen true

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [org.clojure/core.cache "0.6.4"]
                 [environ "1.0.0"]
                 [weasel "0.3.0"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-defaults "0.1.1"]
                 [ring/ring-headers "0.1.0"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-jetty-adapter "1.3.1"]
                 [ring-cors "0.1.4"]
                 [compojure "1.1.8"]
                 [clj-oauth "1.5.1"]
                 [fogus/ring-edn "0.2.0"]
                 [prone "0.6.0"]
                 [om "0.7.1"]
                 [kioo "0.4.0"]
                 [secretary "1.2.0"]
                 [prismatic/om-tools "0.3.2" :exclusions [org.clojure/clojure]]]

  :source-paths ["services"]
  :resource-paths ["src/main/resource"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["ui"]
                        :compiler {
                                   :output-to "resources/public/js/components.js"
                                   :output-dir "resources/public/js/out"
                                   :optimizations :none
                                   :pretty-print true
                                   :source-map true}}
                       {:id "release"
                        :source-paths ["ui"]
                        :compiler {
                                   :output-to "dist/js/components.js"
                                   :optimizations :whitespace
                                   :pretty-print false
                                   :preamble ["react/react.min.js"]
                                   :externs ["react/externs/react.js"]}}]}

  :profiles {:dev {:dependencies [[ring/ring-devel "1.3.1"]
                                  [midje "1.6.3" :exclusions [org.codehaus.plexus/plexus-utils]]]}
             :uberjar {:aot :all}}

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-environ "1.0.0"]
            [lein-marginalia "0.7.1"]
            [lein-ring "0.8.11"]
            [lein-pprint "1.1.1"]
            [lein-heroku "0.1.1"]
            [org.clojars.wokier/lein-bower "0.3.0"]
            [lein-midje "3.1.1"]]

  :main ^:skip-aot ringo.services
  :ring {:handler ring.app/app}
  :uberjar-name "ringo-app.jar")
