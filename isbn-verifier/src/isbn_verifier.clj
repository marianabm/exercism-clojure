(ns isbn-verifier
  [:require [clojure.string :as str]])

(defn char->digit
  [c]
  (if (= c \X)
    10
    (Character/digit c 10)))

(defn isbn? 
  [isbn]
  (let [isbn' (-> isbn
                  (str/replace #"-" "")
                  (#(re-matches #"^\d{10}$|^\d{9}X$" %)))]
    (and (some? isbn')
         (->> isbn'
              (map char->digit)
              (map * (range 10 0 -1))
              (apply +)
              (#(mod % 11))
              zero?))))
