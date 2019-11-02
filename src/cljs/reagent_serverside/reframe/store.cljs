(ns reagent-serverside.reframe.store
  (:require
   [re-frame.core :as rf]
   [reagent-serverside.utils.fetch-status :as status]
   [reagent-serverside.pages.home :refer [home-page]]
   [reagent-serverside.router.router :refer [initial-page]]))

; Initial global state with re-frame; Like using redux
; -------------------------------
(rf/reg-event-db
 :initialize
 (fn []
   {:router initial-page
    :api {}}))

; subscribe key inside :initialize
; -------------------------------
(rf/reg-sub :api (fn [db] (:api db)))
(rf/reg-sub :router (fn [db] (:router db)))