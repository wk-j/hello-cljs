(ns hello-es6.core
    (:require [cljs.nodejs :as nodejs]
              [js.hello :as hello]))
  
(nodejs/enable-util-print!)
  
(defn -main [& args]
  (hello/sayHello))
  
(set! *main-cli-fn* -main)