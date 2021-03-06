(ns hilbert.setup
  (:require [quil.core :as quil])
  (:require [hilbert.input :as input])
  (:require [hilbert.matrix :as matrix]))

(def +default-size+ 48)
(def +default-width+ 800)
(def +default-heigh+ 800)
(def +num-random-points+ 100)

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
     (println (format "pintando la matriz x: %d y: %d grid: %02f" x y cell-size))
     (dorun (map (fn [j] (quil/line j 0 j y)) (range 0 x cell-size)))
     (dorun (map (fn [j] (quil/line 0 j x j)) (range 0 y cell-size)))
     (for [a (range 0 y cell-size)]
       (quil/line 0 a x a))))

(defn setup []
  (quil/smooth)           ;; antialiasing
  (quil/frame-rate 1)     ;; framerate 1fps
  (quil/background 1)
  (let [point-list (input/random-points +num-random-points+)]
    (quil/set-state! :points (set point-list)
                     :cell-size (matrix/find-smaller-cell-size point-list)) ;; initialize to a set of about 100 points
    (println (str (clojure.string/join ", " (map #(format "(%02f, %02f)" (first %) (second %)) point-list)))))
  ) 

(defn draw-point
  [[x y]]
  ;(println (format "drawing %s %s" x y))
  (quil/point (* x (quil/width)) (* y (quil/height)))
  ;(quil/ellipse x y 2 2)
  )

(defn compare-border-points 
  "if x is further than any of the border points, mark it"
  [borders x]
  (loop [[top right bottom left] borders]
    (cond (< (second x) (second top)) (recur [x right bottom left])
          (> (first x) (first right)) (recur [top x bottom left])
          (> (second x) (second bottom)) (recur [top right x left])
          (< (first x) (first left)) [top right bottom x] ;; no need to recurse
          :else [top right bottom left])))

(defn get-border-points
  "get the furthest point on each side"
  [point-list]
  (let [init-point (first point-list)]
    (reduce compare-border-points (into [] (repeat 4 init-point)) (rest point-list))))

(defn draw-points [point-list]
  (quil/stroke 200 100 100)
  (quil/stroke-weight 5)
  (dorun (map draw-point point-list)))

(defn draw []
  (let [points (quil/state :points)
        cell-size (* (quil/width) (quil/state :cell-size))]
    (println (format "matriz de tamaño %02f" cell-size))
    (draw-points points)
    (draw-matrix cell-size)))
