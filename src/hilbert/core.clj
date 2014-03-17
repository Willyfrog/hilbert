(ns hilbert.core
  (:require [quil.core :as quil])
  (:require [hilbert.setup :as setup])
  (:require [hilbert.input :as input]))

(declare exit-on-close)

(defn -main 
  []
  ;; Workaround dangerous default behavior in clojure
  (alter-var-root #'*read-eval* (constantly false))

  (exit-on-close (quil/sketch :title "Hilbert curve"
                              :setup setup/setup
                              :draw setup/draw
                              :key-pressed input/read-key
                              :size [640 640])))


(defn exit-on-close
  [the-sketch]
  (let [the-frame (-> the-sketch
                      .getParent
                      .getParent
                      .getParent
                      .getParent)]
    (.setDefaultCloseOperation the-frame
                               javax.swing.JFrame/EXIT_ON_CLOSE)))
