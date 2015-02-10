(ns leiningen.new.ringo
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "ringo"))

(defn ringo [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' ringo project.")
    (->files data
      ["project.clj" (render "project.clj" data)]
      ["api/{{sanitized}}/db.clj" (render "db.clj" data)]
      ["api/{{sanitized}}/router.clj" (render "router.clj" data)]
      ["api/{{sanitized}}/server.clj" (render "server.clj" data)]
      ["api/{{sanitized}}/utils.clj" (render "utils.clj" data)]
      ["ui/{{sanitized}}/components.cljs" (render "components.cljs" data)]
      ["ui/{{sanitized}}/client.cljs" (render "client.cljs" data)]
      ["ui/{{sanitized}}/utils.cljs" (render "utils.cljs" data)]
      ["design/less/layout.less" (render "layout.less" data)]
      ["resources/public/index.html" (render "index.html" data)]
      ["bower.json" (render "bower.json" data)]
      ["project.clj" (render "project.clj" data)]
      ["README.md" (render "README.md" data)]
      ["LICENSE" (render "LICENSE" data)]
      ["Procfile" (render "Procfile" data)]
      ["system.properties" (render "system.properties" data)]
      [".gitignore" (render "gitignore" data)])))
