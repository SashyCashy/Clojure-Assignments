(def num-coll [1000 900 500 400 100 90 50 40 10 9 5 4 1])

(def roman-coll ["M" "CM" "D" "CD" "C" "XC" "L" "XL" "X" "IX" "V" "IV" "I"])

(defn returnvalues
  "Function to return the greatest number from num-coll collection"
  [number]
  (first (filter (fn[num-coll]
                   (>= number num-coll)) num-coll)))

(defn sdsu-roman-numeral
  "Higher Order function to get roman equivalent of the number."
  [number]
  (cond (pos? number)
        (loop [value number
               pairs (zipmap num-coll roman-coll)
               coll []]
          (cond (pos? value)
                (recur (- value (returnvalues value)) pairs (cons coll (pairs (returnvalues value))))
                :else (apply str (flatten coll))))
        :else "IllegalArgumentException:Please enter number greater than 0"))

