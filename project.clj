(defproject org.clojars.tomjkidd/carafe  "0.1.0-SNAPSHOT"
  :description "A library to carry functions to aid survival"
  :url "https://github.com/tomjkidd/carafe"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]]
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :plugins [[lein-npm "0.6.1"]
            [lein-cljsbuild "1.1.4"]]
  :npm {:dependencies [[source-map-support "0.4.0"]]}
  :source-paths ["src" "target/classes"]
  :clean-targets ["out" "release"]
  :target-path "target"

  :cljsbuild {
    :builds [{:id "none"
              :source-paths ["src"]
              :compiler {
                 :output-to "carafe.js"
                 :output-dir "out"
                 :optimizations :none
                 :source-map true}}]})
