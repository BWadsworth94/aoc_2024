(ns day-1.core
  (:require
   [clojure.string :as s]))

(def example-input
  "3   4
4   3
2   5
1   3
3   9
3   3")

(def part-1-input
  (slurp "./src/day_1/part_1.txt"))

(as-> part-1-input $
  (s/split-lines $)
  ;; organize each line of nums into separate vectors
  (reduce
   (fn [[vf vs] x]
     (let [cleaned-x (s/split x #"\s+")
           [cy cz] [(Integer/parseInt (str (first cleaned-x)))
                    (Integer/parseInt (str (last cleaned-x)))]]
       [(conj vf cy) (conj vs cz)]))
   [[] []]
   $)
  (map sort $)
  (partition 2 (apply interleave $))
  (map (fn [[x y]] (abs (- x y))) $)
  (apply + $))

(let [[ns fs]
      (as-> part-1-input $
        (s/split-lines $)
        (reduce
         (fn [[vf vs] x]
           (let [cleaned-x (s/split x #"\s+")
                 [cy cz] [(Integer/parseInt (str (first cleaned-x)))
                          (Integer/parseInt (str (last cleaned-x)))]]
             [(conj vf cy) (conj vs cz)]))
         [[] []]
         $)
        ((fn [[x y]]
           [x
            (frequencies y)]) $))]
  (apply + (map (fn [n] (* n (get fs n 0))) ns)))