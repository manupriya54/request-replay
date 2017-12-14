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

We recommend you use [Spray-json][spray/spray-json] or [Circe][io/circe] for Json parsiong 
and [Scalaj HTTP][scalaj/scalaj-http] for implementation,
but you can use whichever library you want if there is something you are more familiar with. We've added those
libraries to this project, and included a `build.sbt` that includes them. You don't need to use our provided
`build.sbt` file; feel free to use whichever build system you want.

Since learning a new json parsing library from scratch during an interview would be quite challenging,
We've provided some code that might be helpful under com.stripe.interview.MyLog. It has some case classes that might be useful
to represent the parsed Json. Along with it, there are two companion objects with boilerplate for each
parsing library. Hopefully this will reduce the amount of work that you need to do.
 

Spray-json
----------

Spray Json is a somewhat old library that handles errors as exceptions. If you have not used it before, this section of the documentation is particularly helpful
https://github.com/spray/spray-json#usage

Circe
-----
Circe is a newer library that is popular among functional programming enthusiasts. The quick start documentation is not
so quick, but the Parsing Json and Encoding and Decoding sections are pretty straightforward.
The supplied boilerplate uses Semi-Automatic derivation, which is popular at Stripe.

Scalaj-http
------------
There are better libraries than Scalaj-http in Scala, but it is very easy to learn and use for this exercise.

[spray/spray-json]: https://github.com/spray/spray-json
[io/circe]: https://circe.github.io/circe/
[scalaj/scalaj-http]: https://github.com/scalaj/scalaj-http
