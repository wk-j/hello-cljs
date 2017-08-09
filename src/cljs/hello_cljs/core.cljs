(ns hello-cljs.core)

(enable-console-print!)
(println "Hello world!")
(.write js/document "<h1>Hello Browser</h1>")