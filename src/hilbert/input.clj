(ns hilbert.input)

(defn random-points
  "Get n random points"
  [n]
  (take n (repeat (rand))))

(defn get-points-from-file
  "Read the points from a file"
  [file-name]
  )
