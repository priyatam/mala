(defproject mala "0.3.0"
  :description "A starterkit for building apps with Garden, Ring, and Om"
  :url "https://github.com/priyatam/mala"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/priyatam/mala"}
  :min-lein-version "2.5.0"
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :global-vars {*warn-on-reflection* false *assert* false}

  :dependencies [[org.clojure/clojure "1.7.0-beta3"]
                 [org.clojure/clojurescript "0.0-3211"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-headers "0.1.3"]
                 [ring/ring-json "0.3.1"]
                 [fogus/ring-edn "0.2.0"]
                 [ring-cors "0.1.7"]
                 [compojure "1.3.4"]
                 [cljs-http "0.1.30"]
                 [http-kit "2.1.18"]
                 [org.omcljs/om "0.8.8"]
                 [sablono "0.3.4"]
                 [secretary "1.2.3"]
                 [garden "1.2.5"]
                 [prone "0.8.1"]
                 [environ "1.0.0"]]

  :source-paths ["src" "tasks" "target/classes"]

  :clean-targets ^{:protect false} ["resources/public/js" "target/classes"]

  :cljsbuild {:builds
              {:app {:source-paths ["src"]
                     :compiler {:output-to "resources/public/js/components.js"
                                :output-dir "resources/public/js/out"
                                :main dev.repl
                                :asset-path "js/out"
                                :optimizations :none
                                :cache-analysis true
                                :source-map true}}}}

  :profiles {:dev {:dependencies [[figwheel "0.3.3"]
                                  [figwheel-sidecar "0.3.3"]
                                  [ring/ring-mock "0.2.0"]
                                  [ring/ring-devel "1.3.2"]
                                  [precursor/om-i "0.1.6"]]
                   :env {:is-dev true}
                   :cljsbuild {:builds
                               {:app {:source-paths ["env/dev"]}}}
                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7888
                              :css-dirs ["resources/public/css"]
                              :open-file-command "emacsclient"
                              :ring-handler api.server/app}
                   :garden {:builds
                            [{:id "design"
                              :source-paths ["src/design"]
                              :stylesheet design.styles/all
                              :compiler {:output-to "resources/public/css/styles.css"
                                         :pretty-print true}}]}}

             :uberjar {:hooks [leiningen.garden leiningen.cljsbuild]
                       :env {:production true}
                       :omit-source true
                       :aot :all
                       :garden {:builds
                                [{:id "prod"
                                  :source-paths ["src/design"]
                                  :stylesheet design.styles/all
                                  :compiler {:output-to "resources/public/css/styles.css"
                                             :pretty-print? false}}]}
                       :cljsbuild {:builds
                                   {:prod {:source-paths ["src"]
                                           :compiler {:output-to "resources/public/js/components.js"
                                                      :optimizations :advanced
                                                      :pretty-print false}}}}}}

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.3.3"]
            [lein-environ "1.0.0"]
            [lein-ring "0.9.3"]
            [lein-kibit "0.0.8"]
            [lein-pprint "1.1.1"]
            [lein-garden "0.2.5"]
            [lein-pdo "0.1.1"]
            [lein-cljfmt "0.1.7"]
            [jonase/eastwood "0.2.1"]]

  :aliases {"clean-all"  ["pdo" "clean," "garden" "clean"]
            "dev" ["pdo" "garden" "auto," "figwheel"]
            "format" ["cljfmt" "check"]
            "analyze" ["pdo" "kibit," "eastwood"]
            "prod" ["pdo" "clean," "uberjar"]}

  :main ^:skip-aot api.server
  :uberjar-name "ringo.jar")
