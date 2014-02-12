(ns hilbert.core
  (:require [quil.core :as quil])
  (:require [hilbert.setup :as setup]))

(declare exit-on-close)

(defn -main 
  []
  ;; Workaround dangerous default behavior in clojure
  (alter-var-root #'*read-eval* (constantly false))

  (exit-on-close (quil/sketch :title "Hilbert curve"
                              :setup setup/setup
                              :draw setup/draw
                              :size [640 480])))


(defn exit-on-close
  [the-sketch]
  (let [the-frame (-> the-sketch
                      .getParent
                      .getParent
                      .getParent
                      .getParent)]
    (.setDefaultCloseOperation the-frame
                               javax.swing.JFrame/EXIT_ON_CLOSE)))
