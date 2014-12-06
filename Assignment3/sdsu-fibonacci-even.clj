(defn get-fib-sequence
  "Functionality to calculate the fibonacci series."
  [number]
  (loop [prev-num 1
         next-num 1
         coll [1]
         counter 1]
    (if (> counter number)
      (drop-last 1 coll)
      (recur next-num (+ prev-num next-num) (conj coll (+ prev-num next-num)) (inc counter)))))

(defn sdsu-fibonacci-even
  "Higher Order Function that gets a fibonacci series and returns addition of even numbers."
  [value]
  (cond (< 2 value)
        (let [final-coll (get-fib-sequence value)]
          (reduce + (filter even? final-coll)))

   :else "IllegalArgumentException:Please enter number greater than 2."))

