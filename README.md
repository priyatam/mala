Ringo
=====

![](docs/ringo.png)

A starterkit for building apps with Bootstrap, Om, and Ring. Optimized for beginner Clojure/Clojurescript developers.

## Goals

- separate workflows for design, ui, and services
- unified bower.json + profiles.clj build pipeline
- unified static server and _async_ webserver
- curated ring middleware
- oauth2
- nRepl over http!

_Core dependencies:_

Ring/Compojure, Om, Kioo, Secretary

Curated utilities from [prismatic](https://github.com/Prismatic/plumbing), [raynes](https://github.com/Raynes/fs), and [sam aaron](https://github.com/sritchie).

## Getting started

First-time Clojure developers, assuming you have [jdk7 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), must first install leiningen (a build and task runner similar to mvn, pip, or npm):

	brew install leiningen

Checkout project and download dependencies

	lein deps

Run server, auto reload classes, and compile cljs->js

	lein cljsbuild auto
	lein run

## Editor support

[Lighttable](http://www.lighttable.com) is currently the best editor for beginner Clojure/Clojusrescript developers. Use it with the following plugins:

- bracketglow
- emmet
- gitlight
- paredit and rainbow
- vim-mode

## Status

**Early development**.

The API and organizational structure are subject to change. Comments and contributions are much appreciated.

## Acknowledgements

- Om example component from the Om [Typeahead](https://github.com/swannodette/om/blob/master/examples/typeahead/src/core.cljs)
- 'Ringo' logo by [Matt Martini](http://mistermartini.tumblr.com).

## License

Copyright © 2014 Priyatam Mudivarti

Released under the Eclipse Public License, same as Clojure.
