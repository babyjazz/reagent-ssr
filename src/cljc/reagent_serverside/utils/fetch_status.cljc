(ns reagent-serverside.utils.fetch-status)

(def initial {:initial? true
              :request? false
              :success? false
              :failure? false})

(def request {:initial? false
              :request? true
              :success? false
              :failure? false})

(def success {:initial? false
              :request? false
              :success? true
              :failure? false})

(def failure {:initial? false
              :request? false
              :success? false
              :failure? true})