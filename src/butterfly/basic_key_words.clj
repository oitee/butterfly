(ns butterfly.basic-key-words)

(def a (+ 1 2 3))

;; if-else statements

(if true
  "True"
  "False")

(if true (do
           (def num 11) (+ 1 num 3)))

(when true (do
             (println "When True!")
             str "exit"))


(= true nil)

;;and or not

(or true false)

(or false nil (= 1 2))

(and nil false true)

(and true "Hi!" "Last")

(def my-name "Otee")

my-name


;; get and take

(get [1 2 3] 1)
;;=> 2
(get [1 2 3] 0)
;;=> 1

;; there is a way to use `get` to extract the last item, ie, using `count`
(get [1 2 3] (- (count [1 2 3]) 1))
;;=> 3

;; `get` does not work with lists
(get '(1 2 3) 1)
;;=> nil

;; `take` returns all the elements of a collection till the `n`th index
(take [1 2 3] 2)




