(defproject ringo "0.1.0"
  :description "A starterkit for building apps with Ring and Om"
  :url "https://github.com/priyatam/ringo"
  :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.3.0"
  :jvm-opts ["-Xms768m" "-Xmx768m"]
  :global-vars {*warn-on-reflection* false *assert* false}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [com.cemerick/drawbridge "0.0.6" :exclusions [[org.clojure/tools.nrepl] [ring/ring-core] [cheshire]]]
                 [environ "1.0.0"]
                 [clj-time "0.8.0"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-defaults "0.1.1"]
                 [ring/ring-headers "0.1.0"]
                 [ring/ring-json "0.3.1"]
                 [ring-cors "0.1.4"]
                 [ring-basic-authentication "1.0.5"]
                 [compojure "1.1.9"]
                 [http-kit "2.1.19"]
                 [clj-http "1.0.0"]
                 [fogus/ring-edn "0.2.0"]
                 [prone "0.6.0"]
                 [oauth-clj "0.1.13"]
                 [cljs-ajax "0.3.0"]
                 [om "0.7.1"]
                 [sablono "0.2.22"]]

  :source-paths ["api"]

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
                                   :output-to "resources/public/js/components.js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :preamble ["react/react.min.js"]
                                   :externs ["react/externs/react.js"]}}]}

  :less {:source-paths ["design/less"]
          :target-path "resources/public/css"}

  :minify-assets {:dev
                  {:assets
                   {"resources/public/css/site.min.css" "resources/public/css"}
                   :options {:optimization :none}}
                  :release
                  {:assets
                   {"resources/public/css/site.min.css" "resources/public/css"}
                   :options {:optimization :advanced}}}

  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.3.1"]
                                  [midje "1.6.3" :exclusions [org.codehaus.plexus/plexus-utils]]]}

             :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
             :uberjar {:aot :all}}

  :plugins [;[lein-drip "0.1.1-SNAPSHOT"]
            [lein-cljsbuild "1.0.3"]
            [lein-less "1.7.2"]
            [lein-environ "1.0.0"]
            [lein-marginalia "0.7.1"]
            [lein-ring "0.8.11"]
            [lein-pprint "1.1.1"]
            [org.clojars.wokier/lein-bower "0.3.0"]
            [lein-asset-minifier "0.2.0"]
            [lein-heroku "0.1.1"]
            [lein-pdo "0.1.1"]
            [lein-midje "3.1.1"]]

  :aliases {"init"  ["pdo" "bower" "install," "deps"]
            "ringo" ["pdo" "cljsbuild" "auto," "less" "auto," "ring" "server"]}

  :main ^:skip-aot ringo.api
  :ring {:handler ringo.server/app}
  :uberjar-name "ringo.jar")
