(ns reagent-serverside.pages.about
  (:require [accountant.core :as route]
            [reagent.core :as r :refer [atom]]
            [reagent-serverside.utils.fetch :refer [fetch]]))

(defn about-page []
  (r/create-class
   {:component-did-mount
    (fn []
      (fetch "http://localhost:5000/post"
             {:method "post"
              :body {:name "about cljs"}}))
    :reagent-render
    (fn []
      [:<>
       [:h1 "about"]
       [:button {:on-click #(route/navigate! "/")} "go to home"]])}))