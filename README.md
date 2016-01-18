Mala
=====

![](resources/public/img/mala-cljs.jpg)

A UI template (and reference app) built on Om, with modular components, router,
async event handling, and responsive design with an emphasis on using Clojurescript
_all the way_.

## Features

- Project structure for design and ui components
- Integrated Om components
- Responsive Design with Clojurescript using [Mesh](https://github.com/facjure/mesh) (not bootstrap, not less/sass)
- Core.async
- Reloadable code with [Figwheel](https://github.com/bhauman/lein-figwheel#writing-reloadable-code)
- Routing with Secretary
- Mock api server with _real_ Compojure routes
- Curated [cljsjs](http://cljsjs.github.io) libraries
- Integrated [Ankha](https://github.com/noprompt/ankha) inspector, [Om-i](https://github.com/PrecursorApp/om-i) instrumentation
- Strictly [follows](http://swannodette.github.io/2014/12/22/waitin/) [Om](https://github.com/omcljs/om/blob/master/CHANGES.md) releases

## Quickstart

    lein new mala chant
    cd chant

The generated src looks like this:

	src
	├── layout
	│   ├── grid.clj
	│   ├── index.clj
	│   └── typography.clj
	└── ui
        ├── client.cljs
		├── components
		    ├── <comp>.cljs
			├── <comp-style>.cljs
		├── router.cljs
		├── state.cljs
		├── types.cljs
		└── utils.cljs
     	├── core.cljs
	env
	└── dev
       ├── mock.clj
       └── repl.cljs
	   └── debug.cljs

Start figwheel, watch garden, and a figwheel server integrated with
[mock router](https://github.com/priyatam/mala/blob/master/env/dev/mock.clj):

    lein dev

Open `http://localhost:3449/` and look for a Cljs brepl on the prompt.

Emacs Cider users can start a repl with the following commands

	M-x cider-connect <RET>
	localhost <RET>
	7888 <RET>
    (at the prompt ...)
	(use 'figwheel-sidecar.repl-api)
	(cljs-repl)

Ready to deploy? Generate an optimized js file

    lein release

## Wiki

For more info, see the [wiki](https://github.com/priyatam/mala/wiki).

## Leiningen Template

A lein-template based on this reference app is under `lein`. Significant changes
in the project structure and dependencies will be updated both in the reference
app and leiningen template.

For a production-ready Ring server template for building tiny services, see my other
template, [ring-micro](https://github.com/priyatam/ring-micro).

## Credits

A big thanks to @swannodette for Om, Joel Holbrooks for Garden, and Bhauman for
Fighweel. Some code samples were copied from:

- [goya](https://github.com/jackschaedler/goya)
- [typeahead](https://github.com/omcljs/om/blob/master/examples/typeahead/src/core.cljs)
- [om-cookbook](https://github.com/omcljs/om-cookbook)

## Status

[![Clojars Project](http://clojars.org/mala/lein-template/latest-version.svg)](http://clojars.org/mala/lein-template)

## License

Copyright © 2016 Priyatam Mudivarti.

Released under the Eclipse Public License, same as Clojure.
