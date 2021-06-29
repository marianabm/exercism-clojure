(ns collatz-conjecture)

(defn- next-number
  [num]
  (if (= (mod num 2) 0)
    (/ num 2)
    (+ (* 3 num) 1)))

(defn collatz 
  [num]
  {:pre [(pos? num)]}
  (count (take-while #(> % 1) (iterate next-number num))))
	