(ns reagent-serverside.home
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn home-page []
  (fetch "http://localhost:5000/post"
         {:method "post"
          :body {:data "home cljc"}}))