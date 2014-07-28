Ringo
=====

A starterkit for building single-page apps with Ring and Om.

## Goals

Optimize workflows for beginner Clojure/Clojurescript developers.

- separate design, ui, and services
- bower.json + profiles.clj
- leiningen task pipeline
- a _fast_ static server
- optimized jetty webserver
- curated ring middleware
- oauth2
- integration tests
- heroku integration
- nRepl over http!

_Core dependencies:_

- ring/compojure
- om, sablano, secretary

Curated utilities from [malloy](https://github.com/amalloy/useful), [raynes](https://github.com/Raynes/fs), and [prismatic](https://github.com/Prismatic/plumbing).

## Getting started

First-time Clojure developers, assuming you have [jdk7 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), must first install leiningen (a build and task runner similar to mvn, gradle, pip, or npm):

		brew install leiningen

Checkout project and download dependencies (from Maven)

   		lein deps

Open a repl

		lein repl

Run the server and auto reload classes and compile cljs->js

    		lein cljsbuild auto
   		lein run

## Editor support

[Lighttable](http://www.lighttable.com) is currently the best editor for beginner Clojure/Clojusrescript developers. Use it with the following plugins:

- emmet
- gitlight
- vim-mode for fans of Vim!
- paredit and rainbow
- bracketglow

## Status

**Early development**.

Currently, a playground to build quick prototypes. Roadmap includes curating utilities and best practices around non-trivial Ring/Om apps.

## Credits

- Openid example modified from [friend-demo](https://github.com/cemerick/friend-demo)
- Om example component from the Om [Typeahead](https://github.com/swannodette/om/blob/master/examples/typeahead/src/core.cljs) example

## License

Copyright © 2014 Priyatam Mudivarti

Released under the Eclipse Public License, same as Clojure.
