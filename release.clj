(require 'cljs.build.api)

(cljs.build.api/build "src"
  {:build-to "out/main.js"
   :optimizations :advanced})

(System/exit 0)
