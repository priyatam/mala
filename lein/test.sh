# deploy new template locally and test

lein install
lein new mala example :force
cd example && lein dev
