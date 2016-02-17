A couple tips specifically if you're solving this problem using Scala:

# Running

Some combination of this should execute the sample java project:
```bash
$ cd stripe-interview/request-replay/scala
$ sbt
 > run
 > test
```

Libraries
---------

We recommend you use [Spray][spray/spray-json] and [Scalaj HTTP][scalaj/scalaj-http] for implementation,
but you can use whichever library you want if there is something you are more familiar with. We've added both
libraries to this project, and included a `build.sbt` that includes them. You don't need to use our provided
`build.sbt` file; feel free to use whichever build system you want.

Spray
----

The json library tries to parse classes from the nested JSON. We've
provided a class that can be parsed in one step from the JSON --
`com.stripe.interview.MyLog`. Hopefully this will reduce the amount
of work that you need to do.

[spray/spray-json]: https://github.com/spray/spray-json
[scalaj/scalaj-http]: https://github.com/scalaj/scalaj-http
