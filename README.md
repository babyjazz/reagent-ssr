# reagent-ssr
Reagent with serverside rendering support reagent (npm module) and re-frame (have to switch branch)
Run on java server


### Run development
``` rlwrap lein figwheel ```
server is on http://localhost:3449
or 
``` lein cljsbuild auto ```
``` lein ring server-headless ```
``` rlwrap lein figwheel ```
server is on http://localhost:3000


### Build production
``` lein uberjar ```


### Run production
``` java -jar {{built jar file}} ```


### Note
- follow how to integrate with npm module here https://gist.github.com/babyjazz/2ed6d1c3c052dfb953a182b06ca90271
- If figwheel show error choice, You can remove error choice by remove all comments in project.clj

### License
MIT
