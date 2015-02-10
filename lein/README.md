# Ringo Leiningen Template

A Leiningen template for [Ringo](https://github.com/priyatam/ringo).

## Usage

    lein new ringo kickstart

## Development

Templates are just maven artifacts. For local development, upload the artifacts to your local mvn repo by running:

    lein install

Test it out by creating a new template

    lein new ringo kickstart

Move to the directory and run `lein ringo`, check if things look good.

All good? Upload the template into Clojars

    lein deploy clojars

If it's your first time uploading to Clojars add this to your `.lein/profiles.clj`.

```clojure
:repositories [["clojars" {:url "http://oss.sonatype.org/content/repositories/releases"
	                       :sign-releases false
                           :update :always}]]
```
						   
If you need to deploy to clojars with gpg, read Leiningen Deploy [tutoroial](https://github.com/technomancy/leiningen/blob/stable/doc/DEPLOY.md)
or this [blog](http://bendb.com/how-to-deploy-to-clojars/).

## License

Copyright © 2015 Priyatam Mudivarti.

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
