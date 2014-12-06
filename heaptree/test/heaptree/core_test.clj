
(ns heaptree.core-test
  (:require [clojure.test :refer :all]
            [heaptree.core :refer :all]))

"Test cases for the constructing min-heap containing int/string values."
(deftest min-heap-test

  ;Test 1
  ;Insert the element when heap contains null values
  (is (= (min-heap [nil nil nil] 1) [1 nil nil]))

  ;Test 2
  ;Insert the element when heap is empty
  (is (= (min-heap [] 50) [50 nil nil]))

  ;Test 3
  ;Exception raise when null values are being inserted
  (is (thrown? java.lang.NullPointerException (min-heap [15 nil nil] nil)))

  ;Test 4
  ;Insert when duplicate values are inserted
  (is (= (min-heap [1 nil nil] 1) [1 nil nil]))

  ;Test 5
  ;Root node of the heap replaced by a smaller value
  (is (= (min-heap [2 nil nil] 1) [1 [2 nil nil] nil]))

  ;Test 6
  ;New inserted value becomes the right child of the heap
  (is (= (min-heap [1 [2 nil nil] nil] 3) [1 [2 nil nil] [3 nil nil]]))

  ;Test 7
  ;Root Node changes and manipulation of the heap
  (is (= (min-heap [1 [2 nil nil] [3 nil nil]] 0) [0 [1 [2 nil nil] nil] [3 nil nil]]))

  ;Test 8
  ;Root Node changes and manipulation of the heap
  (is (= (min-heap [0 [1 [2 nil nil] nil] [3 nil nil]] -1) [-1 [1 [2 nil nil] nil] [0 [3 nil nil] nil]]))

  ;Test 9
  ;Test case of the assignment 4
  (is (= (min-heap [45 [65 [90 [99 nil nil] [95 nil nil]] [81 nil nil]] [72 [82 nil nil] [96 nil nil]]] 50)
           [45 [65 [90 [99 nil nil] [95 nil nil]] [81 nil nil]] [50 [72 [82 nil nil] nil] [96 nil nil]]]))

  ;Test 10
  ;Insert the element when heap contains null values
  (is (= (min-heap [nil nil nil] "a") ["a" nil nil]))

  ;Test 11
  ;Insert the element when heap is empty
  (is (= (min-heap [] "test") ["test" nil nil]))

  ;Test 12
  ;Exception raise when null values are being inserted
  (is (thrown? java.lang.NullPointerException (min-heap ["clojure" nil nil] nil)))

  ;Test 13
  ;Insert when duplicate values are inserted
  (is (= (min-heap ["test" nil nil] "test") ["test" nil nil]))

  ;Test 14
   ;New inserted value becomes the left child of the heap
  (is (= (min-heap ["a" nil nil] "b") ["a" ["b" nil nil] nil]))

  ;Test 15
  ;Root node of the heap replaced by a smaller value
  (is (= (min-heap ["a" nil nil] "Z") ["Z" ["a" nil nil] nil]))

  ;Test 16
   ;New inserted value becomes the right child of the heap
  (is (= (min-heap ["a" ["b" nil nil] nil] "c") ["a" ["b" nil nil] ["c" nil nil]]))

  ;Test 17
  ;Root Node changes and manipulation of the heap
  (is (= (min-heap ["b" ["c" nil nil] ["d" nil nil]] "a") ["a" ["b" ["c" nil nil] nil] ["d" nil nil]])))


 "Test cases for the pre-order of a heap containing int values."

 (deftest pre-order-traversal-int-test

   ;Test 1
   ;Pre-order of a zero level heap
   (is (= (pre-order-traversal-int [1 nil nil]) [1]))

   ;Test 2
   ;Pre-order of a one level heap
   (is (= (pre-order-traversal-int [1 [2 nil nil] nil]) [1 2]))

   ;Test 3
   ;Pre-order of a one level heap
   (is (= (pre-order-traversal-int [1 [2 nil nil] [3 nil nil]]) [1 2 3]))

   ;Test 4
   ;Pre-order of a two level heap
   (is (= (pre-order-traversal-int [0 [1 [2 nil nil] nil] [3 nil nil]]) [0 1 2 3]))

   ;Test 5
   ;Pre-order of given heap in the assignment 4
   (is (= (pre-order-traversal-int [45 [65 [90 [99 nil nil] [95 nil nil]] [81 nil nil]] [50 [72 [82 nil nil] nil] [96 nil nil]]]) [45 65 90 99 95 81 50 72 82 96]))

   ;Test 6
   ;Exception raise when pre-order traversal attempted for an empty heap
   (is (thrown? java.lang.IllegalArgumentException (pre-order-traversal-int [])))

   ;Test 7
   ;Exception raise when pre-order traversal attempted for an null collection
   (is (thrown? java.lang.NullPointerException (pre-order-traversal-int [nil nil nil])))

   ;Test 8
   ;Exception raise when pre-order traversal containing integer values attempts to create a pre-order heap of non-integer values
   (is (thrown? java.lang.IllegalArgumentException (pre-order-traversal-int ["a" nil nil])))

   ;Test 9
   ;Get pre-order collection after min-heap creates a heap
   (is (= (pre-order-traversal-int (min-heap [1 [2 nil nil] [3 nil nil]] 0)) [0 1 2 3]))

   ;Test 10
   ;Exception raise when pre-order traversal containing integer values attempts manipulate min-heap of non-integer values
   (is (thrown? java.lang.IllegalArgumentException (pre-order-traversal-int (min-heap ["b" ["c" nil nil] ["d" nil nil]] "a"))))

   ;Test 11
   ;Exception raise when null values are inserted for heap creation
   (is (thrown? java.lang.NullPointerException (pre-order-traversal-int (min-heap [15 nil nil] nil)))))


 "Test cases for the pre-order of a heap containing string values."

 (deftest pre-order-traversal-string-test
   ;Test 1
   ;Pre-order of a zero level heap
   (is (= (pre-order-traversal-string ["a" nil nil]) ["a"]))

   ;Test 2
   ;Pre-order of a one level heap
   (is (= (pre-order-traversal-string ["a" ["b" nil nil] nil]) ["a" "b"]))

   ;Test 3
   ;Pre-order of a one level heap
   (is (= (pre-order-traversal-string ["Z" ["a" nil nil] nil]) ["Z" "a"]))

   ;Test 4
   ;Pre-order of a one level heap
   (is (= (pre-order-traversal-string ["a" ["b" nil nil] ["c" nil nil]]) ["a" "b" "c"]))

   ;Test 5
   ;Pre-order of a two level heap
   (is (= (pre-order-traversal-string ["a" ["b" ["c" nil nil] nil] ["d" nil nil]]) ["a" "b" "c" "d"]))

   ;Test 6
   ;Exception raise when pre-order traversal attempted for an empty heap
   (is (thrown? java.lang.IllegalArgumentException (pre-order-traversal-string [])))

   ;Test 7
   ;Exception raise when pre-order traversal attempted for an null collection
   (is (thrown? java.lang.NullPointerException (pre-order-traversal-string [nil nil nil])))

   ;Test 8
   ;Exception raise when pre-order traversal containing string values attempts to create a pre-order heap of non-string values
   (is (thrown? java.lang.IllegalArgumentException (pre-order-traversal-string [100 nil nil])))

   ;Test 9
   ;Get pre-order collection after min-heap creates a heap
   (is (= (pre-order-traversal-string (min-heap ["b" ["c" nil nil] ["d" nil nil]] "a")) ["a" "b" "c" "d"]))

   ;Test 10
   ;Exception raise when pre-order traversal containing string values attempts manipulate min-heap of non-string values
   (is (thrown? java.lang.IllegalArgumentException (pre-order-traversal-string (min-heap [1 [2 nil nil] [3 nil nil]] 0))))

   ;Test 11
   ;Exception raise when null values are inserted for heap creation
   (is (thrown? java.lang.NullPointerException (pre-order-traversal-string (min-heap ["a" nil nil] nil)))))


 "Test cases for the pre-order of a heap containing string values."

 (deftest filter-list-test

   ;Test 1
   ;Exception raise when filter-list is called for an pre-ordered collection
   (is (thrown? java.lang.NullPointerException (filter-list [])))

   ;Test 2
   ;Exception raise when filter-list is called for an pre-ordered collection and returns an empty list
   (is (thrown? java.lang.NullPointerException (filter-list ["sky" "rock"])))

   ;Test 3
   ;Filters the pre-ordered collection that match the criteria
   (is (= (filter-list ["a" "sky" "string" "ingmak" "making"])) ["string" "making" ])

   ;Test 4
   ;Exception raise when filter-list tries to manipulate the empty list returned by the preorder function
   (is (thrown? java.lang.IllegalArgumentException (filter-list (pre-order-traversal-string []))))

   ;Test 5
   ;Exception raise when filter-list is called for an pre-ordered collection and returns an empty list
   (is (thrown? java.lang.NullPointerException (filter-list (pre-order-traversal-string ["a" ["b" ["c" nil nil] nil] ["d" nil nil]]))))

   ;Test 6
   ;Filters the pre-ordered collection returned by the preorder function according to the criteria
   (is (= (filter-list (pre-order-traversal-string ["aztec" ["baseball" ["casing" nil nil] nil] ["doing" nil nil]]))["casing" "doing"]))

   ;Test 7
   ;Exception raise when filter-list is called after heap is constructed upon which preorder
   ;function is called and that collection returns an empty list after filtered
   (is (thrown? java.lang.NullPointerException (filter-list (pre-order-traversal-string (min-heap ["b" ["c" nil nil] ["d" nil nil]] "a")))))

   ;Test 8
   ;Exception raise when heap could not be created
   (is (thrown? java.lang.NullPointerException (filter-list (pre-order-traversal-string (min-heap ["b" ["casing" nil nil] ["d" nil nil]] nil)))))

   ;Test 9
   ;Filters the pre-ordered collection that was returned by preorder function after heap was constructed containing null values
   (is (= (filter-list (pre-order-traversal-string (min-heap [nil nil nil] "doing"))) ["doing"]))

   ;Test 10
   ;Filters the pre-ordered collection that was returned by preorder function after heap was constructed which was empty
   (is (= (filter-list (pre-order-traversal-string (min-heap [] "caring"))) ["caring"]))

   ;Test 11
   ;Filters the pre-ordered collection that matched the criteria
   ;after applying preorder function on a heap that contained duplicate value
   (is (= (filter-list (pre-order-traversal-string (min-heap ["b" ["casing" nil nil] ["d" nil nil]] "casing"))) ["casing"]))

   ;Test 12
   ;Filters the pre-ordered collection that matched the criteria after applying preorder function on a heap
   (is (= (filter-list (pre-order-traversal-string (min-heap ["b" ["casing" nil nil] ["d" nil nil]] "spring"))) ["casing" "spring"]))

   ;Test 13
   ;Filters the pre-ordered collection that matched the criteria after applying preorder function on a heap
   (is (= (filter-list (pre-order-traversal-string (min-heap ["b" ["doing" nil nil] ["d" nil nil]] "casing"))) ["casing" "doing"])))
