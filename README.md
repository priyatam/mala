Ringo
=====

A starterkit for building apps with Garden, Ring, and Om, optimized for new Clojure/Clojurescript developers.

## Rationale

The Clojure community builds libraries that do one thing and one thing only, and justifiably so. However, newcomers to the community — especially those coming from the world of Express, Flask, or Sinatra could benefit from a curated web stack composed of mature libraries and middleware to get things done.

Ringo is an effort to realize that core.

    lein new ringo kickstart

## Goals

Clojurescript _all_ the way.

- Curated Ring middleware
- Separate workflows for Design, UI, and API development
- Integrated asset pipeline in leiningen
- Live reloading and browser repl, with [figwheel](https://github.com/bhauman/lein-figwheel)
- Sane project structure and namespaces
- Static and _async_ server, with [http-kit](http://www.http-kit.org)
- OAuth2 workflows
- Ajax & core.async
- Error handling in the browser
- nRepl over Http, with [drawbridge](https://github.com/cemerick/drawbridge)
- and more ...

Core dependencies: **Ring, Om, Garden**.

## Usage

### Setup

First-time Clojure developers, assuming you have [jdk7 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), must first install leiningen (a build and task runner similar to mvn, pip, or npm):

    brew install leiningen

Checkout new leiningen template (a boilerplate/generator for new projects)

    lein new ringo kickstart

The project structure looks like this:

    ├── api
    │   └── kickstart
    │       ├── db.clj
    │       ├── router.clj
    │       ├── server.clj
    │       └── utils.clj
	├── design
	│   └── kickstart
	│       ├── components.clj
	│       ├── layout.clj
	│       └── typography.clj
	├── resources
	│   ├── data
	│   │   └── population.csv
	│   └── public
	│       ├── 404.html
	│       ├── 500.html
	│       ├── img
	│       ├── index.html
    └── ui
        └── kickstart
            ├── client.cljs
            ├── components.cljs
            └── utils.cljs
    ├── bower.json
    ├── project.clj

Run the server, auto reload classes, compile less files and cljs->js, all _in one go_.

    lein dev

Generate optimized assets (css, js)

	lein release

Optionally, add the following ENV variables using [environ](https://github.com/weavejester/environ) in your local, `~/.lein/profiles.clj` to enable twitter oAuth.

- :twitter-api-key
- :twitter-api-secret

## Deployment

Deploy user interface assets to `dist`.

	lein deploy

Build and deploy uberjar to heroku

	TODO

### Advanced Setup

First-time Clojurescript developers, add the following to your bash .profile:

    export LEIN_FAST_TRAMPOLINE=y
    alias cljsbuild="lein trampoline cljsbuild $@"

To avoid compiling ClojureScript for each build, AOT Clojurescript locally in your project with the following:

	lein trampoline run -m clojure.main -e "(compile 'cljs.repl.node) (compile 'cljs.repl.browser) (compile 'cljs.core)"

Improve Jvm launch times with [drip](https://github.com/ninjudd/drip)

    brew install drip

Initialize drip by running [lein-drip](https://github.com/josteink/lein-drip) once as:

    lein drip

Read drip's settings for more info.

## Visuall Debugger

Thanks to Magnar's [Prone](https://github.com/magnars/prone), Exceptions and Ring errors can be visually inspected:

## Editor support

### Lighttable

[Lighttable](http://www.lighttable.com) is the best editor for beginner Clojure/Clojusrescript developers. I recommend the following plugins:

- Bracketglow
- Emmet
- Gitlight
- Paredit

Enable **live coding** by evaluating Clojurescript code by _connecting_ "External Browser", and including the websocket js src shown by Lighttable.

## Emacs/Cider

Emacs is recommended for those looking beyond Lighttable. If you're new to Emacs, checkout [Cider-light](https://github.com/priyatam/cider-light), my guide and Cider config to migrate from Lighttable. 

## Thanks

A big thanks to @weavejester for creating beautiful libraries like Hiccup, Ring and Compojure; @swannodette for pushing the limits of Clojurescript and Om.

Some examples are taken from [Om Cookbook](https://github.com/annapawlicka/om-cookbookhttps://github.com/omcljs/om-cookbook).

## Status & Roadmap

**Early development**.

The organizational structure is subject to change.

Future roadmap includes stabilizing the core, improving the lein template experience, and adding developer guides. An ideal reference app would be similar to the likes of [Flask-Heroku](https://github.com/zachwill/flask_heroku) and [Express](https://github.com/madhums/node-express-mongoose-demo).

The following tasks are on my priority list. Your feedback is much appreciated!

- Frontend asset pipeline with [Optimus](https://github.com/magnars/optimus)
- Core.async integration
- Om Components showcase

## CONTRIBUTING

The lein template is generated from [here](https://github.com/priyatam/ringo-template). The template is a subset of the ringo reference app.

Contributing guidlines will be added soon.

## License

Copyright © 2014 Priyatam Mudivarti

Released under the Eclipse Public License, same as Clojure.
