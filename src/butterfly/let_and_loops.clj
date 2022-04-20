(ns butterfly.let-and-loops)

(let [x 3] (println x) (+ x 3) (def y 2) (* x y))
;; => 3
;; 6

(let [[first-value second-value & rest-values] [1 2 3 4 5 6]] (str "Second value " second-value))
;; "Second value 2" 

;; loop

(loop [iteration 0]
  (println "Hello!")
  (if (> iteration 5)
    (println "End")
    (recur (inc iteration))))

;; loop creates a local binding(s) with an implicit let
;; in the above example, this was `iteration`
;; recur transfers control back to the inner-most `loop`
;; note that the value(s) you pass as arguments to recur become the 
;; new local bindings for the next iteration of the loop
;; so, it is not necessary that we need to alter the local binding (`iteration`)
;; we can, pass just a value instead:

(loop [it true]
  (if (= it false)
    (str "End")
    (recur false)))

;; using loop to write my own map and filter functions]

;; nap works just like map
(defn nap [f xs] 
  (loop [initial-vector xs
         final-vector []]
    (if (zero? (count initial-vector))
      final-vector
      (let [[x & others] initial-vector]
        (recur others (conj final-vector (f x)))))))


(nap inc [1 2 3 4])
;; => [2 3 4 5]

;; my-filter works just like filter

(defn my-filter [f xs]
  (loop [initial xs
         final []]
    (if (zero? (count initial))
      final
      (let [[x & remaining] initial] 
        (recur remaining 
               (if (= (f x) true) (conj final x) final))))))

(my-filter (fn [x] (> x 0)) [-1 -11 0 33 100])
;; => [33 100]


;; reduce 

;; function takes one collection, one function, one initial value
;; during each iteration, you find the value of f(initial, current)
;; add this to the initial value
;; and this is also the initial of the next cycle



(defn my-reduce [f val xs]
  (loop [initial-collection xs
         aggregator val]
    (if (zero? (count initial-collection))
      aggregator
      (let [[first & rest] initial-collection]
           (recur rest (f aggregator first))))))


(my-reduce #(+ %1 %2) 0 [1 2 3 4 5 6])
;; => 21

(reduce #(+ %1 %2) 0 [1 2 3 4 5 6])

