(ns phone-number
  (:require [clojure.string :as str]))

(defn char->int
  [c]
  (- (int c) 48))

(defn number
  [phone-number] ;; <- arglist goes here
  ;; your code goes here
  (let [n (filter #(Character/isDigit %) phone-number)
        n-without-code (if (= \1 (first n)) (rest n) n)]
    (if 
      (and (= 10 (count n-without-code)) (>= (char->int (nth n-without-code 0)) 2) (>= (char->int (nth n-without-code 3)) 2))
      (str/join "" n-without-code)
      "0000000000")))

(defn area-code
  [phone-number]
  (subs (number phone-number) 0 3))

(defn pretty-print
  [phone-number]
  (let [n (number phone-number)]
    (str "(" (subs n 0 3) ") " (subs n 3 6) "-" (subs n 6 10))))
