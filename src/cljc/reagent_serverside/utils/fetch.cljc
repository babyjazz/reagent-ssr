(ns reagent-serverside.utils.fetch
  #?(:cljs
     (:require [reagent.core :as reagent :refer [atom]]
               [clojure.walk :refer [keywordize-keys]]))
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
                                :body   (let [body (get options :body)]
                                          (when-not (nil? body)
                                            (when (map? body)
                                              (json/write-str (get options :body)))))
                                :insecure? true})]
       (let [{:keys [status headers body error]} resp]
         (let [body (json/read-str body)]
           (get body "data")))))
  #?(:cljs
     (-> (js/fetch url (clj->js {:method (get options :method)
                                 :body (let [body (get options :body)]
                                         (when-not (nil? body)
                                           (JSON.stringify (clj->js (get options :body)))))
                                 :headers {"Content-Type" "application/json"}}))
         (.then (fn [resp]
                  (.json resp)))
         (.then (fn [resp]
                   (let [resp (keywordize-keys (js->clj resp))]
                     (prn resp)
                     resp)))
         (.catch (fn [err]
                   (let [err (keywordize-keys (js->clj err))]
                     (prn err)
                     err))))))
