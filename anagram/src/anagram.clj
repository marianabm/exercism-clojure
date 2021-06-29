(ns anagram
  [:require [clojure.string :as str]])

(defn anagram?
 [word word-frequencies prospect]
 (let [prospect-lc (str/lower-case prospect)]
   (and (not= word prospect-lc) 
        (= word-frequencies (frequencies prospect-lc)))))

(defn anagrams-for 
  [word prospect-list]
  (let [word-lc (str/lower-case word)]
    (filter (partial anagram? word-lc (frequencies word-lc)) prospect-list)))
