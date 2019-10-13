(ns reagent-serverside.home
  #?(:cljs
     (:require [reagent.core :as reagent :refer [atom]])))

(def a (atom {:a 100 :b 200}))

(defn home-page []
  (str @a))