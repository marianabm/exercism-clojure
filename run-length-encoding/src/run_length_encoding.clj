(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (mapcat (juxt count first))
       (remove #{1})
       (apply str)))

(defn decode-subs
  [[_partition number char]]
  (repeat (Long/parseLong (or number "1")) char))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (->> cipher-text
       (re-seq #"(\d+)?(\D)")
       (mapcat decode-subs)
       (apply str)))
