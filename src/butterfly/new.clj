(ns butterfly.new)

(def a (+ 1 2 3))

(if true
  "True"
  "False")

(if true (do
      (str "True" "!") (+ 1 2 3)))

(when true (do 
             (str "When True!")
             str "exit"))

(= true nil)



