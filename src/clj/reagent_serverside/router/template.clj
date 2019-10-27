(ns reagent-serverside.router.template
  (:require
   [environ.core :refer [env]]
   [hiccup.page :refer [include-js include-css]]
   [hiccup.core :refer [html]]))

; html template
; -------------------------------
(defn loading-page [initial-page & [params]]
  (let [data (initial-page params)]
    (html
     [:html
      [:head
       [:meta {:charset "utf-8"}]
       [:meta {:name "viewport"
               :content "width=device-width, initial-scale=1"}]
       (include-css (if (env :dev) "css/site.css" "css/site.min.css"))]
      [:body
       [:div#app "loading"]
       [:script#data (str data)]
       [:script#mount (when-not (nil? data) "mounted")]
       (include-js "/js/app.js")]])))