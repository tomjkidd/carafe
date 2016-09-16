# carafe

A library to carry functions to aid survival

This is a quick home for things when I don't have a good place for them
otherwise.

## Overview

This is not stable and not meant to be relied on heavily yet.

## Setup

You should just need to use cljsbuild

```
lein cljsbuild once
```

## Test

[doo](https://github.com/bensu/doo) is used to provide tests through a
lein plugin. I installed only what was necessary to get things up and
running for *chrome* using *karma*. See [package.json](./package.json)
for those dependencies.

Once that is settled, run the following command to run the tests once

```
./scripts/run-cljs-tests
```

This is just a convenience to run 'lein doo chrome test-build once'

To have the tests watch while you dev...

```
./scripts/run-cljs-tests-watch
```

## License

Copyright © 2016 Tom Kidd

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
