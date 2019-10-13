(ns reagent-serverside.pages.home
  (:require [reagent.core :as r :refer [atom]]
            [reagent-serverside.home :refer [a]]
            [clojure.walk :refer [keywordize-keys]]
            [ajax.core :refer [GET]]))

(def s (atom nil))
(comment
  (GET "https://reqres.in/api/users/2"  {:handler (fn [res]
                                                    (prn (keywordize-keys res))
                                                    (reset! s res))}))

(defn home-page []
  [:div
   [:h1 (str "home" (get @a :a))]
   [:a {:href "/about"} "go to about"]])