(ns reagent-serverside.about
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn about-page []
  (fetch "https://reqres.in/api/users/2"
         {:method "post"
          :body {:name "test"
                 :salary "123"
                 :age "23"}}))
