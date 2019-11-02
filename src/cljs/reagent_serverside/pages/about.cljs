(ns reagent-serverside.pages.about
  (:require
   [re-frame.core :as rf]
   [accountant.core :as route]
   [reagent.core :as r :refer [atom]]
   [reagent-serverside.utils.fetch :refer [fetch]]
   [reagent-serverside.about :as about]))

(defn about-page []
  (r/create-class
   {:component-did-mount
    (fn []
      (about/initial-data))
    :reagent-render
    (fn [params]
      (let [api-data (-> @(rf/subscribe [:api]) :about-data)] 
       [:<>
        [:h1 "about"]
        [:p "url param: " (get params :num)]
        [:p "second url param: " (get params :char)]
        [:<>
         [:span "fetched data: "]
         [:span {:style {:font-size "0.8em"}}
          (if (-> api-data :status :request?)
            "Loading..."
            (-> api-data :data))]]
        [:hr]
        [:p {:style {:font-size "0.7em"}}
         "This about page will get fetching data before js mount and fetch by url parameter. if no parameter, default body value to fetch is 3"]
        [:hr]
        [:p {:style {:font-size "0.7em"}}
         "Add global state in fetch.cljc for get fetched data in this page"]
        [:hr]
        [:button {:on-click #(route/navigate! "/about/11")} "go to about param"]
        [:button {:on-click #(route/navigate! "/about/11/message")} "go to about two param"]
        [:button {:on-click #(route/navigate! "/")} "go to home"]
        [:button {:on-click #(fetch "http://localhost:5000/post"
                                    {:method "post"
                                     :body {:name (or (get params :char)
                                                      (get params :num))}
                                     :dispatch :about-data})} "fetch post"]]))}))