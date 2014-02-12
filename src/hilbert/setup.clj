(ns hilbert.setup
  (:require [quil.core :as quil]))

(def +default-size+ 48)
(def +default-width+ 800)
(def +default-heigh+ 640)

(defn draw-matrix 
  "Rellena con una matriz"
  ([] 
     (draw-matrix +default-width+ +default-heigh+ +default-size+))
  ([size]
     (draw-matrix +default-width+ +default-heigh+ size))
  ([x y]
     (draw-matrix x y +default-size+))
  ([x y cell-size]
     (quil/stroke 200)       ;; color del trazo
     (quil/stroke-weight 1)  ;; tamaño del trazo
     (println (format "pintando la matriz x: %d y: %d grid: %d" x y cell-size))
     (doall (map (fn [j] (quil/line j 0 j y)) (range 0 x cell-size)))
     (doall (map (fn [j] (quil/line 0 j x j)) (range 0 y cell-size)))
     (for [a (range 0 y cell-size)]
       (quil/line 0 a x a))))

(defn setup []
  (quil/smooth)           ;; antialiasing
  (quil/frame-rate 1)     ;; framerate 1fps
  (quil/background 1)
  (draw-matrix))

(defn draw []
  (println "draaaww!"))
