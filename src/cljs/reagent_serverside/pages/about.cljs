(ns reagent-serverside.pages.about
  (:require [accountant.core :as route]
            [reagent.core :as r :refer [atom]]
            [reagent-serverside.utils.fetch :refer [fetch]]
            [reagent-serverside.about :as about]))

(defn about-page []
  (r/create-class
   {:component-did-mount
    (fn []
      (about/initial-data))
    :reagent-render
    (fn []
      [:<>
       [:h1 "aboutss"]
       [:button {:on-click #(route/navigate! "/")} "go to home"]
       [:button {:on-click #(fetch "http://localhost:5000/post"
                                   {:method "post"
                                    :body {:name "bob bubble"}})} "fetch post"]])}))