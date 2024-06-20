(ns foobar.main
  (:gen-class))

(def dataframes [
    {:index  0 :top 1840 :data "Message" :name nil}
    {:index  1 :top 1540 :data "How did you hear about us?" :name nil}
    {:index  3 :top  340 :data "Last Name" :name nil}
    {:index  4 :top  640 :data "Phone Number" :name nil}
    {:index  6 :top   30 :data "First Name" :name nil}
    {:index  8 :top 3750 :data "Deadline?" :name nil}
    {:index 10 :top  940 :data "E-mail" :name nil}
    {:index 11 :top 3750 :data nil :name "deadline"}
    {:index 12 :top 940  :data nil :name "email"}
    {:index 13 :top 30   :data nil :name "first_name"}
    {:index 14 :top 340  :data nil :name "last_name"}
    {:index 15 :top 1840 :data nil :name "message"}
    {:index 16 :top 640  :data "^" :name "phone_number"}
    {:index 17 :top 1540 :data "~" :name "referral"}
])

(defn- get-peers [frame]
    (filter (fn [x] (not= (frame :index) (x :index))) dataframes))

(defn- find-neighbor [frame]
    (let [peers    (get-peers frame)
          neighbor (apply min-key #(abs (- (frame :top) (% :top))) peers)]
    (format "%s -> %s" (frame :name) (neighbor :data))))

(defn -main [& args]
  (let [input  (filter #(some? (% :name)) dataframes)
        result (map find-neighbor input)]
        (run! println result)))
