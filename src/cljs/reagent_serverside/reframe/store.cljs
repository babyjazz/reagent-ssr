(ns reagent-serverside.reframe.store
  (:require
   [re-frame.core :as rf]
   [reagent-serverside.utils.fetch-status :as status]))

; Initial global state with re-frame; Like using redux
(rf/reg-event-db
 :initialize
 (fn []
   {:api {}}))

; subscribe key inside :initialize
(rf/reg-sub :api (fn [db] (:api db)))