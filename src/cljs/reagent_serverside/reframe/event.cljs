(ns reagent-serverside.reframe.event
  (:require
   [re-frame.core :as rf]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))


(rf/reg-event-db ; Like reducer
 :set-api
 (fn-traced [db [_ value]]
   (assoc db :api (into (get db :api) value))))