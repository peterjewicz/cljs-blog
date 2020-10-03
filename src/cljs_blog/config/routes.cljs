(ns cljs-blog.config.routes
  (:require [cljs-blog.components.pages.home :refer [Home]]
            [cljs-blog.components.pages.posts :refer [Posts]]
            [cljs-blog.components.pages.post :refer [Post]]
            [reitit.frontend.controllers :as rfc]
            [cljs-blog.services.state.dispatcher :refer [handle-state-change]]
            [cljs-blog.posts.post-handler :refer [post_list]]))


(def routes
  [["/"
    {:name ::home
     :view Home
     :public? true}]


   ["/posts"
     {:name ::posts
      :view Posts
      :public? true}]

   ["/post/:slug"
     {:name ::post
      :view Post
      :controllers [{:params (fn [match] (get-in match [:path-params :slug]))
                     :start  (fn [slug] (handle-state-change {:type "set-current-post" :value ((keyword slug) post_list)}))}]}]])

