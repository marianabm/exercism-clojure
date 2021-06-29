(ns beer-song
	 (:require [clojure.string :as str]))

(defmulti verse
  "Returns the nth verse of the song."
  identity)

(defmethod verse 0
  [_num]
  (str "No more bottles of beer on the wall, no more bottles of beer.\n"
       "Go to the store and buy some more, 99 bottles of beer on the wall.\n"))

(defmethod verse 1
  [_num]
  (str "1 bottle of beer on the wall, 1 bottle of beer.\n"
       "Take it down and pass it around, no more bottles of beer on the wall.\n"))

(defmethod verse 2
  [_num]
  (str "2 bottles of beer on the wall, 2 bottles of beer.\n"
       "Take one down and pass it around, 1 bottle of beer on the wall.\n"))

(defmethod verse :default
  [num]
  (str num " bottles of beer on the wall, " num " bottles of beer.\n"
       "Take one down and pass it around, " (dec num) " bottles of beer on the wall.\n"))


(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start]
   (sing start 0))
  ([start end]
  (->> (range start (dec end) -1)
       (map verse)
       (str/join "\n"))))
