(ns cljs-blog.config.routes
  (:require [cljs-blog.components.pages.home :refer [Home]]
            [cljs-blog.components.pages.posts :refer [Posts]]
            [cljs-blog.components.pages.post :refer [Post]]
            [reitit.frontend.controllers :as rfc]
            [cljs-blog.services.state.dispatcher :refer [handle-state-change]]
            [cljs-blog.posts.post-handler :refer [post_list]]
            [cljs-blog.services.seo.header :as seo-header]))

(defn handle-page-load [post]
  (seo-header/set-page-title (:title post))
  (seo-header/set-page-meta-description (:meta-description post))
  (handle-state-change {:type "set-current-post" :value post}))

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
                     :start  (fn [slug] (handle-page-load ((keyword slug) post_list)))}]}]])

