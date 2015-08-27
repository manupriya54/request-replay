Request Replay
==============

When making API changes, it's nice to know that you won't affect
current integrations. To attempt to validate this, we'll build a tool
to replay a series of previously-captured API requests.

Here are a few YAML files containing a series of requests and
responses from the Stripe API. Each entry is a YAML map with the
`request` entry describing the HTTP request to send, and the
`response` entry giving a sample response.

# Your task

Write a script which, given each of these files (start with
[requestlog-simple.yaml](requestlog-simple.yaml)!), replays each
request to api.stripe.com (see [our API
docs](http://stripe.com/docs/api)) and compares the real response to
the request's sample response. Since the responses we get from the API
have fields that vary (timestamps, IDs), only check against the HTTP
status code at first.

## Library recommendations

We recommend these libraries for your language of choice (this is not
an exhaustive list! Ask your interviewer if your language of choice is
not on here!):

* python: requests
* ruby: rest-client
* java/jvm: HttpURLConnection and yamlbeans
* Go: built-in http library and go-yaml
* Common Lisp: drakma and cl-yaml
