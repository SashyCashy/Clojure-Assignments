
;Working Code		30/30
;
;Unit Tests		10/10
;
;Comments		9/10
; What is a heap?
;Quality of Code		7/10
; Why to peorder methods, why mix traversal with printing -3
;56/60

(ns heaptree.core
  (:require [clojure.zip :as zip]))

(declare min-heap)
(declare heap-zipper-insert)
(declare replace-node)
(declare zipper->value)
(declare calculate-height)
(declare zipper->left-child)
(declare right-height-zipper)
(declare left-height-zipper)
(declare heap-empty?)
(declare pre-order-int)
(declare pre-order-traversal-int)
(declare pre-order-string)
(declare pre-order-traversal-string)
(declare filter-list)

 (defn zipper->left-child
   "Traversed to the heap's left child."
   [zipper]
   (-> zipper  zip/down  zip/right))

(defn zipper->right-child
"Traversed to the heap's right child."
 [zipper]
  (-> zipper zip/down zip/rightmost))

(defn zipper->value
    "Gets the value of the heap's root node."
   [zipper]
   (if (zip/node zipper)
     (-> zipper zip/down zip/node)))


(defn left-height-zipper
   "heaps left-side height is being calculated."
   [zipper]
   (loop [left-heap (zipper->left-child zipper) counter 0]
     (if (heap-empty? left-heap)
       counter
       (recur (zipper->left-child left-heap) (inc counter)))))

(defn right-height-zipper
  "heaps right-side height is being calculated."
  [zipper]
  (loop [left-heap (zipper->right-child zipper) counter 0]
    (if (heap-empty? left-heap)
      counter
      (recur (zipper->left-child left-heap) (inc counter)))))


 (defn calculate-height
   "Manipulates the heap by the height that is calculated."
   [zipper value]

   (let [ left-height (left-height-zipper zipper)
          right-height (right-height-zipper zipper)]

     (cond
      (= left-height right-height) (heap-zipper-insert (zipper->left-child zipper) value)
      (> left-height right-height) (heap-zipper-insert (zipper->right-child zipper) value))))


 (defn replace-node
   "Gets the value of the heap node and replaces it at that position."
   [zipper replacement]
   (let [location (zip/node zipper)
         node (zip/make-node zipper location [replacement nil nil])]
     (-> zipper (zip/replace node) zip/root)))


 (defn heap-empty?
   "Checks if the heap/node is empty or not."
   [zipper]
   (not (zip/node zipper)))


 (defn heap-zipper-insert
   "Insert of the zipper."
   [zipper specific-value]
   (let [value (zipper->value zipper)]
     (cond
      (heap-empty? zipper) (replace-node zipper specific-value)
      (zero? (compare specific-value value)) (zip/root zipper)
      (neg? (compare specific-value value)) (recur (-> zipper zip/down (zip/replace specific-value) zip/root zip/vector-zip) value)
      (pos? (compare specific-value value)) (calculate-height zipper specific-value))))

 (defn min-heap
    "Min Heap is constructed."
   [heap value]
   (cond
   (nil? value)(try (throw (NullPointerException. "Empty List")))
   (empty? heap) (replace-node (zip/vector-zip heap) value)
   (heap-empty? heap) (replace-node (zip/vector-zip heap) value)
    :else
    (heap-zipper-insert (zip/vector-zip heap) value)))

 (defn pre-order-int
  "Higher Order function to create pre-ordered for int list."
  [zipper]
  (if ((complement empty?) zipper)
    (let [ left-heap (if (not (heap-empty? zipper))
                       (pre-order-int (zipper->left-child zipper)))

           right-heap (if (not (heap-empty? zipper))
                        (pre-order-int (zipper->right-child zipper)))
           value (cond
                  ((complement string?) (zipper->value zipper))(zipper->value zipper)
                  (string? (zipper->value zipper)) (throw ( IllegalArgumentException. "Alphabets Not Allowed")))
           coll (cons value (concat left-heap right-heap))
           filtered-coll (vec (remove nil? (vec coll)))]
      filtered-coll)))

 (defn pre-order-string
  "Higher Order function to create pre-ordered for string list."
  [zipper]
  (if ((complement empty?) zipper)
    (let [ left-heap (if (not (heap-empty? zipper))
                       (pre-order-string (zipper->left-child zipper)))

           right-heap (if (not (heap-empty? zipper))
                        (pre-order-string (zipper->right-child zipper)))
           value (cond
                  ((complement number?) (zipper->value zipper))(zipper->value zipper)
                  (number? (zipper->value zipper)) (throw ( IllegalArgumentException. "Numbers Not Allowed")))
           coll (cons value (concat left-heap right-heap))
           filtered-coll (vec (remove nil? (vec coll)))]
      filtered-coll)))

(defn pre-order-traversal-int
  "Lower Order function to create pre-ordered int list."
  [heap]
  (let [pre-coll (cond (empty? heap) (throw (IllegalArgumentException. (str "Empty List")))
                       (heap-empty? heap) (throw (NullPointerException. (str "Wrong List")))
                       :else
                       (pre-order-int (zip/vector-zip heap)))]
    pre-coll))

(defn pre-order-traversal-string
  "Lower Order function to create pre-ordered string list."
  [heap]
  (let [pre-coll (cond (empty? heap) (throw (IllegalArgumentException. (str "Empty List")))
                       (heap-empty? heap) (throw (NullPointerException. (str "Wrong List")))
                       :else
                       (pre-order-string (zip/vector-zip heap)))]
    pre-coll))

(defn filter-list
  "Strings containing 'ing' are in the heap, rest removed."
   [zipper]
   (let [filtered-coll (vec (filter #(pos? (.indexOf % "ing")) zipper))]
     (if (empty? filtered-coll)
       (throw (NullPointerException. (str "Empty List")))
       filtered-coll)))

