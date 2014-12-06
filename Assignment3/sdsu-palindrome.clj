(defn palindrome
  "Program to return largest palindrome number depending upon the digit."
  [palindromic-number]
  (let [start (int (- (Math/pow 10 palindromic-number) (Math/pow 10 (- palindromic-number 2))))
        end (int (Math/pow 10 palindromic-number))
        start-number (reverse (range start end))
        end-number (reverse (range start end))]

    (for [num1 start-number
          num2 end-number]
      (if (= (str (* num1 num2))
             (apply str (reverse (str (* num1 num2)))))
        (* num1 num2)))))

(defn sdsu-palindrome
  "Higher order function calling palindrome function by passing palindrome-value into it."
  [value]
  (cond
    (> value 1)
      (last (sort (filter (complement nil?)
                     (into [] (palindrome value)))))

   :else "Please enter number greater than 1"))

