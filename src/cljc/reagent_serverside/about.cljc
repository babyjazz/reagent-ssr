(ns reagent-serverside.about
  #?(:cljs
     (:require [reagent.core :as reagent :refer [atom]])))

(def items (atom nil))
(def a (atom {:a 1 :b 3}))

(defn about-page []
  (str @a))
