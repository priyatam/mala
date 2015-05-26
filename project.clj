(defproject mala "0.4.0"
  :description "A curated template for building and designing UIs in Clojurescript"
  :url "https://github.com/priyatam/mala"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/priyatam/mala"}
  :min-lein-version "2.5.0"
  :jvm-opts ["-Xms768m" "-server"]
  :global-vars {*warn-on-reflection* false *assert* false}

  :dependencies [[org.clojure/clojure "1.7.0-beta3"]
                 [org.clojure/clojurescript "0.0-3211"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.8.8"]
                 [sablono "0.3.4"]
                 [secretary "1.2.3"]
                 [cljs-http "0.1.30"]
                 [garden "1.2.5"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.3.3"]
            [lein-garden "0.2.5"]
            [lein-pdo "0.1.1"]]

  :source-paths ["src" "env" "target/classes"]

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

  :garden {:builds
           [{:id "design"
             :source-paths ["src/design"]
             :stylesheet design.styles/all
             :compiler {:output-to "resources/public/css/styles.css"
                        :pretty-print true}}
            {:id "prod"
             :source-paths ["src/design"]
             :stylesheet design.styles/all
             :compiler {:output-to "resources/public/css/styles.css"
                        :pretty-print? false}}]}

  :profiles {:dev {:dependencies [[figwheel "0.3.3"]
                                  [figwheel-sidecar "0.3.3"]
                                  [ring/ring-json "0.3.1"]
                                  [fogus/ring-edn "0.2.0"]
                                  [compojure "1.3.4"]
                                  [javax.servlet/servlet-api "2.5"]
                                  [precursor/om-i "0.1.6"]]
                   :env {:is-dev true}
                   :cljsbuild {:builds
                               {:app {:source-paths ["env/dev"]}}}
                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7888
                              :css-dirs ["resources/public/css"]
                              :open-file-command "emacsclient"
                              :ring-handler dev.mock/api}}}

  :aliases {"clean-all"  ["pdo" "clean," "garden" "clean"]
            "dev" ["pdo" "garden" "auto," "figwheel"]
            "release" ["pdo" "clean," "garden" "prod," "cljsbuild" "prod"]})
