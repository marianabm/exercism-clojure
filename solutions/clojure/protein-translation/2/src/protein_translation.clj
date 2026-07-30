(ns protein-translation
  (:require [clojure.string :as str]))

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

(defn translate-rna
  "Translates an RNA string into amino acids."
  [rna]
  (let [codons-seq (->> (partition 3 3 nil rna)
                        (map clojure.string/join))]
    (reduce (fn [aminoacids codon]
              (let [a (get codon->aminoacid codon :invalid)]
                (case a
                  :invalid (throw (IllegalArgumentException. "Invalid codon"))
                  :stop (reduced aminoacids)
                  (conj aminoacids a))))
            []
            codons-seq)))