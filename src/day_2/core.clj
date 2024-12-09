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

(def safe-range 3)

(defn safe?
  [dir x1 x2]
  (and
   (if (= :asc dir) (neg? (- x1 x2)) (pos? (- x1 x2)))
   (>= safe-range (abs (- x1 x2)))))

(defn safe-set?
  [xs]
  (let [order (if (neg? (- (first xs) (second xs))) :asc :desc)]
    (for [[x1 x2] (partition 2 1 xs)]
      (safe? order x1 x2))))

(count (filter (comp #(every? true? %) safe-set?) (parse-input input)))

;; -------

