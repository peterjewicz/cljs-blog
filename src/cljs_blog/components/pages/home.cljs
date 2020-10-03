(ns cljs-blog.components.pages.home)

(defn Home [app-state]
  [:div.Home
   [:h1 (:text @app-state)]
   [:h3 "Edit this in src/cljs_blog/core.cljs and watch it change!"]])