
;Question 1:

( defn sdsu-nth [ m y ]
  "Function to find n-th element without recursion."
  (
   first ( map ( into [] m )( range (+ y) (+ y 1) ) )
  )
)

(= (sdsu-nth '(6 5 4 3) 2) 4)
(= (sdsu-nth [:a :b :c] 1) :b)
(= (sdsu-nth '([1 2] [3 4] [5 6]) 2) [5 6])

;-----------------------------------------------------------------------------------------------------
;Question 2:

( defn r-sdsu-nth [ x y ]
  "Function to find the nth element using recursion."
  ( loop [ [ pre & res :as all ] (seq x) end  y initial  0 ]
    (

     if ( < y initial )
      ( last ( take initial all ) )
      ( recur all y ( inc initial ) )

    )
  )
)

(= (r-sdsu-nth '(6 5 4 3) 2) 4)
(= (r-sdsu-nth [:a :b :c] 1) :b)
(= (r-sdsu-nth '([1 2] [3 4] [5 6]) 2) [5 6])

;-----------------------------------------------------------------------------------------------------
;Question 3:

( defn sdsu-reverse [ m ]
  "Function to reverse the sequence."
  ( loop [ start 0  end (count m)  result '()  more (seq m) ]

    (
	 if ( > end start )
      ( recur start (dec end) ( conj result ( first more ) ) ( drop 1 ( into[] more ) ) )
      result )
   )
 )

(sdsu-reverse '(6 5 4 3))
(sdsu-reverse [:a :b :c])
(sdsu-reverse '([1 2] [3 4] [5 6]))
;-----------------------------------------------------------------------------------------------------
;Question 4:

( defn sdsu-dup [ m ]
  "Function to duplicate the elements in the sequence"
  ( sort
   ( reduce into ( replicate 2 m ) )
  )
)

(= (sdsu-dup [1 2 3]) '(1 1 2 2 3 3))
(= (sdsu-dup [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (sdsu-dup [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

;------------------------------------------------------------------------------------------------------
;Question 5:

( defn sdsu-no-dup [ p ]
  "Function to remove the duplicates in the sequence."
  ( loop [ [ initial & res :as all ] (seq p) result '() ]

    (
     if all (

             if ( = initial ( first res ) )
             ( recur res result )
             ( recur res ( cons initial result ) )

             )

     ( sdsu-reverse result )
    )
   )
 )

(= (sdsu-no-dup [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (sdsu-no-dup [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

;----------------------------------------------------------------------------------------------------------

;Question 6:

( defn sdsu-pack [ vecs ]
  "Function to pack similar elements into subsequence."
  (

    if ( seq vecs )
    (
     let [ [ initial & more ] vecs [ cut res ] ( split-with #(= % initial) more ) ]
     ( cons ( cons initial cut ) ( sdsu-pack res ) )
    )

  )
)

(= (sdsu-pack [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (sdsu-pack [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
