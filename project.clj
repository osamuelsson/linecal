(defproject linecal "0.1.0-SNAPSHOT"
  :description "Fiddle with a line-based calendar"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot linecal.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
