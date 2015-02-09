(defproject ringo "0.1.1"
  :description "A starterkit for building apps with Ring and Om"
  :url "https://github.com/priyatam/ringo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/priyatam/ringo"}
  :min-lein-version "2.3.0"
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :global-vars {*warn-on-reflection* false *assert* false}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2755"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [com.cemerick/drawbridge "0.0.6" :exclusions [[org.clojure/tools.nrepl] [ring/ring-core] [cheshire]]]
                 [environ "1.0.0"]
                 [clj-time "0.8.0"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-defaults "0.1.3"]
                 [ring/ring-headers "0.1.1"]
                 [ring/ring-json "0.3.1"]
                 [ring-cors "0.1.6"]
                 [ring-basic-authentication "1.0.5"]
                 [compojure "1.3.1"]
                 [clj-http "1.0.1"]
                 [http-kit "2.1.18"]
                 [fogus/ring-edn "0.2.0"]
                 [prone "0.6.0"]
                 [oauth-clj "0.1.13"]
                 [cljs-ajax "0.3.9"]
                 [org.omcljs/om "0.8.8"]
                 [sablono "0.3.1"]
                 [garden "1.2.5" :exclusions [org.clojure/clojurescript]]]

  :source-paths ["api" "target/classes"]

  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["ui"]
                        :compiler {
                                   :output-to "resources/public/js/components.js"
                                   :output-dir "resources/public/js/out"
                                   :main ringo.components
                                   :asset-path "js/out"
                                   :optimizations :none
                                   :cache-analysis true
                                   :source-map true}}
                       {:id "prod"
                        :source-paths ["ui"]
                        :compiler {:output-to "dist/components.min.js"
                                   :main ringo.components
                                   :optimizations :advanced
                                   :pretty-print false}}]}

  :garden {:builds [{:id "components"
                     :source-paths ["design"]
                     :stylesheet ringo.components/styles
                     :compiler {:output-to "resources/public/css/components.css"
                                :pretty-print? true}}
                    {:id "layout"
                     :source-paths ["design"]
                     :stylesheet ringo.layout/styles
                     :compiler {:output-to "resources/public/css/layout.css"
                                :pretty-print? true}}
                    {:id "typography"
                     :source-paths ["design"]
                     :stylesheet ringo.typography/styles
                     :compiler {:output-to "resources/public/css/typography.css"
                                :pretty-print? true}}]}

  :minify-assets {:prod
                  {:assets
                   {"dist/styles.min.css" "dist"}
                   :options {:optimization :advanced}}}

  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.3.1"]
                                  [midje "1.6.3" :exclusions [org.codehaus.plexus/plexus-utils]]]}

             :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
             :uberjar {:aot :all}}

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-environ "1.0.0"]
            [lein-marginalia "0.7.1"]
            [lein-ring "0.9.1"]
            [lein-pprint "1.1.1"]
            [org.clojars.wokier/lein-bower "0.3.0"]
            [lein-asset-minifier "0.2.0"]
            [lein-garden "0.2.5"]
            [lein-pdo "0.1.1"]
            [lein-midje "3.1.1"]]

  :aliases {"init"  ["pdo" "bower" "install," "deps"]
            "ringo" ["pdo" "cljsbuild" "auto" "dev,"  "garden" "auto," "ring" "server"]
            "release" ["pdo" "cljsbuild" "once" "prod," "minify-assets" "prod"]}

  :main ^:skip-aot ringo.server
  :ring {:handler ringo.server/app}
  :uberjar-name "ringo.jar")
