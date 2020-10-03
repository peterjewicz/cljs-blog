(ns ^:figwheel-hooks cljs-blog.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [reitit.coercion :as rc]
   [reitit.coercion.spec :as rss]
   [cljs-blog.config.routes :refer [routes]]
   [cljs-blog.services.state.app :refer [app-state]]))

;; define your app data so that it doesn't get over-written on reload
; TODO move our app-state
(defonce match (atom nil)) ; this is our current page - we define it here outside our normal data flow

(defn Main [app-state] ; Our base to hold the shell of our application - probably move this once it gets bigger
  [:div
   (if @match
     (let [view (:view (:data @match))]
       [view app-state]))])

(defn init! []
  (rfe/start!
    (rf/router routes {:data {:coercion rss/coercion}})
    (fn [new-match]
      (swap! match
        (fn [old-match]
          (when new-match
            (assoc new-match :controllers (rfc/apply-controllers (:controllers old-match) new-match))))))

    ;; set to false to enable HistoryAPI
    {:use-fragment true})
  (rdom/render [Main app-state] (.getElementById js/document "app")))

(init!)







