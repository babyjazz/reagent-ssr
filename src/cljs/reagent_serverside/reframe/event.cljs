(ns reagent-serverside.reframe.event
  (:require
   [re-frame.core :as rf]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [reagent-serverside.router.router :refer [initial-page]]))

; dispatch events
; -------------------------------
(rf/reg-event-db
 :set-api
 (fn-traced [db [_ value]]
            (assoc db :api (into (get db :api) value))))

(rf/reg-event-db
 :set-router
 (fn-traced [db [_ new-page]]
            (assoc db :router (into (get db :router) (into initial-page new-page)))))