Ringo
=====

A starterkit for building apps with Garden, Ring, Core.Async, and Om.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [Goals](#goals)
- [Usage](#usage)
  - [Quickstart](#quickstart)
  - [Project Structure](#project-structure)
  - [Setup](#setup)
  - [Env Vars](#env-vars)
  - [REPL](#repl)
  - [Development](#development)
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

## Usage
	
### Quickstart

Seasoned Clojure/Clojurescript developers may install the template like so:

	lein new ringo kickstart
	cd kickstart
	lein dev

You may still want to read the setup below that covers the newer repl/brepl and advanced cljs compilation notes.

### Project Structure

After running `lein new ringo kickstart` the project structure looks like this:

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
			├── components.cljs
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
   

Rather than placing every source file under `src`, this project structure provides clear
separation of concerns with namespaces named to reflect a clear goal.

### Setup

First-time Clojure/Clojurescript developers, assuming you have [jdk7](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or above, install leiningen: a build and task runner similar to mvn, pip, or npm.

    brew install leiningen

Add the following to your `bash_profile`:

    export LEIN_FAST_TRAMPOLINE=y
    alias cljsbuild="lein trampoline cljsbuild $@"

Compile ClojureScript ahead of time [AOT](http://swannodette.github.io/2014/12/22/waitin/) instead of compiling for each build:

	./scripts/compile_cljsc

Optionally, improve Jvm launch times with [drip](https://github.com/ninjudd/drip)

    brew install drip

Initialize drip by running [lein-drip](https://github.com/josteink/lein-drip) once as:

	lein drip

### Env Vars

Environment variables are managed with [environ](https://github.com/weavejester/environ). For example, add the following in
your local `~/.lein/profiles.clj` to enable twitter oAuth.

- :twitter-api-key
- :twitter-api-secret

### REPL

There are two repls.

1. A Node REPL bundled with Clojurescript (requires rlwrap) provides the fastest repl environment without a browser context:

		./scripts/repl

2. While it is possible to code live with the Clojurescript's in-built [brepl](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment), figwheel provides a deeper experience by providing an
interactive environment for [reloadable-code](https://github.com/bhauman/lein-figwheel#writing-reloadable-code). 

Ringo bundles figwheel, which embeds cljs brepl, and can be started with `lein dev` (see below)

### Development

Start figwheel, garden/css watches, a ring api server, and auto reload classes/css on the fly

    lein dev

Generate optimized assets (css, js) into `dist`

	lein release

Deploy ui assets to `dist`.

	lein deploy

Deploy entire app into uberjar

	lein uberjar

## Plugins

**Visuall Debugger**

With Magnar's [Prone](https://github.com/magnars/prone), exceptions and ring errors can be visually inspected.

![](doc/img/browser-debug.png)

**Clojure Formatting**

James Reeves created another excellent [library](https://github.com/weavejester/cljfmt) that formats source code.

	lein format

**Static Code Analyzer**

Both kibit and eastwood are integrated as plugins, and can be invoked like this

	lein analyze

## Editors

### Lighttable

[Lighttable](http://www.lighttable.com) is a great editor for beginner Clojure/Clojusrescript developers. I recommend the
following plugins: Gitlight, Paredit, Bracketglow, Emmet. Enable **live coding** by evaluating Clojurescript code
by _connecting_ "External Browser", and including the websocket js src shown by Lighttable.

### Emacs/Cider

Emacs is recommended for those looking beyond Lighttable. If you're new to Emacs, checkout [Cider-light](https://github.com/priyatam/cider-light), my guide and Cider config for those new to Emacs. 

## Thanks

A big thanks to @swannodette for pushing the limits of Clojurescript and Om, @weavejester for creating beautiful libraries
like Ring and Compojure, and Joel Holbrooks for garden and secretary. Some sample components are taken from
[Om Cookbook](https://github.com/annapawlicka/om-cookbookhttps://github.com/omcljs/om-cookbook).

## Status

0.2.x

Future roadmap includes stabilizing the core, improving the lein template experience, and adding developer guides. An ideal
reference app would be similar to the likes of [Flask-Heroku](https://github.com/zachwill/flask_heroku)
and [Express](https://github.com/madhums/node-express-mongoose-demo).

TODO:

- Asset pipeline with [Optimus](https://github.com/magnars/optimus)
- Om Components showcase
- AWS/Heroku integration

[0.1.x versions](https://github.com/priyatam/ringo/tree/hybrid) included a Less workflow with plain old cljbuld autos.

## License

Copyright © 2015 Priyatam Mudivarti.

Released under the Eclipse Public License, same as Clojure.
