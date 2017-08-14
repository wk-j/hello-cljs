;; Intro to string
(= "HELLO WORLD" (.toUpperCase "hello world"))

;; Intro to lists
(= (list :a :b :c) '(:a :b :c))

;; List conj
(= (list 1 2 3 4) (conj '(2 3 4) 1))
(= (list 1 2 3 4) (conj '( 3 4) 2 1))

;; Intro to vector
(= [:a :b :c] (vec '(:a :b :c)) (vector :a :b :c))

;; Vector conj
(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))

;; Intro to set
(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

;; Set conj
(= #{1 2 3 4} (conj #{1 4 3} 2))

;; Intro to map
((hash-map :a 10, :b 20, :c 30) :b)
(= 20 ((hash-map :a 10, :b 20, :c 30) :b)) 
(= 20 (:b {:a 10 :b 20 :c 30}))

;; Maps conj
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
