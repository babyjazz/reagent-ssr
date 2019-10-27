(ns reagent-serverside.core
  (:require [secretary.core :as secretary]
            [accountant.core :as accountant]
            [reagent.core :as r]
            [reagent-serverside.router.router :refer [selected-page]]))


(defn page []
  (when js/goog.DEBUG
    (accountant/dispatch-current!))
  [(@selected-page :page) (@selected-page :params)])


(defn ^:export mount-root []
  (r/render [page] (.getElementById js/document "app")))

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
