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
      ["src/{{sanitized}}/api/db.clj" (render "db.clj" data)]
      ["src/{{sanitized}}/api/router.clj" (render "router.clj" data)]
      ["src/{{sanitized}}/api/server.clj" (render "server.clj" data)]
      ["src/{{sanitized}}/api/utils.clj" (render "utils.clj" data)]
      ["src/{{sanitized}}/ui/client.cljs" (render "client.cljs" data)]
      ["src/{{sanitized}}/ui/components/hello.cljs" (render "hello.cljs" data)]
      ["src/{{sanitized}}/ui/components/typeahead.cljs" (render "typeahead.cljs" data)]
      ["src/{{sanitized}}/ui/main.cljs" (render "main.cljs" data)]
      ["src/{{sanitized}}/ui/pages.cljs" (render "pages.cljs" data)]
      ["src/{{sanitized}}/ui/router.cljs" (render "router.cljs" data)]
      ["src/{{sanitized}}/ui/state.cljs" (render "state.cljs" data)]
      ["src/{{sanitized}}/ui/types.cljs" (render "types.cljs" data)]
      ["src/{{sanitized}}/ui/utils.cljs" (render "utils.cljs" data)]
      ["src/{{sanitized}}/ui/client.cljs" (render "client.cljs" data)]
      ["src/{{sanitized}}/ui/utils.cljs" (render "utils.cljs" data)]
      ["src/{{sanitized}}/design/components.clj" (render "components.clj" data)]
      ["src/{{sanitized}}/design/layout.clj" (render "layout.clj" data)]
      ["src/{{sanitized}}/design/typography.clj" (render "typography.clj" data)]
      ["src/{{sanitized}}/design/styles.clj" (render "styles.clj" data)]
      ["resources/public/index.html" (render "index.html" data)]
      ["README.md" (render "README.md" data)]
      ["LICENSE" (render "LICENSE" data)]
      ["Procfile" (render "Procfile" data)]
      ["system.properties" (render "system.properties" data)]
      [".gitignore" (render "gitignore" data)])))
