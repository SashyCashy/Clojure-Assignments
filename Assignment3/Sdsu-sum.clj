

(defn sdsu-sum-individual
  "Function to create a sequence of multiples of a given number."
  [first-mul
   dividend]
  (distinct (loop [const-adder first-mul
                   counter first-mul
                   coll []]
              (if (> const-adder dividend) coll
                (recur (+ const-adder counter) counter (conj coll const-adder))))))

(defn get-seq
  "Function to return a concatenated sequence of two given multiples."
  [first-seq
   second-seq
   dividend]
  (let [coll (sort (distinct (concat first-seq second-seq)))]
    (if (= dividend (last coll)) (drop-last coll)
      coll)))

(defn sdsu-sum
  "Higher order function to calculate the sum of two multiples that are less than a given number."
  [first-mul
   second-mul
   dividend]
  (cond (or (neg? first-mul)
            (neg? second-mul)
            (neg? dividend)) "IllegalArgumentException: Please enter only positive numbers."

        :else (reduce + (get-seq (sdsu-sum-individual first-mul dividend)
                                 (sdsu-sum-individual second-mul dividend)
                                 dividend))))
