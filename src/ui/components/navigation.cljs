(ns ui.components.navigation
  (:require [clojure.string :as str]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [secretary.core :as sec]
            [ui.state :as state]
            [sablono.core :as html :refer-macros [html]]
            [ui.utils :as utils]
            [goog.events :as events]))

(def header-snippet
  [:header
   [:h1 "Mala"]
   [:h3 "A User Interface template in Clojurescript."]])

(def footer-snippet
  [:footer
   [:a {:href "https://twitter.com/priyatam"} "@priyatam"]])

(defn menu [app _]
  (om/component
    (dom/div nil
      (apply dom/ul nil
             (for [[link-name url] (-> app :navigation :links)]
               (dom/li nil
                 (if (= url (-> app :navigation :current-url))
                   link-name
                   (dom/a #js {:href url
                               :onClick (fn [_]
                                          (om/update! app
                                                      [:navigation :current-url]
                                                      url))}
                     link-name))))))))

(defn item-view [{:keys [active path name]} owner]
  (reify
    om/IRender
    (render [this]
      (html
       [:li {:class (if active "active" "")}
        [:a {:href (str "#" path)} name]]))))

(defn view [app owner]
  (reify
    om/IRender
    (render [this]
      (html
       [:section {:class "home"}
        header-snippet
        [:nav
         [:ul
          (om/build-all item-view (:menu app))]]]))))
