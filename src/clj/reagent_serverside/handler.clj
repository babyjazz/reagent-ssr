(ns reagent-serverside.handler
  (:require
   [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
   [hiccup.page :refer [include-js include-css]]
   [prone.middleware :refer [wrap-exceptions]]
   [ring.middleware.reload :refer [wrap-reload]]
   [environ.core :refer [env]]
   [reagent-serverside.router.router :refer [routes]]))


(defn react-id-str [react-id]
  (assert (vector? react-id))
  (str "." (clojure.string/join "." react-id)))

(defn set-react-id [react-id element]
  (update element 1 merge {:data-reactid (react-id-str react-id)}))

(defn normalize [component]
  (if (map? (second component))
    component
    (into [(first component) {}] (rest component))))

(defn render
  ([component] (render [0] component))
  ([id component]
   (cond
     (fn? component)
     (render (component))
     (not (coll? component))
     component
     (coll? (first component))
     (map-indexed #(render (conj id %1) %2) component)
     (keyword? (first component))
     (let [[tag opts & body] (normalize component)]
       (->> body
            (map-indexed #(render (conj id %1) %2))
            (into [tag opts])
            (set-react-id id)))
     (fn? (first component))
     (render id (apply (first component) (rest component))))))

(def app
  (let [handler (wrap-defaults #'routes  site-defaults)]
    (if (env :dev) (-> handler wrap-exceptions wrap-reload) handler)))
