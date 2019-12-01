# reagent-ssr
Reagent with serverside rendering support reagent (npm module) and re-frame 
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
- If include external js but it doesn't work, do this following steps
    1. Place your external js file into resources/public/
    2. Add custom js and jquery into template.clj
    ```clj
       (include-js "/js/jquery.min.js")
       (include-js "/js/custom.js")
    ```
    3. Add this code to custom.js and include your external javascript here
    ```js
    $(document).ready(function() {
      $("<script/>", {
        type: "text/javascript",
        src: "/js/dashmix.core.min.js"
      }).appendTo("body");
      $("<script/>", {
        type: "text/javascript",
        src: "/js/dashmix.app.min.js"
      }).appendTo("body");
    });
    ```

### License
MIT
