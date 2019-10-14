(ns reagent-serverside.home
  #?(:cljs
     (:require [reagent.core :as reagent :refer [atom]]))
  #?(:clj
     (:require [org.httpkit.client :as http])))

(def a (atom {:a 100 :b 200}))

(defn home-page []
  {:init (fn []
           #?(:clj
              (let [{:keys [status headers body error] :as resp}
                    @(http/request {:url "http://www.mocky.io/v2/5da358e52f00006b008a0789"
                                    :method :get
                                    :insecure? true})]
                resp))
           #?(:cljs
              (js/fetch "https://reqres.in/api/users?delay=5"
                        {:finally (fn [res]
                                    (prn (str "res: ")))})))
   :render #(str @a)})