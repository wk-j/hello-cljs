(ns single-page.prod
  (:require
    [single-page.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
