(ns butterfly.fpwd)

(def filename "suspects.csv")

(slurp filename)

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "convert csv into a list of rows and columns"
  [string]
  (map #(clojure.string/split % #",") (clojure.string/split string #"\n")))

(parse (slurp filename))

(comment (map (fn [row-map]
       ;; row-map = {:name "www",
       ;;              :flitter: "eee"}
                (map (fn [xs]
              ;; xs = (:name "www")
                       (convert (first xs) (second xs))) row-map))
              (map (fn
                     [row]
                     {:name (first row) :glitter-index (second row)}) (parse (slurp filename)))))



{:a 1 :b 2}

{:a 1}

(defn mapify
  [n]
  {:a n})

(mapify 1)


(map identity {:a 1 :b 2})


(map (fn
       [row]
       (let [row-map {:name (first row) :glitter-index (second row)}]
         (map (fn [xs]
              ;; xs = (:name "www")
                (convert (first xs) (second xs))) row-map))) 
     (parse (slurp filename)))


(conj {:a 1 :b 2} {:c 3})
;; conj is generic; result depends on first argument type; meant for lists; better to use DS-specific functions

(assoc {:a 1 :b 2} :c 3 :d 4)

(dissoc {:a 1 :b 2} :a)

(merge {:a 1 :b 2} {:c 3, :d 4} {:e 5})

(conj #{1 2 3} 1 1 1)

(disj #{1 2 3} 1)
;; disj is only for set

(comment (disj [1 2 3] 3)
         )
;; Execution error


(defn example [arg1 ;; [1 2 3]
               arg2])

(defn example [[a b c & d :as arg1] ;; [1 2 3]
               arg2]
  {:a a :b b :c c :d d :arg1 arg1})

(example [1 2 3 4 5 6] "ignored")


(let [[a b c & d :as arg1] [1 2 3 4 5 6]] 
  {:a a :b b :c c :d d :arg1 arg1})

(let [{:keys [a b]} {:a 1 :b 2 :c 3}] a)

(let [{some-val :a some-val2 :b} {:a 1 :b 2 :c 3}] some-val2)
;; can ignore for now (too advanced)
;; remember how to destructure with :keys


(let [{:strs [a b]} {"a" 1 "b" 2 "c" 3}] 
  a)

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [acc [k v]]
                   (assoc acc k (convert k v))
                   ) {} (map vector vamp-keys unmapped-row)))
       rows))

(mapify (parse (slurp filename)))



