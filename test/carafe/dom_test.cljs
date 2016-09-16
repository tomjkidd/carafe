(ns carafe.dom-test
  (:require [carafe.dom :as dom]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(deftest test-pluck-by-id
  "Ensure that an element can be found by id"
  (let [e (dom/create-element [:div
                                {}
                                [
                                 [:div {:id "div-1"} []]
                                 ]
                                ])]
    (is (not (nil? (dom/pluck "#div-1" e))))))
