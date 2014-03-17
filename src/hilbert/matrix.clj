(ns hilbert.matrix)

(defn between? 
  "is the point between limits?"
  [x min max]
  (and (>= x min) (<= x max)))

(defn point-in-square
  "given a point and a square, find if it is inside"
  [[px, py] [sx, sy, width]]
  (and (between? px sx (+ sx width))
       (between? py sy (+ sy width))))

(defn all-cells-full?
  "given a cell width and a max width find if all the cells contain at least one point"
  [cell-width point-list & {:keys [max-width] :or {max-width 1.0}}]
  (loop [cx 0.0
         cy 0.0]
    (cond (>= cx max-width) (recur 0 (+ cy cell-width))
          (>= cy max-width) true
          (some #(point-in-square % [cx cy cell-width]) point-list) (recur (+ cx cell-width) cy)
          :else false)))

(defn find-smaller-cell-size
  "given a list of points find the smallest cell size where 
  every cell in the grid still contains at least a point"
  [point-list 
   & {:keys [max-width] 
      :or {max-width 1.0}}]
  (loop [cell-width (/ max-width 2)]
    (if 
        (all-cells-full? cell-width point-list :max-width max-width)
      (recur (/ cell-width 2))
      (* cell-width 2))))
