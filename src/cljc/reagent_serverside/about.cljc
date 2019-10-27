(ns reagent-serverside.about
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn initial-data [& [params]]
  "NOTE: Data will be writen in HTML before javascript will mount"
  (fetch "http://localhost:5000/post"
         {:method "post"
        ;   :headers {"authorization" "bearer sdfjsdfj"}
          :body {:id (or (get params :char)
                         (or (get params :num) 3))}}))
