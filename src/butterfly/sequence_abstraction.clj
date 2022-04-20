(ns butterfly.sequence-abstraction)

;; into accepts two args
;; first arg takes the type of data structure
;; the second takes the input data structure to be converted into the desired type
(into [] '(1 2 3 4))
; [1 2 3 4]

(into [] {:key1 "val1" :key2 "val2"})
;; => [[:key "val2"], [:key2 "val2"]]


;;implement reduce using first rest and cons

(comment (defn my-reduce [f val xs]
           (if (= (first xs) nil)
             val
             (my-reduce f (f val (first xs)) (rest xs)))))

(defn my-reduce
  [f val [x & others]]
  (if-not x
    val
    (my-reduce f (f val x) others)))

(my-reduce #(+ %1 %2) 0 [1 2 3 4 5 6])
;; => 21

(my-reduce #(+ %1 %2) 0 [])

(my-reduce #(+ %1 %2) 0 [1])

(= true (first []))



;; map using reduce
;; the aggregator should be the new sequence
;; the two element function takes the aggregator and the current element
;; within this function you first do the mapping (f(x)) and push this value to the aggregator

(defn map-using-reduce
  [map-f xs]
  (seq (reduce (fn f
                 [aggregator new-val]
                 (conj (vec aggregator) (map-f new-val)))
               (empty xs)
               xs)))


(def arr [1 2 3])

(map-using-reduce inc arr)

(map-using-reduce inc '(1 2 3))

(take 12 (repeat (str "first" " second")))


;; filter using reduce

(defn filter-using-reduce
  [filter-fn xs]
  (seq (reduce (fn
                 [aggregator new-val]
                 (if (filter-fn new-val)
                   (conj (vec aggregator) new-val)
                   aggregator))
               (empty xs)
               xs)))

(filter-using-reduce even? (take 10 (range)))
(filter-using-reduce even? (take 10 (range)))


(defn some-using-reduce
  [some-fn xs]
   (reduce (fn [aggregator new-val]
                  (if-not aggregator
                    (if (some-fn new-val)
                      (some-fn new-val)
                      aggregator)
                    aggregator))
                nil
                xs))

(some even? [1 3 5 7])
(some-using-reduce even? [1 3 5 7 9])
(some-using-reduce even? '(1 3 5 7 8))
(some even? '(1 3 5 7 8))





;;lazy sequence
;; only that part which needs to be executed, gets executed
;; `repeatedly` is in fact an infinite sequence

(take 10 (repeatedly (fn [] (rand-int 10))))

;; using `into` to convert a hash-map to a vector
(into [] (map identity {:key "value"}))

;; the first argument of `into` need not be empty
(let [arr [1 2 3 4 5 5]] (into arr (map identity arr)))

;; to reduce repitition
(let [arr [1 2 3 4 5 6]]  (into (empty arr) (map identity arr)))

(conj {:1 "one"}[:2 "two"])
;; => {:1 "one", :2 "two"}

(conj {:1 "one", :2 "two"} {:3 "three"})
;; => {:1 "one", :2 "two", :3 "three"}

;; using `partial` to create a function that converts collections to vectors

(def vector-converter (partial into []))

(vector-converter '(1 2 3 4))
(vector-converter #{1 2 3 4})
(vector-converter {:1 "one" :2 "two"})


