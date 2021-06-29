(ns bob
   [:require [clojure.string :as str]])

(defn uppercase?
  [sentence]
  (= sentence (str/upper-case sentence)))

(defn say-anything?
  [sentence]
  (re-find #"[a-zA-Z]+" sentence))

(defn yelling?
  [sentence]
  (and (say-anything? sentence)
       (uppercase? sentence)))

(defn question?
  [sentence]
  (str/ends-with? (str/trim sentence) "?"))

(defn yelling-question?
  [sentence]
  (and (yelling? sentence)
       (question? sentence)))


(defn response-for
  [sentence]
  (cond
    (str/blank? sentence) "Fine. Be that way!"
    (yelling-question? sentence) "Calm down, I know what I'm doing!"
    (yelling? sentence) "Whoa, chill out!"
    (question? sentence) "Sure."
    :else "Whatever."))