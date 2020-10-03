(ns cljs-blog.components.pages.post)

(defn Post [app-state]
  [:div.Post
   [:h1 (:title (:post @app-state))]
   [:p (:content (:post @app-state))]])
