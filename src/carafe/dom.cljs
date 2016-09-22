(ns carafe.dom
  (:require [goog.dom :as dom]))

(defn by-selector
  "Get all elements found by selector

  Optionally provide a root element to limit search"
  ([selector]
   (by-selector selector js/document))
  ([selector element]
   (.querySelectorAll element selector)))

(defn pluck
  "Get a single element using a selector"
  ([selector]
   (pluck selector js/document))
  ([selector element]
   (.querySelector element selector)))

(defn by-id
  "Get an element by id

  Optionally provide a root element to localize search"
  ([id]
   (by-id id js/document))
  ([id element]
   (.getElementById element (name id))))

(defn remove-children
  "Remove all children from element"
  [element]
  (while (.-firstChild element)
    (.removeChild element (.-firstChild element))))

(defn append
  "Append a child element to a parent element"
  [parent child]
  (.appendChild parent child))

(defn- init-element
  "Create an empty common element"
  [tag]
  (.createElement js/document (name tag)))

(defn- create-text-element
  "Create a Text node"
  [text]
  (.createTextNode js/document text))

(def svg-ns
  "The namespace for svg elements"
  "http://www.w3.org/2000/svg")

(def svg-elements
  "Valid SVG elements"
  #{:svg
    :g
    :path
    :circle
    :ellipse
    :line
    :polygon
    :polyline
    :rect

    :animate
    :animateColor
    :animateMotion
    :animateTransform

    :defs
    :symbol
    :use})

(def events
  "Valid events"
  #{:onkeydown :onkeypress :onkeyup

    :onmouseenter :onmouseleave :onmouseover :onmouseout
    :onmousedown :onmouseup :onmousemove
    :onclick :ondblclick :oncontextmenu
    :onwheel
    :onchange

    :ondragstart :ondrag :ondragend :ondragenter :ondragover :ondragleave :ondrop

    :onfocus :onblur

    :onresize :onscroll

    :oncut :oncopy :onpaste})

(defn- init-element-ns
  "Create an empty namespaced element"
  [ns tag]
  (.createElementNS js/document ns (name tag)))

(defn- sanitize-tag
  "Make sure a tag is a keyword, and all lower-case"
  [tag]
  (keyword (.toLowerCase (name tag))))

(defn- create-empty-element
  "Use tag to determine how to create an empty element"
  [tag]
  (let [stag (sanitize-tag tag)]
    (cond
      (contains? svg-elements stag) (init-element-ns svg-ns stag)
      :else (init-element stag))))

(defn process-attr
  "For element, set attribute named a-name to the value a-value"
  [element a-name a-value]
  (cond
    (contains? events a-name)
    (let [event-name (subs (name a-name) 2)]
      (.addEventListener element event-name a-value))
    :else (.setAttribute element (name a-name) (str a-value))))

(defn process-attrs
  "For a hashmap of named attributes, apply them to an element.

  This will use .addEventListener to register to known events.
  If not recognized as an event, .setAttribute is used to apply the value to the 
  element directly."
  [element attrs]
  (doall (map (partial process-attr element)
              (keys attrs)
              (vals attrs))))

(defn create-element
  "Create a DOM element based on a clojure data structure.

  Each element is represented by a vector, [tag attrs children]
  tag is a keyword representing the tag to create.
  attrs is a hashmap of attributes to apply to the element.
  children is a vector of elements, defined in the same way."
  [[tag attrs children]]
  (if (and (= :text tag)
           (string? children))
    (create-text-element children)
    (let [e (create-empty-element tag)
          cs (doall (map #(create-element %1) children))]
      (process-attrs e attrs)
      (when-not (nil? cs)
        (doall (map #(append e %1) cs)))
      e)))
