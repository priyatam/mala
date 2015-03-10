Mala Workflow
=============

## Development

While it is possible to code live with the Clojurescript's in-built [brepl](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment), figwheel provides a deeper experience by providing an interactive environment
for [reloadable-code](https://github.com/bhauman/lein-figwheel#writing-reloadable-code) and css.

Mala bundles figwheel to simplify repl-driven development.

Start figwheel, watch garden, start a ring api server and auto reload cljs/cljs/css on the fly

    lein dev

If the build is successful, open/refresh `http://localhost:3449/`: you'll see a Cljs brepl on the prompt. Enjoy live coding!

Format sourcecode with this [library](https://github.com/weavejester/cljfmt)

    lein format

Both [kibit](https://github.com/jonase/kibit) and [eastwood](https://github.com/jonase/eastwood) are excellent
static code analyzers, and can be invoked with

    lein analyze

Visual Debugger: Magnar's [Prone](https://github.com/magnars/prone) lets you visualize exceptions and ring errors on the browser.

![](doc/img/browser-debug.png)

## Design

Instrumentation helpers for Om components are enabled by integrating [Om-i](https://github.com/PrecursorApp/om-i). Preview
them with

	"ctrl" "alt" "shift" "j"

## Production

Bundle entire app into an uberjar

    lein prod

Assuming you installed [foreman](https://github.com/ddollar/foreman), test the app before going live

    foreman start
    
Preview `http://localhost:5000/`
