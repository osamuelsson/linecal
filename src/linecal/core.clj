(ns linecal.core
  (:use [clojure.pprint :as pprint])
  (:gen-class))

;; TODO
;; Add a number to weekday mapper
;; Change this to a "gen"
;; Add a "sum", etc.

(defn createLine
  "creates one of the lines"
  [ ymd ]
  (let [[year month day cal] ymd]
    (.set cal year month day)
    (let [dayOfWeek (.get cal java.util.Calendar/DAY_OF_WEEK)]
      [year month day dayOfWeek])))

(defn line-calendar
  "Returns a list of the days in the month represented by the date and the day of week"
  [year month]
  (let [cal (.newInstance java.util.GregorianCalendar)
        firstDayOfMonth (.getActualMinimum cal java.util.Calendar/DAY_OF_MONTH)
        lastDayOfMonth (.getActualMaximum cal java.util.Calendar/DAY_OF_MONTH)
        year (.get cal java.util.Calendar/YEAR)
        month (+ 1 (.get cal java.util.Calendar/MONTH))
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
