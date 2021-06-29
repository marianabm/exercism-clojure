(ns series
  [:require [clojure.string :as str]])

; (defn slice
;   [length string]
;   (->> string
;        (take length)
;        str/join))

; (defn slices 
;   [string length]
;   (if (pos? length)
; 		  (->> string
; 		       (iterate rest)
; 		       (take-while #(>= (count %) length))
; 		       (map (partial slice length)))
; 		  [""]))

(defn slices
  [string length]
  (if (pos? length)
    (->> string
         (partition length 1)
         (map str/join))
    [""]))
