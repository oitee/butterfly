(ns butterfly.pure-functions)

;; comp
;; it composes new functions by combining them
;; ((comp f g h) x y z) is equivalent to f (g (h (x y z)))
;; the last (or first) function can take n arguments (here, function f)
;; but the other functions should only accept one argument

((comp inc +) 1 3 4)
;; => 9

;; implementing my own version of comp
;; return a function that accepts `& args`
;; find the value of the last fn over `args` 
;; note: have to use `apply` over `args` as `args` will be stored as  a list (rest parameter)
;; create a new collection of functions by taking all but the last function
;; note: as functions are called from inside to out, remember to reverse this collection
;; apply reduce over this collection
;; reducing function should look like this: f(aggregator, newElement) => return newElement(aggregator) 
;; aggregator should be the value of the last function over the `& args`

(defn my-comp 
  [& fns]
  (fn [& args]
    (let [val (apply (last fns) args)
          others (reverse (take (- (count fns) 1) fns))]
      (reduce
       (fn [aggregator, new-element]
         (new-element aggregator))
       val others))))

((comp identity inc dec dec identity +) 100 10 1)

((my-comp identity inc dec dec identity +) 100 10 1)
(= ((comp inc inc inc inc *) 100 2 2) ((my-comp inc inc inc inc *) 100 2 2)
   )
