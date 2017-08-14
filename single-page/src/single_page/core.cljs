(ns single-page.core
    (:require
      [ajax.core :refer [POST]]
      [reagent.core :as r]))

;; -------------------------
;; Views

(def state (atom {:doc {} :saved? false}))
(defn set-value! [id value]
  (swap! state assoc :saved? false)
  (swap! state assoc-in [:doc id] value))

(defn get-value [id]
  (get-in @state [:doc id]))

(defn row [label & body]
  [:div.row
    [:div.col-md 2
      [:span label]]
    [:div.col-md-3 body]])


(defn list-item [id k v selections]
  (letfn [(handle-click! []
            (swap! selections update-in [k] not)
            (set-value! id (->> @selections
                                (filter second)
                                (map first))))]
    [:li {:class (str "list-group-item"
                      (if (k @selections) " active"))
          :on-click handle-click!}
      v]))

(defn save-doc []
  (POST (str js/context "/save")
        {:params (:doc @state)
         :handler (fn [_] (swap! state assoc :saved? true))}))

(defn selection-list [id label & items]
  (let [selections (->> items (map (fn [[k]] [k false])) (into {}) atom)]
    (fn []
      [:div.row
        [:div.col-md-2 
          [:span label]]
        [:div.col-md-5
          [:div.row
            (for [[k v] items]
              [list-item id k v selections])]]])))

(defn text-input [id label]
  [:div.row
    [:div.col-md-2
      [:span label]]
    [:div.col-md3
      [:input {:type "text" 
               :value (get-value id)
               :on-change #(set-value! id (-> % .-target .-value))
               :class "form-control"}]]])

(defn home []
  [:div
    [:div.page-header
      [:h1 "Reagent Form"]]
    [text-input :first-name "First name"]
    [text-input :last-name "Last name"]
    [selection-list :favarite-drinks "Favorite drinks"
      [:coffee "Coffee"]
      [:beer "Beer"]
      [:crab-juice "Crab juice"]]
    [:button {:type "submit"
              :class "btn btn-default"
              :on-click #(.log js/console (clj->js @state))}
      "Submit"]])

(defn home-page []
  (home))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
