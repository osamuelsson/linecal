(ns linecal.core
  (:use [clojure.pprint :as pprint])
  (:gen-class))

;; TODO
;; Make it generate for something other than the current month (.newInstance ..)
;; Change this to a "gen"
;; Add a "sum", etc.

(def nameOfDay {2 'M
                3 'Ti
                4 'O
                5 'To
                6 'F
                7 'L
                1 'S})

(defn createLine
  "creates one of the lines"
  [ ymd ]
  (let [[year month day cal] ymd]
    (.clear cal)
    (.set cal year month day)
    (let [dayOfWeek (.get cal java.util.Calendar/DAY_OF_WEEK)]
      [year (+ 1 month) day (nameOfDay dayOfWeek)])))

(defn line-calendar
  "Returns a list of the days in the month represented by the date and the day of week"
  [year month]
  (let [cal (.newInstance java.util.GregorianCalendar)
        firstDayOfMonth (.getActualMinimum cal java.util.Calendar/DAY_OF_MONTH)
        lastDayOfMonth (.getActualMaximum cal java.util.Calendar/DAY_OF_MONTH)
        year (.get cal java.util.Calendar/YEAR)
        month (.get cal java.util.Calendar/MONTH)
        ]
    (map createLine
         (map (fn [day] (list year month day cal))
              (range firstDayOfMonth (+ 1 lastDayOfMonth))))))


(defn -main
  "Create a list of dates and concomittant weekdays"
  [& args]
  (if (= (count args) 2)
    (println (line-calendar (first args) (nth args 1)))
    (pprint (line-calendar "2017" "09"))))
