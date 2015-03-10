Mala Installation
=================

## Setup

First-time Clojure/Clojurescript developers, install [jdk8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
and leiningen (a build and task runner similar to mvn, pip, or npm):
 
	brew install leiningen
 
Add the following to your `bash_profile` to improve lein launch times

    export LEIN_FAST_TRAMPOLINE=y
    alias cljsbuild="lein trampoline cljsbuild $@"

Compile ClojureScript ahead of time with [AOT](http://swannodette.github.io/2014/12/22/waitin/)

    chmod 755 compile-cljsc && compile-cljsc

Environment variables are managed with [environ](https://github.com/weavejester/environ). For example add the
following in your local `~/.lein/profiles.clj` to enable twitter oAuth.

- :twitter-api-key
- :twitter-api-secret

Optionally, improve Jvm launch times with [drip](https://github.com/ninjudd/drip). Add [lein-drip](https://github.com/josteink/lein-drip)
in `~/.lein/profiles.clj` and initialize drip by running

    brew install drip
    lein drip
	
## Editors
	
### Emacs/Cider

Starting with 0.2.5, you can [enable an nrepl](https://github.com/bhauman/lein-figwheel/wiki/Using-the-Figwheel-REPL-within-NRepl)
connection to a running figwheel.

	M-x cider-connect
	<enter> localhost:7888
	(use 'figwheel-sidecar.repl-api)
	(cljs-repl)

You can now open any cljs file and start evaluating code in Emacs.

If you're new to Emacs, checkout [Cider-light](https://github.com/priyatam/cider-light), my guide and Cider
config for those new to Emacs.

### Lighttable

[Lighttable](http://www.lighttable.com) is a great editor for beginner Clojure/Clojusrescript developers.
I recommend the following plugins: Gitlight, Paredit, Bracketglow, Emmet. Enable **live coding** by evaluating
Clojurescript code by _connecting_ "External Browser", and including the websocket js src shown by Lighttable.

### IntelliJ/Cursive

TODO
