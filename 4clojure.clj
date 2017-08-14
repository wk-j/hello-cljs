;; 1 Nothing but the truth
(= true true)

;; 2 Simple match
(= (- 10 (* 2 3)) 4)

;; 3 Intro to string
(= "HELLO WORLD" (.toUpperCase "hello world"))

;; 4 Intro to lists
(= (list :a :b :c) '(:a :b :c))

;; 5 List conj
(= (list 1 2 3 4) (conj '(2 3 4) 1))
(= (list 1 2 3 4) (conj '( 3 4) 2 1))

;; 6 Intro to vector
(= [:a :b :c] (vec '(:a :b :c)) (vector :a :b :c))

;; 7 Vector conj
(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))

;; 8 Intro to set
(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

;; 9 Set conj
(= #{1 2 3 4} (conj #{1 4 3} 2))

;; 10 Intro to map
((hash-map :a 10, :b 20, :c 30) :b)
(= 20 ((hash-map :a 10, :b 20, :c 30) :b)) 
(= 20 (:b {:a 10 :b 20 :c 30}))

;; 11 Maps conj
(= {:a 1 :b 2 :c 3} (conj {:a 1} {:b 2} [:c 3]))

;; Intro to Sequences
(= 3 (first '(3 2 1)))
(= 3 (second [2 3 1]))
(= 3 (last (list 1 2 3)))

;; Sequences rest
(= '(20 30 40) (rest [10 20 30 40]))

;; Intro to function
(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

;; Double down
(= (#(* % 2) 2) 4)
(= (#(* % 2) 3) 6)
(= (#(* % 2) 11) 22)
(= (#(* % 2) 7) 14)

; Hello world
(= (#(str "Hello, " % "!") "Dave") "Hello, Dave!")
(= (#(str "Hello, " % "!") "Jenn") "Hello, Jenn!")
(= (#(str "Hello, " % "!") "Rhea") "Hello, Rhea!")

;; Sequences map
(= '(6 7 8) (map #(+ % 5) '(1 2 3)))

;; Sequence filter
(= '(6 7) (filter #(> % 5) '(3 4 5 6 7)))

;; Intro to reduce
(= 15 (reduce + [1 2 3 4 5]))
(= 0 (reduce + []))
(= 6 (reduce + 1 [2 3]))

;; A nil key
(true?  (#(if (contains? %2 %1) (= nil (get %2 %1)) false) :a {:a nil :b 2}))
(false?  (#(if (contains? %2 %1) (= nil (get %2 %1)) false) :b {:a nil :b 2}))
(false?  (#(if (contains? %2 %1) (= nil (get %2 %1)) false) :c {:a nil :b 2}))


(defn nil-key? [k m]
  (and (contains? m k)
       (nil? (m k))))
(nil-key? :a {:a nil})


;; Logical falsity and truth
(= 1 (if-not false 1 0))
(= 1 (if-not nil 1 0))
(= 1 (if true 1 0))
(= 1 (if [] 1 0))
(= 1 (if [0] 1 0))
(= 1 (if 0 1 0))
(= 1 (if 1 1 0))

;; Subset and superset
(clojure.set/superset? #{1 2 3} #{2})
(clojure.set/subset? #{1} #{1 2 3})
(clojure.set/superset? #{1 2 3} #{1 2})
(clojure.set/subset? #{1 2} #{1 2 3})

;; 52 Intro to destructuring
(= [2 4] (let [[a b c d e] [ 0 1 2 3 4]] [c e]))


;; Last element
(= (last [1 2 3 4 5]) 5)
(= (last '(5 4 3)) 3)
(= (last ["b" "c" "d"]) "d")

;; Penultimate element
(= (#(->> % reverse rest first) (list 1 2 3 4 5)) 4)
(= (#(->> % reverse rest first) ["a" "b" "c"]) "b") 
(= (#(->> % reverse rest first) [[1 2] [3 4]]) [1 2])

;; Nth element
(= (#(nth %1 %2) '(4 5 6 7) 2) 6)
(= (#(nth %1 %2) [:a :b :c] 0) :a)
(= (#(nth %1 %2) [1 2 3 4] 1) 2)
(= (#(nth %1 %2) '([1 2] [3 4] [5 6]) 2) [5 6]) 

;; Count a sequence
(= (#(->> % count) '(1 2 3 3 1)) 5)
(= (#(->> % count) "Hello World") 11)
(= (#(->> % count) [[1 2] [3 4]  [5 6]]) 3)
(= (#(->> % count) '(13)) 1) 
(= (#(->> % count) '(:a :b :c)) 3)

;; Sum it all up
(= (reduce + [1 2 3]) 6)
(= (reduce + (list 0 -2 5 5)) 8)
(= (reduce + #{4 2 1}) 7)
(= (reduce + '(0 0 -1)) -1)
(= (reduce + '(1 10 3)) 14)

;; 25 Find the odd numbers
(= (filter odd? #{1 2 3 4 5}) '(1 3 5))

;; 26 Fibonacci sequence
(def fib-seq (lazy-cat [1 1] (map + (rest fib-seq) fib-seq)))
(take 5 fib-seq)

;; 33 Replicate a sequence
(= (#(mapcat (partial repeat %2) %1) [1 2 3] 2) '(1 1 2 2 3 3))

;; 34 Implement range
(defn positive-numbers [n] (lazy-seq (cons n (positive-numbers (inc n)))))
(take 5 (positive-numbers 1))

(#(take (- %2 %1) 
    ((fn pn [n]
           (lazy-seq (cons n (pn (+ n 1)))))
     %1))    
 1 4)


;; 35 Local binding
(= 7 (let [x 5] (+ 2 x)))

;; 36 Let it be
(= 10 (let [x 7 y 3 z 1] (+ x y)))


;; 65 Black box testing


;; 156 Map default
(interleave [1 2 3 4])
(interleave [1 2 3 4] (repeat [1 1]))
;;
(= (#(apply hash-map (interleave %2 (repeat %))) 0 [:a :b :c]) {:a 0 :b 0 :c 0})
(= (#(apply hash-map (interleave %2 (repeat %))) "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})
(= (#(apply hash-map (interleave %2 (repeat %))) [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})


(defn df [v ks]
  (zipmap ks (repeat v)))

(df 0 [1 2 3])

lein repl
(exit)