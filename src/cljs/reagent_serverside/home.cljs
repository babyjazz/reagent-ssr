(ns reagent-serverside.home)

(defn home-page []
  [:div
   [:h1 "home"]
   [:a {:href "/about"} "go to about"]])