(ns reagent-serverside.router.router
  (:require
   [compojure.core :refer [GET defroutes context]]
   [compojure.route :refer [not-found resources]]
   [reagent-serverside.home :as home]
   [reagent-serverside.about :as about]
   [reagent-serverside.router.template :refer [loading-page]]))

; all routes define
; -------------------------------
(defroutes routes
  (GET "/" [] (loading-page home/initial-data))
  (GET "/about" [] (loading-page about/initial-data))
  (GET "/about/:num" [num] (loading-page about/initial-data {:num num}))
  (GET "/about/:num/:char" [num char] (loading-page about/initial-data {:num num :char char}))
  (resources "/")
  (resources "/about")
  (resources "/about/:id")
  (not-found "Not Found"))