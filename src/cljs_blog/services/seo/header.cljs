(ns cljs-blog.services.seo.header)

; File handles the title concerns for a page

(defn set-page-title [title]
  (set! (. js/document -title) title))

(defn set-page-meta-description [desc]
  (-> js/document
    (.getElementsByTagName "meta")
    (.namedItem "description")
    (.setAttribute "content" desc)))
