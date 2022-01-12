(ns butterfly.functions-primer)

(do (def num1 11) (def num2 12) ((or + -) num1 num2))
;; => 23
;; here, we pass two functions `+` and `-` to another function `or`
;; the function so returned is applied on two arguments: num1 num2
;; we use `do` to ensure that we can first define `num1` and `num2`

(+ (inc 200) (- (/ 11 num1) (inc 11)))
;; => 190

;; this is an example of how function arguments are evaluated recursively
;; before being applied to the main function
;; step 1: (inc 200), which is the first argument to `+` function gets evaluated  to `201`
;; The expression now stands as follows:
(+ 200 (- (/ 11 num1) (inc 11)))

;; step 2: `(+ 11 num1 1)`, which is the first argument to the `-` function, gets evaluated to `1`
;; The expression now stands as follows:
(+ 200 (- 1 (inc 11)))

;; step 3: `(inc 11)`, which is the second argument to the `-` function gets evaluated to `12`
;; The expression now stands as follows:
(+ 200 (- 1 12))

;; step 4: `(- 1 12)`, which is the second argument to `+` gets evaluated to `11`
;; The expression now stands as: ``
(+ 200 -11)

;; step 5: `(+ 201 11) gets evaluated to `189`
189

;; arity overloading: a different function body for each different numbers of arguments

(defn multiple-args-function
  "A function to describe arity overloading"
  ([] str "Zero Argument function body")

  ([x] (str "One argument function body; argument passed:" x))

  ([a b] (str "Two argument function; arguments:" a b)))

(multiple-args-function)
;;=> "Zero Argument function body"

(multiple-args-function 1)
;; =>"One argument function body; argument passed:1"

(multiple-args-function 1 2)
;;"Two argument function; arguments:12"


;; variable arities using rest parameters

(defn variable-arity-function
  ([one-arg] (str "Arguments: " one-arg))
  ([first-arg & rest-args] (conj (map variable-arity-function rest-args) first-arg)))


(variable-arity-function "1" "two" 3 4 [55, 56] "seven")

;; destructuring

(defn example [x] (str x))
;; here, we are not looking what the argument really is
;; it could be a vector with n elements, or just a string or any other data structure or a function

(example [1 2 3])
;;=> "[1 2 3]"

(defn returns-only-first [[first-element]] (str first-element))
;; here, we say that the one-argument function will accept a vector
;; and the first element will be called 'first-element'

(returns-only-first [1 2 3 4])
;; => "1"


(defn example-with-parallel-expressions "To see if we can have multiple parallel forms within one function"
  [x, y]
  (println "first: " x)
  (println "second" y))

(example-with-parallel-expressions 100 "22")
;; works! REPL:
;; first:  100
;; second 22
;; nil


;;destructuring maps

(defn destructuring-maps "Trying out how destructuring works for hash-maps"
  [{:key1 key1 :key2 key2}]
  (println (str "Key1 value" key1)
       (str "Key2 value" key2)))




