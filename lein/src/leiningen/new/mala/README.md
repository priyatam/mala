{{name}}
========

FIXME: One-line description of your project.

## Setup

First-time Clojure/Clojurescript developers, assuming you have [jdk7](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or above, install leiningen (a build and task runner similar to mvn, pip, or npm).
 
    brew install leiningen

Add the following to your `bash_profile` to improve lein launch times

    export LEIN_FAST_TRAMPOLINE=y
    alias cljsbuild="lein trampoline cljsbuild $@"

Compile ClojureScript ahead of time with [AOT](http://swannodette.github.io/2014/12/22/waitin/), instead of compiling for each build

	chmod 755 compile_cljsc
	compile_cljsc

Environment variables are managed with [environ](https://github.com/weavejester/environ). For example add the
following in your local `~/.lein/profiles.clj` to enable twitter oAuth.

- :twitter-api-key
- :twitter-api-secret

## Development

While it is possible to code live with the Clojurescript's in-built [brepl](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment), figwheel provides a deeper experience by providing an
interactive environment for [reloadable-code](https://github.com/bhauman/lein-figwheel#writing-reloadable-code) and css.
Ringo bundles figwheel to simplify the repl-driven development.

Start figwheel, watch garden, start a ring api server, and auto reload cljs/cljs/css on the fly

	lein dev

After a succesfull build, open/refresh `http://localhost:3449/`; you will see a Cljs brepl on the prompt

Bundle entire app into an uberjar

	lein prod

Assuming you installed [foreman](https://github.com/ddollar/foreman), test the app before going live

	foreman start
	
Checkout `http://localhost:5000/`

Format your source code using [cljfmt](https://github.com/weavejester/cljfmt)

	lein format

Both kibit and eastwood are integrated as plugins, and can be invoked like this

	lein analyze

## Editors

### Lighttable

[Lighttable](http://www.lighttable.com) is a great editor for beginner Clojure/Clojusrescript developers.
I recommend the following plugins: Gitlight, Paredit, Bracketglow, Emmet. Enable **live coding** by evaluating
Clojurescript code by _connecting_ "External Browser", and including the websocket js src shown by Lighttable.

### Emacs/Cider

Emacs is recommended for those looking beyond Lighttable. If you're new to Emacs, checkout
[Cider-light](https://github.com/priyatam/cider-light), my guide and Cider config for those new to Emacs. 

## Thanks

The project templates is generated with [Mala](https://github.com/priyatam/mala).

## Status

FIXME.

## License

Copyright © 2015 FIXME

Released under the Eclipse Public License, same as Clojure.
