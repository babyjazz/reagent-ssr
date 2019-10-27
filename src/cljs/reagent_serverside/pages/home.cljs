(ns reagent-serverside.pages.home
  (:require [reagent.core :as r :refer [atom]]
            [reagent-serverside.utils.fetch :refer [fetch]]
            [accountant.core :as route]
            [reagent-serverside.home :as home]))

(defn home-page []
  (r/create-class
   {:component-did-mount
    (fn []
      (home/initial-data))
    :reagent-render
    (fn []
      [:<>
       [:h1 (str "homes")]
       [:button {:on-click #(route/navigate! "/about")} "go to about"]
       [:button {:on-click #(route/navigate! "/about/10")} "go to about with param"]
       [:button {:on-click #(fetch "http://localhost:5000/post/some message"
                                   {:method "get"})}
        "fetch api"]])}))