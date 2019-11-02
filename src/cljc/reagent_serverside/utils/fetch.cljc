(ns reagent-serverside.utils.fetch
  #?(:cljs
     (:require [clojure.walk :refer [keywordize-keys]]
               [re-frame.core :as rf]
               [reagent-serverside.utils.fetch-status :as status]))
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
         (do
           (rf/dispatch [:set-api {(get options :dispatch) {:status status/request}}])
           (-> (js/fetch url (clj->js {:method (get options :method)
                                       :body (let [body (get options :body)]
                                               (when-not (nil? body)
                                                 (js/JSON.stringify (clj->js (get options :body)))))
                                       :headers (merge {"Content-Type" "application/json"}
                                                       (get options :headers))}))
               (.then (fn [resp]
                        (.json resp)))
               (.then (fn [resp]
                        (let [resp (keywordize-keys (js->clj resp))]
                          (rf/dispatch [:set-api {(get options :dispatch) {:status status/success
                                                                           :data (get resp :data)}}]))))
               (.catch (fn [err]
                         (let [err (keywordize-keys (js->clj err))]
                           (rf/dispatch [:set-api {(get options :dispatch) {:status status/failure
                                                                            :message (get err :message)}}]))))))
         (do
           (set! (.-innerText (.getElementById js/document "mount")) "")
           (let [fetched-data (.-innerText (.getElementById js/document "data"))]
             (rf/dispatch [:set-api {(get options :dispatch) {:status status/success
                                                              :data (cljs.reader/read-string fetched-data)}}])))))))