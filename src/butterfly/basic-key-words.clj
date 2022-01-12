(ns butterfly.new)

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




