Ringo
=====

A starterkit for building apps with Bootstrap, Om, and Ring. Optimized for beginner Clojure/Clojurescript developers.

## Overview

The Clojure community builds libraries that do one thing and one thing only, and justifiably so. However, newcomers to the community — especially those coming from the world of Express, Flask, or Sinatra could benefit from a curated web stack composed of mature libraries and middleware to get things done.

Ringo is an effort to realize that core.

	lein new ringo myapp

## Goals

- separate workflows for design, ui, and apis
- unified bower.json + profiles.clj build pipeline
- static server and _async_ webserver
- curated ring middleware
- oauth2
- nRepl over http!

_Core dependencies_

Ring/Compojure, Om/Sablono, Bootstrap/Less

Tools from [prismatic](https://github.com/Prismatic/plumbing).

## Getting started

First-time Clojure developers, assuming you have [jdk7 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), must first install leiningen (a build and task runner similar to mvn, pip, or npm):

	brew install leiningen

Checkout project and download dependencies

	lein deps

Run server, auto reload classes, and compile cljs->js

	lein ringo

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

- Om example component from the official Om [examples](https://github.com/swannodette/om/blob/master/examples/)

## License

Copyright © 2014 Priyatam Mudivarti

Released under the Eclipse Public License, same as Clojure.
