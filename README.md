# Request Replay

When making API changes,
it's nice to know that you won't affect current integrations.
To attempt to validate this, we'll build a tool to replay a
series of previously-captured API requests.

Here are a few JSON files containing a series of requests and responses from
the Stripe API. Each entry is a JSON map with the `request` entry describing
the HTTP request to send, and the `response` entry giving a sample response.

## Your task

Write a program which, given each of these files
(start with [requestlog-charges.json](requestlog-charges.json)!),
replays each request to `api.stripe.com`
(see [our API docs](http://stripe.com/docs/api)),
and compares the real response to the request's sample response.
Since the responses we get from the API
have fields that vary (timestamps, IDs),
only check against the HTTP status code.

## What to focus on

Please write this program as you would anything that you plan on running for a
long time: Focus on maintainability and readability!

## Library recommendations

Parsing JSON and making HTTPS requests can be no fun!
We recommend these libraries for your language of choice.
(This is not an exhaustive list!
Ask your interviewer if your language of choice is not on here!
You may use different JSON or HTTP libraries, if you prefer.):

| Language      | HTTP                          | JSON              |
| ------------- | ----------------------------- | ----------------- |
| C++           | [cpr]                         | [nlohmann's json] |
| C#            | [HttpClient] or [WebRequest]  | [Json.NET]        |
| Clojure       | [clj-http.client]             | [data.json]      |
| Common Lisp   | [drakma]                      | [cl-json]         |
| Go            | [net/http]                    | [encoding/json]   |
| Java          | [OkHttp]                      | [Gson]            |
| JavaScript    | [request] or [fetch]          | [ES5 JSON]        |
| Python        | [requests]                    | [json]            |
| Ruby          | [excon]                       | [JSON]            |
| Scala         | [scalaj-http]                 | [spray-json]      |

[cpr]: https://github.com/whoshuu/cpr
[nlohmann's json]: https://github.com/nlohmann/json
[HttpClient]: https://docs.microsoft.com/en-us/aspnet/web-api/overview/advanced/calling-a-web-api-from-a-net-client
[WebRequest]: https://docs.microsoft.com/en-us/dotnet/api/system.net.webrequest
[Json.NET]: https://www.newtonsoft.com/json
[clj-http.client]: https://github.com/dakrone/clj-http
[data.json]: https://github.com/clojure/data.json/
[drakma]: http://weitz.de/drakma/
[cl-json]: https://common-lisp.net/project/cl-json/
[net/http]: https://golang.org/pkg/net/http/
[encoding/json]: https://golang.org/pkg/encoding/json/
[OkHttp]: http://square.github.io/okhttp/
[Gson]: https://github.com/google/gson
[request]: https://github.com/request/request
[fetch]: https://github.com/bitinn/node-fetch
[ES5 JSON]: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON
[requests]: https://python-requests.org/
[json]: https://docs.python.org/3/library/json.html
[excon]: https://github.com/excon/excon
[JSON]: http://ruby-doc.org/stdlib-2.6.1/libdoc/json/rdoc/JSON.html
[scalaj-http]: https://github.com/scalaj/scalaj-http
[spray-json]: https://github.com/spray/spray-json

## Known Issues

### Old OpenSSL versions

The built-in version of OpenSSL on MacOS is old and
our API [does not support it](https://support.stripe.com/questions/how-do-i-upgrade-my-openssl-to-support-tls-1-2).
If you encounter SSL or TCP errors (for example, "Connection reset by peer")
when connecting to the Stripe API, this might be the cause.
If you don't have a newer version of Ruby (or Python) and OpenSSL
(installed via [brew](https://brew.sh/)) immediately available,
the quickest way to avoid losing time is to switch to
a pre-configured loaner laptop,
which your interviewer can pick up from the Recruiting pod.
