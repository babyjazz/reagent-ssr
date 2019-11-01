(ns reagent-serverside.home
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn initial-data [& [id]]
  "NOTE: Data will be writen in HTML before javascript will mount"
  (fetch "http://localhost:5000/post"
         {:method "post"
          :body {:name "jonathan"
                 :age 19
                 :sex "man"}
          :dispatch :user}))