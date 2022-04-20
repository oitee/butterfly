(ns butterfly.macro-and-name-space)

(ns-name *ns*)
(get (ns-interns *ns*) 'arr)
(deref #'butterfly.sequence-abstraction/arr)

(concat [1 1] {:a 1} '(9 0))

(def a (list + 1 2))

(eval (concat (list + 1 2) [10]))

(read-string "111")


(defmacro infix
  [expression]
  (println (second expression))
  (list (second expression)(first expression) (last expression)))

(infix '(1 + 2))

(infix [1 + 2])


(infix (1 + 2 + 3))

(macroexpand -1)
 

(= (list 1 2 3) '(1 2 3))

(cons "asr" '( 1 2))

(conj '(1 2) "asr")
;;=> [1 2 "asr"]

(conj (reverse [1 2 3])'("test" "!") 'if)
;; => (if ("test" "!") 3 2 1)

(let [x 123] (quote x))


(defn criticize-code
  [criticism code]
  `(println ~criticism ~code))

(defmacro code-critic
  [bad good]
  `(do ~(criticize-code "Cursed bacteria of Liberia, this is bad code:" bad)
       ~(criticize-code "Sweet sacred boa of Western and Eastern Samoa, this is good code:" good)))

(code-critic "JS" "Clojure")