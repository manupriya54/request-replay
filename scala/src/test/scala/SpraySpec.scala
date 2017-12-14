package com.stripe.interview

import org.scalatest.{ WordSpec, Matchers }
import org.scalatest.prop.Checkers

class SpraySpec extends WordSpec with Matchers with Checkers {
  "spray" should {
    "parse to json" in {
      Main.parseJsonWithSpray("hello!").head shouldBe """hello!"""
    }
  }
}
