Ringo
=====

A starterkit for building apps with Garden, Ring, and Om, optimized for new Clojure/Clojurescript developers.

## Rationale

The Clojure community builds libraries that do one thing and one thing only, and justifiably so. However, newcomers to the community — especially those coming from the world of Express, Flask, or Sinatra could benefit from curated libraries and middleware to get things done.

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
- curated lein plugins

## Usage

### Setup

First-time Clojure/Clojurescript developers (assuming you have [jdk7 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)), install leiningen (a build and task runner similar to mvn, pip, or npm):

    brew install leiningen

Create a new leiningen template (a generator for new projects)

    lein new ringo kickstart

The project structure looks like this:

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
    └── ui
        └── kickstart
            ├── client.cljs
            ├── components.cljs
            └── utils.cljs
    ├── bower.json
    ├── project.clj

Optionally, add the following env variables using [environ](https://github.com/weavejester/environ) in your local, `~/.lein/profiles.clj` to enable twitter oAuth.

- :twitter-api-key
- :twitter-api-secret

### Development

Start figwheel, garden/css watches, a ring api server, and auto reload classes/css on the fly

    lein dev

Generate optimized assets (css, js) into `dist`

	lein release

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

## Tools & Plugins

**Visuall Debugger**

With Magnar's [Prone](https://github.com/magnars/prone), exceptions and ring errors can be visually inspected.

**Clojure Formatting**

James Reeves created another excellent [library](https://github.com/weavejester/cljfmt) that formats source code.

	lein format

**Static Code Analyzer**

Both kibit and eastwood are integrated as plugins, and can be invoked like this

	lein analyze

## Editor support

### Lighttable

[Lighttable](http://www.lighttable.com) is the best editor for beginner Clojure/Clojusrescript developers. I recommend the following plugins: Gitlight, Paredit, Bracketglow, Emmet. Enable **live coding** by evaluating Clojurescript code by _connecting_ "External Browser", and including the websocket js src shown by Lighttable.

## Emacs/Cider

Emacs is recommended for those looking beyond Lighttable. If you're new to Emacs, checkout [Cider-light](https://github.com/priyatam/cider-light), my guide and Cider config to migrate from Lighttable. 

## Thanks

A big thanks to @weavejester for creating beautiful libraries like Hiccup, Ring and Compojure; @swannodette for pushing the limits of Clojurescript and Om. Some examples are taken from [Om Cookbook](https://github.com/annapawlicka/om-cookbookhttps://github.com/omcljs/om-cookbook).

## Status & Roadmap

**Early development**.

Future roadmap includes stabilizing the core, improving the lein template experience, and adding developer guides. An ideal reference app would be similar to the likes of [Flask-Heroku](https://github.com/zachwill/flask_heroku) and [Express](https://github.com/madhums/node-express-mongoose-demo).

TODO:

- Frontend asset pipeline with [Optimus](https://github.com/magnars/optimus)
- Om Components showcase
- Docker integration

## License

Copyright © 2015 Priyatam Mudivarti.

Released under the Eclipse Public License, same as Clojure.
