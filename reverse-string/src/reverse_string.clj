(ns reverse-string)

; (defn reverse-string 
;   [s]
;   (loop [reverse-str "" remaining s]
;     (if (empty? remaining)
;       reverse-str
;       (recur (str reverse-str (last remaining)) (butlast remaining)))))

 (defn reverse-string 
  [s]
  (apply str (into () s)))
