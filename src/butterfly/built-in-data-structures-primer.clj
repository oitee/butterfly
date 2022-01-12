(ns butterfly.built-in-data-structures-basics)


;; hash-maps

{:prop1 "val1" "prop2" "val2" :prop3 12}

(hash-map :prop1 "val1" "prop2" "val2" :prop3 123)


(get {:fruit "mango" :color "pink"} :fruit)

(get (hash-map :fruit "mango" :color "pink") :age "not found")

(def my-hash-map (hash-map :fruit "mango" :color "pink" :family {:parent "Plant" :sibling "Another Mango"} "likes" "yes"))

(get-in my-hash-map [:family :parent])

(get my-hash-map "likes")
;;=> "yes"
;; you can have non-key-word keys also

(my-hash-map :color)

(my-hash-map :family)

(:family my-hash-map)

;;vectors

(def my-vector [0 1 2 3 4 5 "six" {:key "seven"}])

(get my-vector 5 "default")

(get my-vector 6 "default")

(get my-vector 7)

(:key (get my-vector 7))

(get my-vector 11 "default")

(def name-of-vector (vector "zero" "one" "two" 4))

name-of-vector

(conj my-vector "eight")

(first my-vector)
;; => 0

(last my-vector)
;; => {:key "seven"}

(second my-vector)
;;=> 1

;; lists

`("zero" 1 2 "three")

(def my-list (list 0 1 2 "three" [4 5]))

my-list

(nth my-list 4)

(nth my-list 5 "default")

(conj my-list "-1")

;; hash-set

#{1 2 3 "4" [5, 6]}
;; =>#{1 2 3 "4" [5, 6]}

(def my-set (hash-set 0 1 2 3 "four" :5))

(get my-set "four")
;; => four

(get my-set "eleventh hour")
;; => nil

(get my-set "eleventh hour" "Nah! Doesnt contain")
;; => "Nah! Doesnt contain"

(contains? my-set "four")
;; => true

(contains? my-set "eleventh hour")
;; => false

(:11 my-set)
;; => nil

(:5 my-set)
;; => :5

(def my-new-set (conj my-set "eleventh hour"))

(contains? my-new-set "eleventh hour")
;; =>true


