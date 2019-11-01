(defproject reagent-serverside "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring-server "0.4.0"]
                 [org.clojure/core.async  "0.4.500"]
                 [reagent "0.8.1"]
                 [reagent-forms "0.5.13"]
                 [reagent-utils "0.3.3"]
                 [http-kit "2.3.0"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [prone "0.8.2"]
                 [org.clojure/data.json "0.2.6"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]
                 [environ "1.0.1"]
                 [org.clojure/clojurescript "1.10.520" :scope "provided"]
                 [secretary "1.2.3"]
                 [venantius/accountant "0.2.5"]]

  :plugins [[lein-environ "1.0.1"]
            [lein-cljsbuild "1.1.7"]
            [lein-asset-minifier "0.2.2"]
            [lein-npm "0.6.2"]
            [lein-ring "0.12.5"]]

  :npm {:package {:scripts {:build "webpack -p"}}
        :dependencies [["@cljs-oss/module-deps" "1.1.1"]
                       [browserify "15.1.0"]
                       [webpack "4.8.3"]]
        :devDependencies [[webpack-cli "3.0.3"]]}

  :ring {:handler reagent-serverside.handler/app
         :uberwar-name "reagent-serverside.war"}

  :min-lein-version "2.5.0"

  :uberjar-name "reagent-serverside.jar"

  :main reagent-serverside.server

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :app :compiler :output-dir]
                                    [:cljsbuild :builds :app :compiler :output-to]]

  :source-paths ["src/clj" "src/cljc"]
  :resource-paths ["resources" "target/cljsbuild"]

  :minify-assets
  {:assets
   {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :cljsbuild {:builds {:app {:source-paths ["src/cljs" "src/cljc"]
                             :compiler {:output-to "target/cljsbuild/public/js/app.js"
                                        :output-dir "target/cljsbuild/public/js/out"
                                        :asset-path   "js/out"
                                        :optimizations :none
                                        :process-shim true
                                        :foreign-libs [{:file "resources/public/js/npm-deps.js"}]
                                                        ; :provides ["moment"] ; example
                                                        ; :global-exports {moment Moment}}] ; [momen Moment] ; name-to-export name-from-npm-deps
                                        :pretty-print  true}}}}

  :profiles {:dev {:repl-options {:init-ns reagent-serverside.repl}

                   :dependencies [[ring/ring-mock "0.3.0"]
                                  [ring/ring-devel "1.4.0"]
                                  [lein-figwheel "0.5.19"]
                                  [figwheel-sidecar "0.5.19"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [com.cemerick/piggieback "0.1.5"]
                                  [pjstadig/humane-test-output "0.7.0"]]

                   :source-paths ["env/dev/clj"]
                   :plugins [[lein-figwheel "0.5.19"]]

                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]

                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7002
                              :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
                              :css-dirs ["resources/public/css"]
                              :ring-handler reagent-serverside.handler/app}

                   :env {:dev true}

                   :cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]
                                              :compiler {:main "reagent-serverside.dev"
                                                         :source-map true}}}}}

             :uberjar {:hooks [minify-assets.plugin/hooks]
                       :prep-tasks ["compile" ["cljsbuild" "once"]]
                       :env {:production true}
                       :aot :all
                       :omit-source true
                       :cljsbuild {:jar true
                                   :builds {:app
                                            {:source-paths ["env/prod/cljs"]
                                             :compiler
                                             {:optimizations :advanced
                                              :pretty-print false}}}}}})
