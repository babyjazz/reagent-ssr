(ns reagent-serverside.reframe.event
  (:require
   [re-frame.core :as rf]
   [reagent-serverside.router.router :refer [initial-page]]))

; dispatch events
; -------------------------------
(rf/reg-event-db
 :set-api
 (fn [db [_ value]]
            (assoc db :api (into (get db :api) value))))

(rf/reg-event-db
 :set-router
 (fn [db [_ new-page]]
            (assoc db :router (into (get db :router) (into initial-page new-page)))))