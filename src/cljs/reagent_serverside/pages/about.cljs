(ns reagent-serverside.pages.about
  (:require [accountant.core :as route]))

(defn about-page []
  [:<>
   [:h1 "about"]
   [:button {:on-click #(route/navigate! "/")} "go to home"]])