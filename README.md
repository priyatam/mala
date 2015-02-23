Mala
=====

[![Join the chat at https://gitter.im/priyatam/mala](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/priyatam/mala?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

![](doc/img/mala-cljs.jpg)

## Goal

Provide an optimized starterkit and a reference app for coding _and_ designing Clojurescript apps with Garden, Ring, and Om.

## Features

- separate workflows for _design_, _ui_, and _api_
- sane project structure and namespaces
- live reloading and browser repl with [figwheel](https://github.com/bhauman/lein-figwheel)
- curated ring middleware
- curated lein plugins
- curated cljsjs libraries
- core.async
- static and async server, with [http-kit](http://www.http-kit.org)
- leiningen tasks
- asset pipeline in leinigen
- [up](http://swannodette.github.io/2014/12/22/waitin/) to [date](https://github.com/omcljs/om/blob/master/CHANGES.md)
dependencies, puhlease!

## Quickstart

Seasoned Clojure/Clojurescript developers may install the template, like so:

    lein new mala kickstart
    cd kickstart
    lein dev

Open `http://localhost:3449/`

The generated src looks like this:

    env
    ├── dev
    │  └── repl.cljs
    src
    ├── api
    │   ├── db.clj
    │   ├── router.clj
    │   ├── server.clj
    │   └── utils.clj
    ├── design
    │   ├── components.clj
    │   ├── layout.clj
    │   ├── styles.clj
    │   └── typography.clj
    └── ui
        ├── client.cljs
        ├── components
        │  ├── hello.cljs
        │  └── typeahead.cljs
        ├── main.cljs
        ├── pages.cljs
        ├── router.cljs
        ├── state.cljs
        ├── types.cljs
        └── utils.cljs

The project structure follows a simple naming convention, a clear separation of concerns with noun-based namespaces
that follow api, design, and ui.

## Setup

First-time Clojure/Clojurescript developers, assuming you have [jdk7](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or above, install leiningen (a build and task runner similar to mvn, pip, or npm).
 
    brew install leiningen

Add the following to your `bash_profile` to improve lein launch times

    export LEIN_FAST_TRAMPOLINE=y
    alias cljsbuild="lein trampoline cljsbuild $@"

Compile ClojureScript ahead of time with [AOT](http://swannodette.github.io/2014/12/22/waitin/), instead of compiling for each build

    chmod 755 compile-cljsc && compile-cljsc

Environment variables are managed with [environ](https://github.com/weavejester/environ). For example add the
following in your local `~/.lein/profiles.clj` to enable twitter oAuth.

- :twitter-api-key
- :twitter-api-secret

## Development

While it is possible to code live with the Clojurescript's in-built [brepl](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment), figwheel provides a deeper experience by providing an
interactive environment for [reloadable-code](https://github.com/bhauman/lein-figwheel#writing-reloadable-code) and css.

Mala bundles figwheel to simplify the repl-driven development.

Start figwheel, watch garden, start a ring api server and auto reload cljs/cljs/css on the fly

    lein dev

If the build is successful open/refresh `http://localhost:3449/`; you'll see a Cljs brepl on the prompt. Enjoy live coding!

Bundle entire app into an uberjar

    lein prod

Assuming you installed [foreman](https://github.com/ddollar/foreman), test the app before going live

    foreman start
    
Checkout `http://localhost:5000/`

James Reeves created an excellent [library](https://github.com/weavejester/cljfmt) that formats source code

    lein format

Both [kibit](https://github.com/jonase/kibit) and [eastwood](https://github.com/jonase/eastwood) are excellent
static code analyzers, and are integrated as plugin

    lein analyze

Visual Debugger: Magnar's [Prone](https://github.com/magnars/prone) lets you visualize exceptions and ring errors on the browser.

![](doc/img/browser-debug.png)

Optionally, improve Jvm launch times with [drip](https://github.com/ninjudd/drip)

    brew install drip

Add [lein-drip](https://github.com/josteink/lein-drip) in `~/.lein/profiles.clj` and initialize drip by running

    lein drip

## Lein Template

A minimal lein-template, based on the reference app present in this directory is under `lein`. Any significant changes in project structure and dependencies will be updated both in teh reference app and leiningen template, in parallel.

## Editors

### Lighttable

[Lighttable](http://www.lighttable.com) is a great editor for beginner Clojure/Clojusrescript developers.
I recommend the following plugins: Gitlight, Paredit, Bracketglow, Emmet. Enable **live coding** by evaluating
Clojurescript code by _connecting_ "External Browser", and including the websocket js src shown by Lighttable.

### Emacs/Cider

Emacs is recommended for those looking beyond Lighttable. If you're new to Emacs, checkout
[Cider-light](https://github.com/priyatam/cider-light), my guide and Cider config for those new to Emacs. 

## References

Other lein templates for Cljs/Clj development:

- [mies](https://github.com/swannodette/mies)
- [mies-om](https://github.com/swannodette/mies-om)
- [chestnut](https://github.com/plexus/chestnut)

Further examples of Om can be found at [om-cookbook](https://github.com/omcljs/om-cookbook).

## Thanks

A big thanks to @swannodette for pushing the limits of Clojurescript and Om, @weavejester for creating Ring,
Joel Holbrooks for garden and secretary, and Bhauman for fighweel.

Some sample components are taken from Om Cookbook.

## Status

0.2.0

An ideal reference app would be similar to the likes of [Flask-Heroku](https://github.com/zachwill/flask_heroku)
and [Express](https://github.com/madhums/node-express-mongoose-demo).

TODO:

- Replace figwheel with a minimal [Cljs brepl](https://github.com/swannodette/mies/commit/d2da9f032e6a6e23f4eb598b5131209bda5f04c1)
- Asset pipeline with [Optimus](https://github.com/magnars/optimus)
- AWS/Heroku/Docker integration
- Bootcljs?

[0.1.x versions](https://github.com/priyatam/mala/tree/hybrid) included a Less workflow with plain old cljbuld.

## License

Copyright © 2015 Priyatam Mudivarti.

Released under the Eclipse Public License, same as Clojure.
