(ns reagent-serverside.router.router
  (:require-macros [secretary.core :refer [defroute]])
  (:require
   [reagent.core :as r :refer [atom]]
   [secretary.core :as secretary]
   [reagent-serverside.pages.home :refer [home-page]]
   [reagent-serverside.pages.about :refer [about-page]]))

; initial page state
; -------------------------------
(def selected-page (r/atom {:page home-page
                            :params {}}))

; all routes define
; -------------------------------
(defroute "/" []
  (swap! selected-page (fn [] {:page home-page
                               :params {}})))
(defroute "/about" []
  (swap! selected-page (fn [] {:page about-page
                               :params {}})))
(defroute #"/about/(\w+)" [num]
  (swap! selected-page (fn [] {:page about-page
                               :params {:num num}})))
(defroute #"/about/(\w+)/(\w+)" [num char]
  (swap! selected-page (fn [] {:page about-page
                               :params {:num num :char char}})))
