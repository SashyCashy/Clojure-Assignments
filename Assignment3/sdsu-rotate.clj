(defn sdsu-rotate
  "Program to rotate the sequence in either direction."
  [rotate-by coll]
  (let [[init & res :as all] coll
        result []
        len (count all)]

    (cond
      (and (pos? rotate-by) (> len rotate-by))
        (let [sub-coll (concat (drop rotate-by coll) result)]
          (concat sub-coll (drop-last (- len rotate-by)
           coll)))

     (and (pos? rotate-by) (< len rotate-by))
       (let [moves (mod rotate-by len)
              sub-coll (concat (drop moves coll ) result)]
         (concat sub-coll (first (split-at  moves
          coll )))
                                             )
     (and (< rotate-by 0) (> len (- rotate-by)) )
       (let [sub-coll (concat (drop (+ rotate-by len) coll) result)]
         (concat sub-coll (first (split-at (+ rotate-by len)
          coll ))))

     (and (< rotate-by 0) (< len (- rotate-by)))
       (let [moves (mod rotate-by len)
             sub-coll (concat (first (split-at moves coll)))]
         (concat (drop moves coll )
          sub-coll))

     :else (seq coll))))

