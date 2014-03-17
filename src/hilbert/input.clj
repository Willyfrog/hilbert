(ns hilbert.input
  (:require [quil.core :as quil]))

(defn random-points
  "Get n random points"
  [n]
  (take n (repeatedly (fn [] [(rand) (rand)]))))

(defn get-points-from-file
  "Read the points from a file"
  [file-name]
  )

(defn read-key
  "which key was pressed?"
  []
  (println (format "Pulsaste %s" (quil/key-as-keyword)))
  (let [key (quil/key-as-keyword)]
    (cond 
     (= :+ key) (quil/set-state! :matrix-size (+ (quil/state :matrix-size) 10))
     (= :- key) (quil/set-state! :matrix-size (- (quil/state :matrix-size) 10)))))
