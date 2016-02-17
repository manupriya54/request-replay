package com.stripe.interview

import spray.json.DefaultJsonProtocol._

object MyLog {
  case class Request(val url:String, val headers: Map[String,String], body:String, method:String)
  case class Response(val body:String, val headers: Map[String,String], code:Int)
  case class Pair(request:Request,response:Response)
  case class ExtractableResponse(`object`:String,id:String)

  implicit val responseFormat = jsonFormat3(Response.apply)
  implicit val requestFormat = jsonFormat4(Request.apply)
  implicit val pairFormat = jsonFormat2(Pair.apply)
  implicit val extractableResponse = jsonFormat2(ExtractableResponse.apply)
}

