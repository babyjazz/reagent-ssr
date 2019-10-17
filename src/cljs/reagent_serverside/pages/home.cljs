(ns reagent-serverside.pages.home
  (:require [reagent.core :as r :refer [atom]]
            [reagent-serverside.utils.fetch :refer [fetch]]))

(defn home-page []
  [:div
   [:h1 (str "home")]
   [:a {:href "/about"} "go to about"]
   [:button {:on-click #(fetch "http://localhost:5000/post"
                               {:method "post"
                                :body {:name "babyjazz only"}})}
    "fetch api"]])