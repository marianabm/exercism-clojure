(ns rna-transcription
  [:require [clojure.string :as str]])

(defn complement-nucleotide
 [nucleotide]
 (if-let [complement (get {\G \C 
                           \C \G 
                           \T \A 
                           \A \U}
																									 nucleotide)]
   complement
   (throw (AssertionError. "Invalid nucleotide"))))

(defn to-rna 
  [dna]
  (str/escape dna complement-nucleotide))
