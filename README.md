Ringo
=====

A starterkit for building apps with Bootstrap, Om, and Ring. Optimized for beginner Clojure/Clojurescript developers.

## Rationale

The Clojure community builds libraries that do one thing and one thing only, and justifiably so. However, newcomers to the community — especially those coming from the world of Express, Flask, or Sinatra could benefit from a curated web stack composed of mature libraries and middleware to get things done.

Ringo is an effort to realize that core.

    lein new ringo kickstart

## Goals

- Separate workflows for Design, UI, and API development
- Unified `bower.json` + `profiles.clj` build pipeline
- Static server and _async_ webserver (http-kit)
- Frontend asset pipeline
- Curated Ring middleware
- Ajax & core.async
- OAuth2 workflows
- nRepl over Http
- Error handling in the browser

  Thanks to Magnar's [Prone](https://github.com/magnars/prone), Exceptions and Ring errors can be visually inspected:

![](docs/img/browser-debug.png)

_Core dependencies_

Ring/Compojure, Om/Sablono, Bootstrap/Less

## Usage

### Setup

First-time Clojure developers, assuming you have [jdk7 or above](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), must first install leiningen (a build and task runner similar to mvn, pip, or npm):

    brew install leiningen

Checkout new leiningen template (a boilerplate/generator for new projects)

    lein new ringo kickstart

Run the server, auto reload classes, compile less files and cljs->js, all _in one go_.

    lein ringo

### Advanced Setup

Optinally, add the following ENV variables using [environ](https://github.com/weavejester/environ) in your local, `~/.lein/profiles.clj` to enable oAuth and AWS actions.

- :aws-access-key
- :aws-secret-key
- :s3-bucket
- :twitter-api-key
- :twitter-api-secret

For fast Jvm launching with [drip](https://github.com/ninjudd/drip), install

    brew install drip

Initialize drip by running [lein-drip](https://github.com/josteink/lein-drip) once as:

    lein drip

Read drip's settings for more info.

## Editor support

[Lighttable](http://www.lighttable.com) is the best editor for beginner Clojure/Clojusrescript developers. I recommend the following plugins:

- Bracketglow
- Emmet
- Gitlight
- Paredit and rainbow
- Vim-mode (if you're a Vim fan, like me)

## FAQ

TODO.

## Thanks

A big thanks to @weavejester for creating beautiful Clojure libraries like Hiccup, Ring and Compojure; @swannodette for pushing the limits of Clojurescript and Om.

Thanks to [Anna](https://github.com/annapawlicka/om-data-vis), whose modified Om example is used in this repo.

## Status & Roadmap

**Early development**. Feedback is appreciated.

The organizational structure is subject to change.

Future roadmap includes stabilizing the core, improving the lein template experience, and adding detailed docs with examples. An ideal reference app would be similar to the likes of [Flask](https://github.com/zachwill/flask_heroku) and [Express](https://github.com/madhums/node-express-mongoose-demo).

TODO:

- Integrate [Om-Bootstrap](https://github.com/racehub/om-bootstrap), as an alternate workflow to HTML5/Less
- Integrate [Garden](https://github.com/noprompt/garden)
- Frontend asset pipeline with [Optimus](https://github.com/magnars/optimus)
- Core.async integration

## License

Copyright © 2014 Priyatam Mudivarti

Released under the Eclipse Public License, same as Clojure.
