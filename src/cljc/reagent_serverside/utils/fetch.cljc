(ns reagent-serverside.utils.fetch
  #?(:cljs
     (:require [clojure.walk :refer [keywordize-keys]]))
  #?(:clj
     (:require [org.httpkit.client :as http]
               [clojure.data.json :as json]
               [clojure.walk :refer [keywordize-keys]])))

(def method-map {"get" :get
                 "post" :post
                 "patch" :patch
                 "put" :put
                 "delete" :delete})

(defn fetch [url options]
  #?(:clj (let [resp
                @(http/request {:url url
                                :method (get method-map (get options :method))
                                :headers {"Content-Type" "application/json"}
                                :body   (let [body (get options :body)]
                                          (when-not (nil? body)
                                            (when (map? body)
                                              (json/write-str (get options :body)))))
                                :insecure? true})]
            (let [{:keys [body status error]} resp]
              (if-not error
                (let [body (keywordize-keys (json/read-str body))]
                  (get body :data))))))
  #?(:cljs
     (let [mount (.-innerText (.getElementById js/document "mount"))]
       (if (empty? mount)
         (-> (js/fetch url (clj->js {:method (get options :method)
                                     :body (let [body (get options :body)]
                                             (when-not (nil? body)
                                               (js/JSON.stringify (clj->js (get options :body)))))
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
                         err))))
         (set! (.-innerText (.getElementById js/document "mount")) "")))))