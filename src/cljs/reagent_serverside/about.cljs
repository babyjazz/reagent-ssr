(ns reagent-serverside.about)

(defn about-page []
  [:div
   [:h1 "about"]
   [:a {:href "/"} "go to home"]])