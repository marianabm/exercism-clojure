(ns armstrong-numbers)

(defn digits
  [num]
  (->> num
       (iterate #(quot % 10))
       (take-while pos?)
       (map #(rem % 10))))

(defn exp
  [base pow]
  (apply * (repeat pow base)))

(defn armstrong?
  [num]
  (let [digits (digits num)
        size   (count digits)]
    (->> digits
         (map #(exp % size))
         (apply +)
         (= num))))
