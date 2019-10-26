(ns reagent-serverside.core
  (:require [reagent.core :as r :refer [atom]]
            [reagent-serverside.pages.home :refer [home-page]]
            [reagent-serverside.pages.about :refer [about-page]]
            [secretary.core :as secretary :include-macros true]
            [accountant.core :as route]
            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType])
  (:import goog.History))

(def selected-page (atom home-page))

(defn page []
  (when js/goog.DEBUG
    (route/dispatch-current!))
  [@selected-page])

(secretary/defroute "/" []
  (reset! selected-page home-page))

(secretary/defroute "/about" []
  (reset! selected-page about-page))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     HistoryEventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn mount-root []
  (r/render [page] (.getElementById js/document "app")))

(defn init! []
  (route/configure-navigation!
   {:nav-handler
    (fn [path]
      (secretary/dispatch! path))
    :path-exists?
    (fn [path]
      (secretary/locate-route path))})
  (when-not js/goog.DEBUG
    (route/dispatch-current!))
  (mount-root))
