(ns reagent-serverside.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent-serverside.home :refer [home-page]]
            [reagent-serverside.about :refer [about-page]]))

(def page (atom [home-page]))

(if (= js/window.location.pathname "/about")
  (do
    (js/console.log "ABOUTTTTTT")
    (swap! page (fn [_] [about-page])))
  (do
    (js/console.log "HOMEEE")
    (swap! page (fn [_] [home-page]))))

(defn mount-root []
  (reagent/render @page (.getElementById js/document "app")))

(defn init! []
  (mount-root))
