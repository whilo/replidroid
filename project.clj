(defproject replidroid/replidroid "0.1.0-SNAPSHOT"
  :description "FIXME: Android project description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :global-vars {*warn-on-reflection* true}

  :source-paths ["src/clojure" "src"]
  :java-source-paths ["src/java"]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :plugins [[lein-droid "0.4.3"]]

  :dependencies [[org.clojure-android/clojure "1.7.0-r4"]
                 [neko/neko "4.0.0-alpha5"]
                 [org.clojure/core.async "0.2.374"]
                 #_[io.replikativ/kabel "0.1.5"]
                 #_[io.replikativ/replikativ "0.1.3-SNAPSHOT" :exclusions [http-kit
                                                                         org.clojure/clojurescript]]]
  :profiles {:default [:dev]

             :dev
             [:android-common :android-user
              {:dependencies [[org.clojure/tools.nrepl "0.2.12"]]
               :target-path "target/debug"
               :android {:aot :all-with-unused
                         :rename-manifest-package "io.replikativ.replidroid.debug"
                         :manifest-options {:app-name "replidroid (debug)"}}}]
             :release
             [:android-common
              {:target-path "target/release"
               :android
               {;; :keystore-path "/home/user/.android/private.keystore"
                ;; :key-alias "mykeyalias"
                ;; :sigalg "MD5withRSA"

                :ignore-log-priority [:debug :verbose]
                :aot :all
                :build-type :release}}]}

  :android {;; Specify the path to the Android SDK directory.
            ;; :sdk-path "/home/user/path/to/android-sdk/"

            ;; Try increasing this value if dexer fails with
            ;; OutOfMemoryException. Set the value according to your
            ;; available RAM.
            :dex-opts ["-JXmx4096M" "--incremental"]

            :target-version "15"
            :aot-exclude-ns ["clojure.parallel" "clojure.core.reducers"
                             "cider.nrepl" "cider-nrepl.plugin"
                             "cider.nrepl.middleware.util.java.parser"
                             "clojure.core.memoize"
                             "clojure.core.cache"
                             #"cljs-tooling\..+"]})
