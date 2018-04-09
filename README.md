Request Replay
==============

When making API changes, it's nice to know that you won't affect current
integrations. To attempt to validate this, we'll build a tool to replay a
series of previously-captured API requests.

Here are a few JSON files containing a series of requests and responses from
the Stripe API. Each entry is a JSON map with the `request` entry describing
the HTTP request to send, and the `response` entry giving a sample response.

# Your task

Write a program which, given each of these files (start with
[requestlog-charges.json](requestlog-charges.json)!), replays each request to
api.stripe.com (see [our API docs](http://stripe.com/docs/api)) and compares
the real response to the request's sample response. Since the responses we get
from the API have fields that vary (timestamps, IDs), only check against the
HTTP status code.

## What to focus on

Please write this program as you would anything that you plan on running for a
long time: Focus on maintainability and readability!

## Library recommendations

Parsing JSON and making HTTPS requests can be no fun! We recommend these
libraries for your language of choice (this is not an exhaustive list!  Ask
your interviewer if your language of choice is not on here!):

| Language | HTTP | JSON |
| ------------- | ------------- | ------------- |
| C# | [HttpClient](https://docs.microsoft.com/en-us/aspnet/web-api/overview/advanced/calling-a-web-api-from-a-net-client) | [Json.NET](https://www.newtonsoft.com/json) |
| Clojure | [clj-http.client](https://github.com/dakrone/clj-http) | [data.json](https://github.com/clojure/data.json/) |
| Common Lisp | [drakma](http://weitz.de/drakma/) | [cl-json](https://common-lisp.net/project/cl-json/) |
| Go | [net/http](https://golang.org/pkg/net/http/) | [encoding/json](https://golang.org/pkg/encoding/json/) |
| Java (JVM) | [OkHttp](http://square.github.io/okhttp/) | [Gson](https://github.com/google/gson) |
| Python | [requests](http://www.python-requests.org/en/latest/) | [json](https://docs.python.org/2/library/json.html) |
| Ruby | [faraday](https://github.com/lostisland/faraday) | [JSON](http://ruby-doc.org/stdlib-2.0.0/libdoc/json/rdoc/JSON.html) |
| Scala | [scalaj-http](https://github.com/scalaj/scalaj-http) | [spray-json](https://github.com/spray/spray-json) |
| JavaScript | [request](https://github.com/request/request) | [ES5 JSON](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON) |

## Known Issues

### Old OpenSSL versions

The built-in version of OpenSSL on MacOS is old and our API [does not support it](https://support.stripe.com/questions/how-do-i-upgrade-my-openssl-to-support-tls-1-2). If you encounter SSL or TCP errors (for example, "Connection reset by peer") when connecting to the Stripe API, this might be the cause. If you don't have a newer version of Ruby and OpenSSL (installed via `brew`) immediately available, the quickest way to avoid losing time is to switch to a pre-configured loaner laptop, which your interviewer can pick up from the Recruiting pod.
