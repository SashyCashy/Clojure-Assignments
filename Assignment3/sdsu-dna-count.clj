(defn dna-count
  "Function to get the dna-count of the dna-string."
  [dna-string]
  (read-string (clojure.string/replace (frequencies (seq dna-string)) #"\\" ":")))

(defn sdsu-dna-count
  "Higher order function to get the dna-count of the dna-string."
  [dna-string]
  (dna-count dna-string))

