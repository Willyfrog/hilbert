(ns hilbert.core
  :use (quil.core))

(defn draw-something
  "I don't do a whole lot."
  []
  (sketch :setup (fn [] (background 20) (ellipse 50 50 80 80)) :title "The moon delights the night"))
