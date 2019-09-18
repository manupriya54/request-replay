# C++

A couple tips specifically if you're solving this problem using C++:

## Libraries

We recommend you use [nlohmann's json library][nlohmann/json] and
[cpr][whoshuu/cpr] for implementation. We've added both libraries to
this project, and included a `Makefile` that will build them in, as well
as enabling debug symbols and C++11 (C++17 if supported).
You'll need to have [libcurl](https://curl.haxx.se/download.html)
installed - we'll link it automatically.

## json

The json library tries to be pretty magical. Don't be afraid to use
`.get<type>()` instead of relying on it to coerce.

Iterating over json objects is tricky. The best solution we found was
to assign the object to a `std::map<string, json>` and iterate over
that.

## cpr

If you want to set custom headers, know that `cpr::Header` is basically
just a `std::map<string, string>` - you don't have to use their initializer
list syntax. Note that the type has a custom comparison function, so it's
easiest to declare your variable as `cpr::Header`.

We also made a small change from the public docs: `cpr::Payload` has
an overloaded constructor which can take a `std::string`, instead of
only accepting a list of key/value pairs.

[nlohmann/json]: https://nlohmann.github.io/json/
[whoshuu/cpr]: https://whoshuu.github.io/cpr/
