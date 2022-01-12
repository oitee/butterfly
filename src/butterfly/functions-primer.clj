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

(defn another-one-arg-fn "this just returns the argument with a !"[x] (str x "!!"))

(defn variable-arity-function
  ;;([one-arg] (str "Arguments: " one-arg))
  ([first-arg & rest-args] (conj (map another-one-arg-fn rest-args) first-arg)))



(variable-arity-function "1" "two" 3 4 [55, 56] "seven")



