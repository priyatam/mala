Ringo
=====

A starterkit for building apps with Bootstrap, Ring, and Om. Optimized for beginner Clojure/Clojurescript developers.

## Rationale

The Clojure community builds libraries that do one thing and one thing only, and justifiably so. However, newcomers to the community — especially those coming from the world of Express, Flask, or Sinatra could benefit from a curated web stack composed of mature libraries and middleware to get things done.

Ringo is an effort to realize that core.

    lein new ringo kickstart

## Goals

- Separate workflows for Design, UI, and API development
- Unified `bower.json` + `profiles.clj` build pipeline
- Static server and _async_ webserver (http-kit)
- Asset pipeline (LESS)
- Curated Ring middleware
- Ajax & core.async
- OAuth2 workflows
- nRepl over Http
- Error handling in the browser
- and more ...

Ringo is made up of the following core dependencies:

Ring/Compojure, Om/Sablono, Bootstrap/Less

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
    │   └── less
    │       └── layout.less
    ├── resources
    │   └── public
    │       └── index.html
    └── ui
        └── kickstart
            ├── client.cljs
            ├── components.cljs
            └── utils.cljs
    ├── bower.json
    ├── project.clj

Run the server, auto reload classes, compile less files and cljs->js, all _in one go_.

    lein ringo

Optionally, add the following ENV variables using [environ](https://github.com/weavejester/environ) in your local, `~/.lein/profiles.clj` to enable oAuth and AWS actions.

- :aws-access-key
- :aws-secret-key
- :s3-bucket
- :twitter-api-key
- :twitter-api-secret

## Deployment

To deploy to Heroku, first set up the keys using `lein heroku keys:add`

    lein heroku apps:create
    git push heroku master
    lein heroku logs

### Advanced Setup

Improve Jvm launch times with [drip](https://github.com/ninjudd/drip)

    brew install drip

Initialize drip by running [lein-drip](https://github.com/josteink/lein-drip) once as:

    lein drip

Read drip's settings for more info.

## Visuall Debugger

Thanks to Magnar's [Prone](https://github.com/magnars/prone), Exceptions and Ring errors can be visually inspected:

## Editor support

[Lighttable](http://www.lighttable.com) is the best editor for beginner Clojure/Clojusrescript developers. I recommend the following plugins:

- Bracketglow
- Emmet
- Gitlight
- Paredit and rainbow

Don't forget to enable **live coding** by evaluating Clojurescript code by _connecting_ "External Browser", and incliding the websocket javascript src show by Lighttable.

## Thanks

A big thanks to @weavejester for creating beautiful Clojure libraries like Hiccup, Ring and Compojure; @swannodette for pushing the limits of Clojurescript and Om.

Thanks to [Anna](https://github.com/annapawlicka/om-data-vis), whose modified Om example is used in this repo.

## Status & Roadmap

**Early development**.

The organizational structure is subject to change.

Future roadmap includes stabilizing the core, improving the lein template experience, and adding developer guides. An ideal reference app would be similar to the likes of [Flask-Heroku](https://github.com/zachwill/flask_heroku) and [Express](https://github.com/madhums/node-express-mongoose-demo).

The following tasks are on my priority list. Your feedback is much appreciated!

- Integrate [Om-Bootstrap](https://github.com/racehub/om-bootstrap), as an alternate workflow to HTML5/Less
- Integrate [Garden](https://github.com/noprompt/garden)
- Frontend asset pipeline with [Optimus](https://github.com/magnars/optimus)
- Core.async integration
- Midje Tests

## CONTRIBUTING

The lein template is generated from [here](https://github.com/priyatam/ringo-template). The template is a subset of the ringo reference app.

Contributing guidlines will be added soon.

## License

Copyright © 2014 Priyatam Mudivarti

Released under the Eclipse Public License, same as Clojure.
