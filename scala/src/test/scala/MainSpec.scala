package com.stripe.interview

import org.scalatest.{ WordSpec, Matchers }
import org.scalatest.prop.Checkers

class MainSpec extends WordSpec with Matchers with Checkers {
  "spray" should {
    "parse to json" in {
      Main.useSprayForSomething("hello").asJsObject.getFields("your_string")(0).toString should === (""""hello!"""")
    }
  }
}
