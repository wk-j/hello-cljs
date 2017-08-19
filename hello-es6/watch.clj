(require '[cljs.build.api :as b])

(b/watch "src"
  {:output-to    "main.js"
   :output-dir   "out"
   :main         'hello-es6.core
   :target       :nodejs
   :foreign-libs [{:file "src"
                   :module-type :es6}] ;; or :commonjs / :amd
   :verbose      true})