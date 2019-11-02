(ns reagent-serverside.router.router
  (:require-macros [secretary.core :refer [defroute]])
  (:require
   [re-frame.core :as rf]
   [reagent.core :as r :refer [atom]]
   [secretary.core :as secretary]
   [reagent-serverside.pages.home :refer [home-page]]
   [reagent-serverside.pages.notfound :refer [notfound]]
   [reagent-serverside.pages.about :refer [about-page]]))

; initial page state
; -------------------------------
(def initial-page {:page home-page
                   :params {}})

; all routes define
; -------------------------------
(defroute "/" []
  (rf/dispatch [:set-router {:page home-page}]))
(defroute "/about" []
  (rf/dispatch [:set-router {:page about-page}]))
(defroute #"/about/(\w+)" [num]
  (rf/dispatch [:set-router {:page about-page
                             :params {:num num}}]))
(defroute #"/about/(\w+)/(\w+)" [num char]
  (rf/dispatch [:set-router {:page about-page
                             :params {:num num :char char}}]))
(defroute #"/(\w+)" []
  (rf/dispatch [:set-router {:page notfound}]))