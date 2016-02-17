package com.stripe.interview

import spray.json._
// import scalaj.http._

object Main {

  def main(args: Array[String]): Unit = {
    val myJson = useSprayForSomething("hello world")
    print(myJson.prettyPrint)
  }

  def useSprayForSomething(input: String): JsValue = {
    val source = s"""{ "your_string": "$input!" }"""
    return source.parseJson.asJsObject
  }
}
