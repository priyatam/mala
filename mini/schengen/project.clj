(defproject schengencalc "0.1.0-SNAPSHOT"
  :description "A handy schengen zone calculator"
  :url "http://adambard.github.io/schengencalc"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [kioo "0.4.1"]
                 [org.omcljs/om "0.9.0"]]
  :plugins [[lein-cljsbuild "1.1.2"]]
  :cljsbuild {
    :builds [{
        :source-paths ["src"]
        :compiler {
          :output-to "resources/main.js"
          :optimizations :whitespace
          :pretty-print true}}]})
