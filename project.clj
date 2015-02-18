(defproject ringo "0.2.0-SNAPSHOT"
  :description "A starterkit for building apps with Garden, Ring, and Om"
  :url "https://github.com/priyatam/ringo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/priyatam/ringo"}
  :min-lein-version "2.5.0"
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :global-vars {*warn-on-reflection* false *assert* false}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2843"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-defaults "0.1.3"]
                 [ring/ring-headers "0.1.1"]
                 [ring/ring-json "0.3.1"]
                 [fogus/ring-edn "0.2.0"]
                 [ring-cors "0.1.6"]
                 [compojure "1.3.1"]
                 [cljs-http "0.1.25"]
                 [http-kit "2.1.18"]
                 [org.omcljs/om "0.8.8"]
                 [sablono "0.3.4"]
                 [secretary "1.2.1"]
                 [garden "1.2.5"]
                 [prone "0.8.0"]
                 [environ "1.0.0"]]

  :source-paths ["src/api" "tasks" "target/classes"]

  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src/ui" "env"]
                        :compiler {
                                   :output-to "resources/public/js/components.js"
                                   :output-dir "resources/public/js/out"
                                   :main dev
                                   :asset-path "js/out"
                                   :optimizations :none
                                   :cache-analysis true
                                   :source-map true}}
                       {:id "prod"
                        :source-paths ["ui"]
                        :compiler {:output-to "dist/components.min.js"
                                   :main ringo.main
                                   :optimizations :advanced
                                   :pretty-print false}}]}

  :profiles {:dev {:dependencies [[figwheel "0.2.5-SNAPSHOT"]
                                  [figwheel-sidecar "0.2.5-SNAPSHOT"]
                                  [ring-mock "0.1.5"]
                                  [ring/ring-devel "1.3.1"]
                                  [leiningen "2.5.0"]]
                   :env {:is-dev true}
                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7888
                              :css-dirs ["resources/public/css"]
                              :open-file-command "emacsclient"
                              :ring-handler ringo.server/app}
                   :garden {:builds [{:id "components"
                                      :source-paths ["src/design"]
                                      :stylesheet ringo.components/styles
                                      :compiler {:output-to "resources/public/css/components.css"
                                                 :pretty-print? true}}
                                     {:id "layout"
                                      :source-paths ["src/design"]
                                      :stylesheet ringo.layout/styles
                                      :compiler {:output-to "resources/public/css/layout.css"
                                                 :pretty-print? true}}
                                     {:id "typography"
                                      :source-paths ["src/design"]
                                      :stylesheet ringo.typography/styles
                                      :compiler {:output-to "resources/public/css/typography.css"
                                                 :pretty-print? true}}]}}
             :uberjar {:aot :all}}

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.2.5-SNAPSHOT"]
            [lein-environ "1.0.0"]
            [lein-ring "0.9.1"]
            [lein-kibit "0.0.8"]
            [lein-pprint "1.1.1"]
            [lein-garden "0.2.5"]
            [lein-pdo "0.1.1"]
            [lein-cljfmt "0.1.7"]
            [jonase/eastwood "0.2.1"]
            [org.clojars.wokier/lein-bower "0.3.0"]]

  :aliases {"init"  ["pdo" "bower" "install," "deps"]
            "dev" ["pdo" "figwheel," "garden" "auto"]
            "format" ["cljfmt" "check"]
            "analyze" ["pdo" "kibit," "eastwood"]
            "release" ["pdo" "cljsbuild" "once" "prod"]}

  :main ^:skip-aot ringo.server
  :uberjar-name "ringo.jar")
