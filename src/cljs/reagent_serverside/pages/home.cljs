(ns reagent-serverside.pages.home
  (:require [reagent.core :as r :refer [atom]]
            [reagent-serverside.utils.fetch :refer [fetch]]
            [accountant.core :as route]
            [reagent-serverside.home :as h]))

(def a (atom 1))

(defn home-page []
  (r/create-class
   {:component-did-mount
    (fn []
      (h/home-page))
    :reagent-render
    (fn []
      [:<>
       [:h1 (str "home" @a)]
       [:button {:on-click #(route/navigate! "/about")} "go to about"]
       [:button {:on-click #(fetch "http://localhost:5000/post"
                                   {:method "post"
                                    :body {:name "babyjazz only"}})}
        "fetch api"]])}))