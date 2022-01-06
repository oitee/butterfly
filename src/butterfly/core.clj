(ns butterfly.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def a (+ 1 2 3))

(def b (+ 1 2 3 4))

(println (+ a b))

(defn my-product
  [a b]
  (* a b))

(my-product 5 6)

