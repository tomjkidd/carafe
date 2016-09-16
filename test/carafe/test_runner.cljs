(ns carafe.test-runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [carafe.dom-test]))

(doo-tests 'carafe.dom-test)
