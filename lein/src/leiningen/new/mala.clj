(ns leiningen.new.mala
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "mala"))

(defn mala [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' mala project.")
    (->files data
      ["project.clj" (render "project.clj" data)]
      ["compile_cljsc" (render "compile_cljsc" data)]
      ["env/dev/repl.cljs" (render "repl.cljs" data)]
      ["env/dev/mock.clj" (render "mock.clj" data)]
      ["env/dev/debug.cljs" (render "debug.cljs" data)]
      ["src/{{sanitized}}/ui/components/navigation.cljs" (render "navigation.cljs" data)]
      ["src/{{sanitized}}/ui/components/typeahead.cljs" (render "typeahead.cljs" data)]
      ["src/{{sanitized}}/ui/components/typeahead_styles.cljs" (render "typeahead_styles.cljs" data)]
      ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
      ["src/{{sanitized}}/ui/client.cljs" (render "client.cljs" data)]
      ["src/{{sanitized}}/ui/pages.cljs" (render "pages.cljs" data)]
      ["src/{{sanitized}}/ui/router.cljs" (render "router.cljs" data)]
      ["src/{{sanitized}}/ui/state.cljs" (render "state.cljs" data)]
      ["src/{{sanitized}}/ui/types.cljs" (render "types.cljs" data)]
      ["src/{{sanitized}}/ui/utils.cljs" (render "utils.cljs" data)]
      ["src/{{sanitized}}/ui/client.cljs" (render "client.cljs" data)]
      ["src/{{sanitized}}/layout/grid.clj" (render "grid.clj" data)]
      ["src/{{sanitized}}/layout/typography.clj" (render "typography.clj" data)]
      ["src/{{sanitized}}/layout/index.clj" (render "index.clj" data)]
      ["resources/public/index.html" (render "index.html" data)]
      ["README.md" (render "README.md" data)]
      ["LICENSE" (render "LICENSE" data)]
      [".gitignore" (render "gitignore" data)])))
