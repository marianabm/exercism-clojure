(ns nucleotide-count)

(defn count-of-nucleotide-in-strand 
  [nucleotide strand]
  {:pre [(contains? #{\A \C \T \G} nucleotide)]}
  (->> strand
       (map #(if (= % nucleotide) 1 0))
       (apply +)))


(defn nucleotide-counts 
  [strand]
  (let [nucleotides [\A \C \T \G]]
    (reduce #(assoc %1 %2 (count-of-nucleotide-in-strand %2 strand)) 
            {} 
            nucleotides)))
