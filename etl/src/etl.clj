(ns etl
  (:require [clojure.string :as s]))

(defn transform-data [m letter score]
  (into m (map #(hash-map (s/lower-case %) letter) 
               score)))

(defn transform [source]
  (reduce-kv transform-data {} source))
