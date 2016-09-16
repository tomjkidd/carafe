(defproject org.clojars.tomjkidd/carafe  "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "A library to carry functions to aid survival"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "https://github.com/tomjkidd/carafe"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [lein-doo "0.1.7"]]
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :plugins [[lein-npm "0.6.1"]
            [lein-cljsbuild "1.1.4"]
            [lein-doo "0.1.7"]]
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
                 :source-map true}}
             {:id "test-build"
              :source-paths ["src" "test"]
              :compiler {:output-to "resources/public/js/testable.js"
                         :main carafe.test-runner
                         :optimizations :none}}]}
  :doo {:build "test-build"})
