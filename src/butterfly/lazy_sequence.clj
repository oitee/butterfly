(ns butterfly.lazy-sequence)

(defn print-numbers
  [n]
  (map println (range n))
  (println "Done printing: " n))

(print-numbers 9)

(defn print-numbers
  [n]
  (mapv println (range n))
  (println "Done printing: " n))

(defn print-numbers
  [n]
  (doall (map println (range n)))
  (println "Done printing: " n))

(defn print-numbers
  [n]
  (doseq [i (range n)]
    (println i))
  (println "Done printing: " n))

(defn print-numbers
  [n]
  (run! println (range n))
  (println "Done printing: " n))


(defn is-prime?
  [n]
  (not-any? (fn [factor]
              (zero? (mod n factor)))
            (range 2 (dec n))))

(is-prime? 8)

(def inifite-primes
  (filter is-prime? (range)))

(take 5 inifite-primes)
(take 10 inifite-primes)
(take 100 inifite-primes)


(defn eager-primes
  ([n] (eager-primes n 2 []))
  ([n curr xs]
   (if (= (count xs) n)
     xs
     (if (is-prime? curr)
       (recur n (inc curr) (conj xs curr))
       (recur n (inc curr) xs)))))

(take 10 (eager-primes 100))

(defn primes
  ([] (primes 2))
  ([curr]
   (if (is-prime? curr)
     (cons curr (lazy-seq (primes (inc curr))))
     (lazy-seq (primes (inc curr))))))



(take 5 (primes))


(take 2 (cons 100 (range)))
