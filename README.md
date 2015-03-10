Mala
=====

[![Join the chat at https://gitter.im/priyatam/mala](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/priyatam/mala?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

![](doc/img/mala-cljs.jpg)

## Goal

A reference app and Leiningen template for building SPA in Clojurescript with Garden, Ring, and Om.

## Features

- sane project structure
- workflows for api, design, and user-interface
- live [reloadable-code](https://github.com/bhauman/lein-figwheel#writing-reloadable-code) and brepl with figwheel
- static analyzers with [kibit](https://github.com/jonase/kibit) and [eastwood](https://github.com/jonase/eastwood) 
- curated ring [middleware](https://github.com/ring-clojure/ring-defaults)
- curated lein plugins
- curated cljsjs libraries
- [om-i](https://github.com/PrecursorApp/om-i) instrumentation 
- async server with [http-kit](http://www.http-kit.org)
- core.async integration, no ajax
- visual debugger with [prone](https://github.com/magnars/prone)
- asset pipeline and tasks in leinigen
- automatic sourcecode formatting with [cljfmt](https://github.com/weavejester/cljfmt)
- environment variables with [environ](https://github.com/weavejester/environ)
- [up](http://swannodette.github.io/2014/12/22/waitin/) to [date](https://github.com/omcljs/om/blob/master/CHANGES.md) dependencies!

## Quickstart

    lein new mala chant
    cd chant

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

Start figwheel, watch garden, and fire a ring api server for live coding:

    lein dev

Open/refresh `http://localhost:3449/` for a Cljs brepl on the prompt.

Ready to deploy? Generate an uberjar:

    lein prod

Test with [foreman](https://github.com/ddollar/foreman):

    foreman start
    
Preview staging `http://localhost:5000/`
	
## Documentation

A guide with installation, workflows, and best practices is under [doc](/doc).

## Leiningen Template

A minimal lein-template, based on this reference app, is under `lein`. Significant changes in the project structure and dependencies will be updated both in the reference app and leiningen template.

## Credits

A big thanks to @swannodette for pushing the limits of Clojurescript and Om, @weavejester for creating Ring, Joel Holbrooks for Garden, and Bhauman for Fighweel.

Best practices and code inspired and taken from:

- [circleci-frontend](https://github.com/circleci/frontend)
- [david nolen's blog](http://swannodette.github.io)
- [goya](https://github.com/jackschaedler/goya)
- [omchaya](https://github.com/sgrove/omchaya)
- [om-cookbook](https://github.com/omcljs/om-cookbook)

## Status

[![Clojars Project](http://clojars.org/mala/lein-template/latest-version.svg)](http://clojars.org/mala/lein-template)

An ideal reference app would be similar to the likes of [React-Starterkit](https://github.com/kriasoft/react-starter-kit), [Flask-Heroku](https://github.com/zachwill/flask_heroku), and [Express](https://github.com/madhums/node-express-mongoose-demo).

[0.1.x versions](https://github.com/priyatam/mala/tree/hybrid) included a Less workflow with plain old cljbuld.

## License

Copyright © 2015 Priyatam Mudivarti, and others.

Released under the Eclipse Public License, same as Clojure.
