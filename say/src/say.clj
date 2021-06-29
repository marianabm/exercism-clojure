(ns say
  (:require [clojure.string :as str]))

(def numbers {0 ""
              1 "one"
              2 "two"
              3 "three"
              4 "four"
              5 "five"
              6 "six"
              7 "seven"
              8 "eight"
              9 "nine"
              10 "ten"
              11 "eleven"
              12 "twelve"
              13 "thirteen"
              14 "fourteen"
              15 "fifteen"
              16 "sixteen"
              17 "seventeen"
              18 "eighten"
              19 "nineteen"
              20 "twenty"
              30 "thirty"
              40 "forty"
              50 "fifty"
              60 "sixty"
              70 "seventy"
              80 "eighty"
              90 "ninety"})

(def tens-scale ["" "thousand" "million" "billion"])

(defn num->num-seq [ten-scale n]
  (->> n
       (iterate #(quot % ten-scale))
       (take-while pos?)
       (mapv #(mod % ten-scale))))

(defn say-number-two-digits
  [ten unity]
  (->> [(numbers (* 10 (or ten 0))) (numbers unity)]
       (remove str/blank?)
       (str/join "-")))

(defn say-number
  [ten-scale num]
  (when (pos? num)
    (let [[u t h] (num->num-seq 10 num)
          word (cond
                 (<= num 19)
                 ;; => Execution error (ClassCastException) at say/eval8204 (form-init7723522704829149165.clj:52).
                 ;;    class clojure.core$num cannot be cast to class java.lang.Number (clojure.core$num is in unnamed module of loader 'app'; java.lang.Number is in module java.base of loader 'bootstrap')
                 [(numbers num)]

                 (<= num 99) [(say-number-two-digits t u)]

                 :else
                 [(numbers h) "hundred" (say-number-two-digits t u)])]
      (conj word ten-scale))))

(defn number
  [num]
  (when (or (neg? num) (> num 999999999999))
    (throw (IllegalArgumentException. "Invalid number")))

  (if (zero? num)
    "zero"
    (->> num
         (num->num-seq 1000)
         (map say-number tens-scale)
         reverse
         (apply concat)
         (remove str/blank?)
         (str/join " "))))
