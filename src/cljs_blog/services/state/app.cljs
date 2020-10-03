(ns cljs-blog.services.state.app
  (:require [reagent.core :as reagent :refer [atom]]))


(defonce app-state (atom {:text "Hellow World"
                          :post nil}))



(defn get-from-state [key]
  "returns the value for a specified key from state"
  ((keyword key) @app-state))
