(ns day-2.core
  (:require [clojure.string :as s]))

(def example-input
  "7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9")

(def input
  (slurp "src\\day_2\\part_2.txt"))

(defn- parse-input
  "Parse the input to coll of colls of ints"
  [input]
  (->> input
       s/split-lines
       (map (comp (partial map parse-long)
                  (fn [s] (s/split s #" "))))))

(defn- ordered?
  [xs]
  (or
   (apply < xs)
   (apply > xs)))

(defn- acceptable-step-interval?
  [[x y z]]
  (>= 3 (max (abs (- x y)) (abs (- y z)))))

(defn safe-set?
  [xs]
  (for [ixs (partition 3 1 xs)]
    (and
     (ordered? ixs)
     (acceptable-step-interval? ixs))))

(count (filter true? (map (comp (partial every? true?) safe-set?) (parse-input input))))
