(ns reagent-serverside.pages.home
  (:require [reagent.core :as r :refer [atom]]
            [clojure.walk :refer [keywordize-keys]]))


(defn home-page []
  [:div
   [:h1 (str "home")]
   [:a {:href "/about"} "go to about"]])