(ns reagent-serverside.pages.about
  (:require [accountant.core :as route]
            [reagent.core :as r :refer [atom]]
            [reagent-serverside.about :as a]))

(defn about-page []
  (r/create-class
   {:component-did-mount
    (fn []
      (a/about-page)
      (prn "about"))
    :reagent-render
    (fn []
      [:<>
       [:h1 "about"]
       [:button {:on-click #(route/navigate! "/")} "go to home"]])}))