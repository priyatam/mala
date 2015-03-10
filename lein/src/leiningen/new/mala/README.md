{{name}}
========

FIXME: One-line description of your project.

## Setup

Read [this](https://github.com/priyatam/mala/blob/master/README.md) for setup and editor support.

## Development

Start figwheel, watch garden, start a ring api server, and auto reload cljs/cljs/css on the fly

	lein dev

After a succesfull build, open/refresh `http://localhost:3449/`; you will see a Cljs brepl on the prompt

Bundle entire app into an uberjar

	lein prod

Assuming you installed [foreman](https://github.com/ddollar/foreman), test the app before going live

	foreman start
	
Checkout `http://localhost:5000/`

Format your source code using [cljfmt](https://github.com/weavejester/cljfmt)

	lein format

Both kibit and eastwood are integrated as plugins, and can be invoked like this

	lein analyze

## Credits

This project template is generated with [Mala](https://github.com/priyatam/mala).

## Status

FIXME.

## License

Copyright © 2015 FIXME

Released under the Eclipse Public License, same as Clojure.
