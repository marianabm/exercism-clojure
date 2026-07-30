(ns protein-translation)

(def codon->aminoacid
  {"AUG" "Methionine"
   "UUU" "Phenylalanine"
   "UUC" "Phenylalanine"
   "UUA" "Leucine"
   "UUG" "Leucine"
   "UCU" "Serine" 
   "UCC" "Serine"
   "UCA" "Serine"
   "UCG" "Serine"
   "UAU" "Tyrosine"
   "UAC" "Tyrosine"
   "UGU" "Cysteine"
   "UGC" "Cysteine"
   "UGG" "Tryptophan"
   "UAA" :stop
   "UAG" :stop
   "UGA" :stop})

(defn decode-first-codon
  [rna]
  (cond 
    (empty? rna) :stop
    (< (count rna) 3) :invalid
    :else (get codon->aminoacid (subs rna 0 3) :invalid)))

(defn translate-rna
  "Translates an RNA string into amino acids."
  [rna]
  (loop [r rna
       aminoacid-seq []]
    (let [aminoacid (decode-first-codon r)]
      (case aminoacid
        :stop
        aminoacid-seq

        :invalid
        (throw (IllegalArgumentException. "Invalid codon"))
        
        (recur (subs r 3) (conj aminoacid-seq aminoacid))))))
