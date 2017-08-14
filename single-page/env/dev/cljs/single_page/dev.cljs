(ns ^:figwheel-no-load single-page.dev
  (:require
    [single-page.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
