(ns reagent-serverside.home
  (:require [reagent-serverside.utils.fetch :refer [fetch]]))

(defn home-page []
  (fetch "https://a425b464.ngrok.io/post"
         {:method "post"
          :body {:key "Y"}}))