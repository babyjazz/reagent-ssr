(ns reagent-serverside.reframe.event
  (:require
   [re-frame.core :as rf]))

(rf/reg-event-db ; Like reducer
 :set-api
 (fn [db [_ value]]
   (assoc db :api (into (get db :api) value))))