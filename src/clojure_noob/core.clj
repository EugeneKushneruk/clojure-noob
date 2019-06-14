(ns clojure-noob.core
  (:gen-class))

;; "lein run" -> To run a code
;; "lein uberjar" -> This command creates the file target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar.
;; You can make Java execute it by running this:
;; "java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar"
;; "lein repl" -> To start a REPL

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn mimimi
  [name]
  (str name ", you are so mimimi!"))

(def pr1
  [45 75 50 45 95 35 45 45 30 45 30 45 45 45 95 50 45 45 55 30 25 125 35 50 50 50 50 10 45 35 60 5 45 50 5 60 45 85 25 45 50 45 45 25 90 50 50 45 50 40 50 30])

;; Function definitions are composed of five main parts:

;; 1. defn
;; 2. Function name
;; 3. A docstring describing the function (optional)
;; 4. Parameters listed in brackets
;; 5. Function body

(defn main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

;; Multiply passed values
(defn mul
  [arg & rest]
  (apply * arg rest))

;; Map vector (array) and increment each value +1
;; (Note that map doesn’t return a vector, even though we supplied a vector as an argument.
;; You’ll learn why in Chapter 4. For now, just trust that this is okay and expected.)
(defn my-map-inc
  []
  (map inc [1 2 3 4 5]))

;; Reduce vector (array) and get total value
(defn my-red
  []
  (reduce + [6 7 8 9 10]))

;; If cond
;; You can also omit the else branch.
;; If you do that and the Boolean expression is false, Clojure returns nil.
(defn my-if
  [bool]
  (if bool
    "By Zeus's hammer!"
    "By Aquaman's trident!"))

;; Vector
;; (get [3 2 1] 0) - 0 is the index of el
;; Can use (conj -websites "mysite.com") to add additional elements to the vector.
(def websites
  ["google.com" "attendify.com" "dou.ua"])

;; !IMPORTANT!
;; Map
;; Use (get my-map :first) to get value from a key
;; Also you can treat the map like a function - (my-map :first)
;; (get my-map :first) is the same as (:first my-map)
;; (:third my-map "Predifined value")
(def my-map
  {:first "Hey"
   :second " How are you?"})

;; Lists - '(1 2 3 4) => (1 2 3 4)
;; You can’t retrieve list elements with get
;; You can use the nth function:
;; (nth '(:a :b :c) 0) => :a
;; List values can have any type, and you can create lists with the list function:
;; (list 1 "two" {3 4}) => (1 "two" {3 4})
;; Elements are added to the beginning of a list:
;; (conj '(1 2 3) 4) => (4 1 2 3)

;; Sets
;; Sets are collections of unique values.
;; #{"kurt vonnegut" 20 :icicle}
;; You can also use hash-set to create a set:
;; (hash-set 1 1 2 2) => #{1 2}
;; You can also create sets from existing vectors and lists by using the set function:
;; (set [3 3 3 4 4]) => #{3 4}
;; Here’s how you’d use contains?:
;; (contains? #{:a :b} :a) => true
;; Notice that using get to test whether a set contains nil will always return nil, which is confusing.
;; contains? may be the better option when you’re testing specifically for set membership.

((or + -) 1 2 3)

;; Atom
(def my-atom (atom []))

(defn push-atom
  [value]
  (do
    (print "Update Atom! ")
    (swap! my-atom conj value)))

(defn reset-atom
  []
  (print "Atom has been reset ")
  (reset! my-atom []))

(defn random-id
  []
  (str (+ (rand-int 100) (rand))))

;; (push-atom (random-id))
;; (swap! my-atom rseq)
;; (reset-atom)

;; Multi-Arity
;; Samples:
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))

(defn weird-arity
  ([]
   "Destiny dressed you this morning, my friend, and now Fear is
   trying to pull off your pants. If you give up, if you give in,
   you're gonna end up naked with Fear just standing there laughing
   at your dangling unmentionables! - the Tick")
  ([number]
   (inc number)))

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

;;(codger "Billy" "Anne-Marie" "The Incredible Bulk")

;; Return the first element of a collection
(defn my-first
  [[first-thing]] ;Notice that first-thing is within a vector
  first-thing)

;(my-first ["oven" "bike" "war-axe"])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

;(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

;; Destructuring maps
(defn announce-treasure-location-one
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn announce-treasure-location-two
  [{:keys [lat lng] :as all}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

;; (announce-treasure-location- {:lat 28.22 :lng 81.33})

;; Passing anonymous fn to map
;; % - reader macros. You can distinguish them like this: %1, %2, %3, and so on. % is equivalent to %1
(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])

(#(str %1 " and " %2) "cornbread" "butter beans")
; => "cornbread and butter beans"

;; Returning Functions
;; By now you’ve seen that functions can return other functions.
;; The returned functions are closures, which means that they can access all the variables that were in scope when the function was created.
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))
;; (inc3 7) => 10

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

;; (symmetrize-body-parts asym-hobbit-body-parts)
;; (let [x 10 y 20] x) => 10 (x) - let is some kind of variable. let binds names to values.
;; (let [a 1 b 2] (+ a b))

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])

(let [dalmatians (take 2 dalmatian-list)] dalmatians)
;; => ("Pongo" "Perdita")

;; Using macros
(defn get-dogs
  []
  (->> (let [dogs (take 3 dalmatian-list)] dogs)
       (vec)))

;; pongo is assigned to the first value inside the vector. dalmatians is the rest.
(let [[pongo & dalmatians] dalmatian-list] [pongo dalmatians])

;; Loop sample
(defn example
  []
  (loop [x 10]
    (when (> x 1)
      (println x)
      (recur (- x 2)))))

(defn example-two
  []
  (loop [x 1]
    (when (< x 100)
      (println x)
      (recur (inc x)))))

(defn example-three
  []
  (loop [iteration 0]
    (println (str "Iteration " iteration))
    (if (> iteration 3)
      (println "Goodbye!")
      (recur (inc iteration)))))

;; You could in fact accomplish the same thing by just using a normal function definition:
(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

;; Regular expression
(re-find #"^left-" "left-eye")
; => "left-"

(re-find #"^left-" "cleft-chin")
; => nil

(re-find #"^left-" "wongleblart")
; => nil

;; Function composition!
;; first will + (add) all args (8 8 8) and then transforms the result into str
((comp str +) 8 8 8)

;; #(first (reverse %)) - alternative to (last [1 2 3]) => 3
(->> [1 2 3]
     (reverse)
     (first))

((comp first reverse) [1 2 3])

;; Returns the number of items in the collection. (count nil) returns 0.
;; Also works on strings, arrays, and Java Collections and Maps
;; P.S. Count will start counting from 1 unlike in nth fn
(count [1 2 3])

;; Returns penultimate item in the collection
(#(nth % (- (count %) 2)) [1 2 3 4])

(defn test-me [new-map [key val]] (print new-map))

(def sec-at (atom {:first {:second {:third "HI"}}}))

(def fmap {:id 1 :descr "hello"})
(def fmapcp {:id 1 :descr "hello"})
(def fmapcp2 {:id 1 :descr "hello "})
(def smap {:id 2 :descr "hello 2"})