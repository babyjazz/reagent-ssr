(ns reagent-serverside.home
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn home-page []
  (fetch "https://156165fc.ngrok.io/post"
         {:method "post"
          :body {:name "babyjazz only"}}))