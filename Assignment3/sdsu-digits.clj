(defn sdsu-digits
  [number base]
  "Function to convert the number depending upon its base"
  (cond (neg? number) "IllegalArgumentException:Please enter number greater than 0"
        :else (loop [dividend number
                     result []]
                (if (>= dividend base)
                  (recur (quot dividend base) (cons (rem dividend base) result)) (cons dividend result)))))


