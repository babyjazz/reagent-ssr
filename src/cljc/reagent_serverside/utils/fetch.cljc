(ns reagent-serverside.utils.fetch
  #?(:cljs
     (:require [reagent.core :as reagent :refer [atom]]))
  #?(:clj
     (:require [org.httpkit.client :as http]
               [clojure.data.json :as json])))

(def method-map {"get" :get
                 "post" :post
                 "patch" :patch
                 "put" :put
                 "delete" :delete})

(defn fetch [url options]
  #?(:clj
     (let [resp @(http/request {:url url
                                :method (get method-map (get options :method))
                                :headers {"Content-Type" "application/json"}
                                :body   (let [body (get options :body)]
                                          (when-not (nil? body)
                                            (when (map? body)
                                              (json/write-str (get options :body)))))
                                :insecure? true})]
       (let [{:keys [status headers body error]} resp]
         (let [body (json/read-str body)]
           (get body "data"))))))
