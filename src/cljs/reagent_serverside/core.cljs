(ns reagent-serverside.core
  (:import goog.history.Html5History)
  (:require [secretary.core :as secretary]
            [accountant.core :as accountant]
            [reagent.core :as r]
            [goog.events :as events]
            [re-frame.core :as rf]
            [reagent-serverside.reframe.store]
            [reagent-serverside.reframe.event]
            [reagent-serverside.router.router :refer [selected-page]]
            [goog.history.EventType :as EventType]))

; page renderer
; -------------------------------
(defn page []
  (when js/goog.DEBUG
    (accountant/dispatch-current!))
  (r/create-class
   {:component-did-mount
    (fn []
      (rf/dispatch [:initialize]))
    :reagent-render
    (fn []
      [(@selected-page :page) (@selected-page :params)])}))

; page navigation history
; -------------------------------
(defn hook-browser-navigation! []
  (doto (Html5History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

; javascript mount
; -------------------------------
(defn ^:export mount-root []
  (rf/dispatch [:initialize])
  (r/render [page] (.getElementById js/document "app")))

; initial javascript
; -------------------------------
(defn init! []
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (secretary/dispatch! path))
    :path-exists?
    (fn [path]
      (secretary/locate-route path))})
  (when-not js/goog.DEBUG
    (accountant/dispatch-current!))
  (mount-root))
