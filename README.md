Ringo
=====

A starterkit for building apps with Garden, Ring, and Om. Optimized for new Clojure/Clojurescript developers.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Rationale](#rationale)
- [Usage](#usage)
  - [Quickstart](#quickstart)
  - [Setup](#setup)
  - [Env-vars](#env-vars)
  - [REPL/BREPL](#replbrepl)
  - [Development](#development)
  - [Deployment](#deployment)
  - [Advanced](#advanced)
- [Plugins](#plugins)
- [Project structure](#project-structure)
- [Editors](#editors)
  - [Lighttable](#lighttable)
  - [Emacs/Cider](#emacscider)
- [Thanks](#thanks)
- [Status](#status)
- [License](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Rationale

The Clojure community builds libraries that do one thing and one thing only, and justifiably so. However, newcomers to the
community — especially those coming from the world of Express, Flask, or Sinatra could benefit from curated libraries and
middleware to get things done.

Ringo is an effort to realize that core.

**Goals**

- curated Ring middleware
- separate workflows for _design_, _ui_, and _api_
- live reloading and browser repl, with [figwheel](https://github.com/bhauman/lein-figwheel)
- sane project structure and namespaces
- core.async
- oAuth2 workflows
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

### Setup

First-time Clojure/Clojurescript developers, make sure you installed [jdk7 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

Then install leiningen, a build and task runner similar to mvn, pip, or npm:

    brew install leiningen

Add the following to your bash .profile:

    export LEIN_FAST_TRAMPOLINE=y
    alias cljsbuild="lein trampoline cljsbuild $@"

To avoid compiling ClojureScript for each build, AOT Clojurescript locally in your project with:

	./scripts/compile_cljsc

### Env-vars

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

The project comes bundled with the figwheel, which comes with an embedded cljs brepl, and can be started with `lein dev` (see below)

### Development

Start figwheel, garden/css watches, a ring api server, and auto reload classes/css on the fly

    lein dev

Generate optimized assets (css, js) into `dist`

	lein release

Deploy user interface assets to `dist`.

	lein deploy

### Deployment

There are two modes for deployment:

- compile cljs, css3 into static assets and distribute them to any static server
- compile them into uberjar, with the provided ring server

TODO

### Advanced

Improve Jvm launch times with [drip](https://github.com/ninjudd/drip)

    brew install drip

Initialize drip by running [lein-drip](https://github.com/josteink/lein-drip) once as:

    lein drip

## Plugins

**Visuall Debugger**

With Magnar's [Prone](https://github.com/magnars/prone), exceptions and ring errors can be visually inspected.

**Clojure Formatting**

James Reeves created another excellent [library](https://github.com/weavejester/cljfmt) that formats source code.

	lein format

**Static Code Analyzer**

Both kibit and eastwood are integrated as plugins, and can be invoked like this

	lein analyze

## Project structure

After running `lein new ringo kickstart` the project structure looks like this:

    ├── api
    │   └── kickstart
    │       ├── db.clj
    │       ├── router.clj
    │       ├── server.clj
    │       └── utils.clj
	├── design
	│   └── kickstart
	│       ├── components.clj
	│       ├── layout.clj
	│       └── typography.clj
	├── env
	│   └── dev.cljs
	├── resources
	│   ├── data
	│   └── public
	│       ├── 404.html
	│       ├── 500.html
	│       ├── img
	│       ├── index.html
    ├── tasks
	│   └── leiningen
	│       └── tasks.clj
	└── ui
        └── kickstart
            ├── client.cljs
            ├── components.cljs
            └── utils.cljs
    ├── bower.json
    ├── project.clj

Rather than placing every source file under `src`, this project structure provides clear separation of concerns. Within
each folder, namespaces are named accordingly to reflect a clear goal.

## Editors

### Lighttable

[Lighttable](http://www.lighttable.com) is a great editor for beginner Clojure/Clojusrescript developers. I recommend the
following plugins: Gitlight, Paredit, Bracketglow, Emmet. Enable **live coding** by evaluating Clojurescript code
by _connecting_ "External Browser", and including the websocket js src shown by Lighttable.

### Emacs/Cider

Emacs is recommended for those looking beyond Lighttable. If you're new to Emacs, checkout [Cider-light](https://github.com/priyatam/cider-light), my guide and Cider config for those new to Emacs. 

## Thanks

A big thanks to @weavejester for creating beautiful libraries like Hiccup, Ring and Compojure; @swannodette for pushing the
limits of Clojurescript and Om. Some examples are taken from [Om Cookbook](https://github.com/annapawlicka/om-cookbookhttps://github.com/omcljs/om-cookbook).

## Status

**Early development**.

Future roadmap includes stabilizing the core, improving the lein template experience, and adding developer guides. An ideal
reference app would be similar to the likes of [Flask-Heroku](https://github.com/zachwill/flask_heroku)
and [Express](https://github.com/madhums/node-express-mongoose-demo).

TODO:

- Frontend asset pipeline with [Optimus](https://github.com/magnars/optimus)
- Om Components showcase
- Docker integration

## License

Copyright © 2015 Priyatam Mudivarti.

Released under the Eclipse Public License, same as Clojure.
