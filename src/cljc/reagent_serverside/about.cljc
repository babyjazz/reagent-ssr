(ns reagent-serverside.about
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn initial-data []
  "NOTE: Data will be writen in HTML before javascript will mount"
  (fetch "http://localhost:5000/post"
         {:method "post"
          :body {:id 3}}))
