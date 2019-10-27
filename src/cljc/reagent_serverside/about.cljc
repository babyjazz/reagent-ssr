(ns reagent-serverside.about
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn initial-data [& [params]]
  "NOTE: Data will be writen in HTML before javascript will mount"
  (prn params)
  (fetch "http://localhost:5000/post"
         {:method "post"
          :body {:id (or params 3)}}))
