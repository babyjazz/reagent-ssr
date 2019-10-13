(ns reagent-serverside.home
  #?(:cljs
     (:require [reagent.core :as reagent :refer [atom]])))

(def items (atom nil))
(def a (atom 100))

(defn item-list [items]
  [:ul
   (for [item items]
     ^{:key item}
     [:li item])])

(defn add-item-button [items]
  [:div
   [:button
    {:on-click #(swap! items conj (count @items))}
    "add item"]
   [:a {:href "/about"} "about"]])

(defn home-page []
  [:div
   [:h2 "Welcome to reagent-serverside babyjazz"]
   [:h3 @a]
   [:a {:href "/about"} "go to about"]
   [add-item-button items]
   [item-list @items]])
