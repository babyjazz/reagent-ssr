(ns reagent-serverside.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.history.Html5History)
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [accountant.core :as accountant]
            [reagent-serverside.pages.home :refer [home-page]]
            [reagent-serverside.pages.about :refer [about-page]]
            [goog.history.EventType :as EventType]
            [reagent.core :as reagent]))

(def selected-page (reagent/atom {:page home-page
                                  :params {}}))

(defn page []
  (when js/goog.DEBUG
    (accountant/dispatch-current!))
  [(@selected-page :page) (@selected-page :params)])

(defn hook-browser-navigation! []
  (doto (Html5History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

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
                                :params {:num num
                                         :char char}})))

(defn ^:export mount-root []
  (reagent/render [page]
                  (.getElementById js/document "app")))

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
