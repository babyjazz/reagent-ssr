(ns reagent-serverside.about
  #?(:cljs
     (:require [reagent.core :as reagent :refer [atom]]
               [ajax.core :refer [GET]]))
  #?(:clj
     (:require [org.httpkit.client :as http])))

(def a (atom {:a 1 :b 3}))

(defn about-page []
  {:init (fn []
           #?(:clj
              (let [resp
                    @(http/request {:url "http://www.mocky.io/v2/5da358e52f00006b008a0789"
                                    :method :get
                                    :insecure? true})]
                resp))
           #?(:cljs
              (GET "https://reqres.in/api/users?delay=5"
                {:finally (fn []
                            (prn (str "res: ")))})))
   :render #(str @a)})