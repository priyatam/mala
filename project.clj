(defproject mala "0.5.2"
  :description "A integrated UI template for building and designing pages in Clojurescript"
  :url "https://github.com/priyatam/mala"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/priyatam/mala"}
  :min-lein-version "2.5.0"
  :jvm-opts ["-Xmx256m" "-server"]
  :global-vars {*warn-on-reflection* false *assert* false}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.clojure/core.async "0.2.374"]
                 [org.omcljs/om "0.9.0"]
                 [cljsjs/react "0.14.3-0"]
                 [cljsjs/react-dom "0.14.3-1"]
                 [cljsjs/react-dom-server "0.14.3-0"]
                 [facjure/mesh "0.4.0"]
                 [sablono "0.5.3"]
                 [secretary "1.2.3"]
                 [cljs-http "0.1.39"]]

  :plugins [[lein-cljsbuild "1.1.2"]
            [lein-figwheel "0.5.0"]
            [lein-garden "0.2.6"]
            [lein-pdo "0.1.1"]]

  :source-paths ["src" "env" "target/classes"]

  :clean-targets ^{:protect false} ["resources/public/js" "target/classes"]

  :cljsbuild {:builds [{:id "app"
                        :source-paths ["src" "env/dev"]
                        :compiler {:output-to "resources/public/js/components.js"
                                   :output-dir "resources/public/js/out"
                                   :main dev.repl
                                   :asset-path "js/out"
                                   :optimizations :none
                                   :cache-analysis true
                                   :source-map true}}
                       {:id "prod"
                        :source-paths ["src"]
                        :compiler {:output-to "dist/components.min.js"
                                   :main core
                                   :optimizations :advanced
                                   :pretty-print false}}]
              :test-commands {"unit-tests" ["phantomjs" :runner]}}

  :garden {:builds [{:id "design"
                     :source-paths ["src"]
                     :stylesheet layout.index/styles
                     :compiler {:output-to "resources/public/css/styles.css"
                                :pretty-print true}}
                    {:id "prod"
                     :source-paths ["src"]
                     :stylesheet layout.index/styles
                     :compiler {:output-to "dist/styles.min.css"
                                :pretty-print? false}}]}

  :profiles {:dev {:env {:is-dev true}
                   :dependencies [[figwheel "0.5.0"]
                                  [figwheel-sidecar "0.5.0"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [ring/ring-json "0.4.0"]
                                  [fogus/ring-edn "0.2.0"]
                                  [javax.servlet/servlet-api "2.5"]
                                  [precursor/om-i "0.1.7"]
                                  [compojure "1.4.0"]
                                  [ankha "0.1.4"]]
                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7888
                              :css-dirs ["resources/public/css"]
                              :open-file-command "emacsclient"
                              :ring-handler dev.mock/api}}}

  :aliases {"clean-all"  ["pdo" "clean," "garden" "clean"]
            "dev" ["pdo" "garden" "auto" "design," "figwheel"]
            "release" ["pdo" "clean," "garden" "once" "prod," "cljsbuild" "once" "prod"]})
