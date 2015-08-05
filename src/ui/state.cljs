(ns ui.state
  (:require [om.core :as om]
            [om.dom :as dom]))

(def characters (into [] "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"))

(defn- rand-char []
  (nth characters (rand-int (count characters))))

(defn- rand-word []
  (apply str (take (inc (rand-int 5)) (repeatedly rand-char))))

(defonce app-state
  (atom {:text "Hello Mala."
         :devices {:all []}
         :chart {:data []}
         :words (into []
                      (map (fn [w i] {:index i :word w :count (count w)})
                           (sort (into [] (take 100 (repeatedly rand-word))))
                           (range)))
         :menu [{:name "Search" :path "/typeahead"}
                {:name "Graphs" :path "/graph"}
                {:name "Layout" :path "/layout"}
                {:name "Design" :path "/mesh"}]
         :layout {:rows ["an" "engineer" "without a" "designer" "is a" "parking lot"]
                  :columns ["a" "designer" "without an" "engineer" "is an art gallery"]}
         :content {:title "Thinking Clojurescript"
                   :issue "Issue #1"
                   :publisher "The Facjure Review"
                   :logo "http://placekitten.com/404/404"
                   :editorial "Object Oriented programming as a paradigm has many real
                   benefits but one of the worst plagues it has inflicted on
                   programming culture is obscuring data. Functional programming
                   is not a silver bullet but its emphasis on unadorned data is
                   a guiding light. No models."
                   :interviews ["Bruce Hauman, " "David Nolen, " "Joel Holdbrooks"]
                   :developer-pic "http://placekitten.com/404/404"
                   :essays ["“A meditation in Edn” by Priyatam Mudivarti"]
                   :quotes {:nolen-tweet "“We create things with our tools, and
                    our tools have consequences”"
                            :dev-quote "The Functional final frontier: Clojurescript"}}}))

(def history
  (atom [@app-state]))

(add-watch
 app-state :history
 (fn [_ _ old new]
   #_(println old new history)
   (when-not (= (last @history) new)
     (swap! history conj new))))

(defn undo! []
  (when (> (count @history) 1)
    (swap! history pop)
    (reset! app-state (last @history))))
