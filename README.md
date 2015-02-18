Ringo
=====

A starterkit for building apps with Garden, Ring, Core.Async, and Om.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [Goals](#goals)
- [Quickstart](#quickstart)
- [Setup](#setup)
- [REPL/BREPL](#replbrepl)
- [Plugins](#plugins)
- [Editors](#editors)
  - [Lighttable](#lighttable)
  - [Emacs/Cider](#emacscider)
- [Thanks](#thanks)
- [Status](#status)
- [License](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Goals

Ringo is a reference om/ring/garden app and leiningen template that demonstrates the following:

- separate workflows for _design_, _ui_, and _api_
- sane project structure and namespaces
- live reloading and browser repl, with [figwheel](https://github.com/bhauman/lein-figwheel)
- curated ring middleware
- core.async, instead of ajax
- static and async server, with [http-kit](http://www.http-kit.org)
- integrated asset pipeline in leiningen
- bower integration
- curated lein plugins
- leiningen tasks

## Quickstart

Seasoned Clojure/Clojurescript developers may install the template, like so:

	lein new ringo kickstart
	cd kickstart
	lein dev

Checkout `http://localhost:3449/`

The generated project structure looks like this:

    ├── api
    │   └── kickstart
    │       ├── router.clj
    │       ├── server.clj
    │       └── utils.clj
	├── design
	│   └── kickstart
	│       ├── components.clj
	│       ├── layout.clj
	│       └── typography.clj
	└── ui
		└── kickstart
			├── client.cljs
			├── components
			│   ├── graph.cljs
			│   └── typeahead.cljs
			├── main.cljs
			├── pages.cljs
			├── router.cljs
			├── state.cljs
			├── types.cljs
			└── utils.cljs
	├── resources
	│   ├── data
	│   └── public
	│       ├── img
	│       ├── index.html
   

This structure provides a clear separation of concerns with namespaces named to reflect goals. You may
still want to read the setup below that covers the newer repl/brepl and advanced cljs compilation notes.

## Setup

First-time Clojure/Clojurescript developers, assuming you have [jdk7](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or above, install leiningen (a build and task runner similar to mvn, pip, or npm).

    brew install leiningen

Add the following to your `bash_profile` to improve lein launch times

    export LEIN_FAST_TRAMPOLINE=y
    alias cljsbuild="lein trampoline cljsbuild $@"

Compile ClojureScript ahead of time with [AOT](http://swannodette.github.io/2014/12/22/waitin/), instead of compiling for each build

	lein trampoline run -m clojure.main -e "(compile 'cljs.repl.node) (compile 'cljs.repl.browser) (compile 'cljs.core)"

Optionally, improve Jvm launch times with [drip](https://github.com/ninjudd/drip)

    brew install drip

Add [lein-drip](https://github.com/josteink/lein-drip) in `~/.lein/profiles.clj` and initialize drip by running

	lein drip

Environment variables are managed with [environ](https://github.com/weavejester/environ). For example add the
following in your local `~/.lein/profiles.clj` to enable twitter oAuth.

- :twitter-api-key
- :twitter-api-secret

## REPL/BREPL

While it is possible to code live with the Clojurescript's in-built [brepl](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment), figwheel provides a deeper experience by providing an
interactive environment for [reloadable-code](https://github.com/bhauman/lein-figwheel#writing-reloadable-code) and css.
Ringo bundles figwheel to simplify the repl-driven development.

Start figwheel, watch garden, start a ring api server, and auto reload cljs/cljs/css on the fly

	lein dev

Bundle entire app into an uberjar

	lein prod

Assuming you installed [foreman](https://github.com/ddollar/foreman), test the app before going live

	foreman start
	
Checkout `http://localhost:5000/`

## Plugins

**Visuall Debugger**

With Magnar's [Prone](https://github.com/magnars/prone), exceptions and ring errors can be visually inspected.

![](doc/img/browser-debug.png)

**Clojure Formatting**

James Reeves created an excellent [library](https://github.com/weavejester/cljfmt) that formats source code

	lein format

**Static Code Analyzer**

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

## References

There are several lein templates that offer a 'starterkit' of sorts for Cljs/Clj development. Here are some
popular templates:

- [mies](https://github.com/swannodette/mies)
- [mies-om](https://github.com/swannodette/mies-om)
- [chestnut](https://github.com/plexus/chestnut)

## Thanks

A big thanks to @swannodette for pushing the limits of Clojurescript and Om, @weavejester for creating beautiful
libraries like Ring and Compojure, Joel Holbrooks for garden and secretary, and Bhauman for fighweel!
Some sample components are taken from [Om Cookbook](https://github.com/annapawlicka/om-cookbookhttps://github.com/omcljs/om-cookbook).

## Status

0.2.x

Future roadmap includes stabilizing the core, improving the lein template experience, and adding developer guides.
An ideal reference app would be similar to the likes of [Flask-Heroku](https://github.com/zachwill/flask_heroku)
and [Express](https://github.com/madhums/node-express-mongoose-demo).

TODO:

- Integrate asset pipeline with [Optimus](https://github.com/magnars/optimus)
- Om Components showcase
- AWS/Heroku/Docker integration

[0.1.x versions](https://github.com/priyatam/ringo/tree/hybrid) included a Less workflow with plain old cljbuld autos.

## License

Copyright © 2015 Priyatam Mudivarti.

Released under the Eclipse Public License, same as Clojure.
