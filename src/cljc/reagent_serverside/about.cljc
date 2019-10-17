(ns reagent-serverside.about
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn about-page []
  (fetch "http://localhost:5000"
         {:method "get"}))
