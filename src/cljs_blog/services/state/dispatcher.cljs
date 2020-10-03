(ns cljs-blog.services.state.dispatcher
  (:require [cljs-blog.services.state.app :refer [app-state]]))

(defmulti handle-state-change (fn [action] (:type action)))

(defmethod handle-state-change "set-current-post"
  [action]
  (swap! app-state conj {:post (:value action)}))
