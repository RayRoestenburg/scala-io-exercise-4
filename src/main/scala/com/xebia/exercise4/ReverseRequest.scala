package com.xebia.exercise4

import spray.json.DefaultJsonProtocol

case class ReverseRequest(value:String)

object ReverseRequest extends DefaultJsonProtocol {
  implicit val format = jsonFormat1(ReverseRequest.apply)
}

case class ReverseResponse(value:String, isPalindrome:Boolean = false)

object ReverseResponse extends DefaultJsonProtocol {
  implicit val format = jsonFormat2(ReverseResponse.apply)
}

case class FormatRequest(value:String)

object FormatRequest extends DefaultJsonProtocol {
  implicit val format = jsonFormat1(FormatRequest.apply)
}

case class FormatResponse(value:String)

object FormatResponse extends DefaultJsonProtocol {
  implicit val format = jsonFormat1(FormatResponse.apply)
}