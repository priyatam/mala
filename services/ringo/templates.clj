(ns ringo.templates
  (:require [compojure.route :as route])
  (:use (hiccup core page)))

(defn tweets []
  "Simple HTML page for rendering tweets. Includes the css and js for websockets."
  (html5
   [:head
    (include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css")]
   [:body
    [:div.container
     [:div.row
      [:div.col-md-8
        [:p [:h1#headline "comment"]]
        [:form
          [:input#message {:type "text"}]
          [:input.btn-lg {:type "submit"}]]
        [:div#messages]]]]
    (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js")
    (include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js")
    (include-js "/js/web_socket.js")
    (include-js "/js/app.js")]))
