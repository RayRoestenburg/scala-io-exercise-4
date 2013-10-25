package com.xebia.exercise4

import akka.actor.{Props, Actor}

object FormatActor {
  case class Format(value:String)
  case class FormatResult(value:String)
  def props = Props[FormatActor]
  def name = "format"
}

class FormatActor extends Actor {
  import FormatActor._

  //TODO create a val for the settings
  val settings = Settings(context.system)

  //TODO get 'uppercase' boolean from the Settings Format object
  val upper = settings.Format.uppercase

  //TODO get 'useShift' boolean from the Settings Format object
  val useShiftDigit = settings.Format.useShift

  val conversionMap = Map('o'-> 0, 'i'->1, 'z'->2, 'e'-> 3, 'u' -> '4', 's' -> 5, 'b'-> 6, 'y' -> 7, 'B'-> 8, 'g'-> 9)

  val shiftConversionMap = Map('o'-> ')', 'i'->'!', 'z'->'@', 'e'-> '#', 'u' -> '$', 's' -> '%', 'b'-> '^', 'y' -> '&', 'B'-> '*', 'g'-> '(')

  def convertToDigit(value:String) = value.map(c=> conversionMap.get(c).map(number=> number.toString).getOrElse(c)).mkString("")

  def convertToShiftDigit(value:String) = value.map(c=> shiftConversionMap.getOrElse(c,c)).mkString("")

  def receive = {
    case Format(value) =>

      val result = if(useShiftDigit) convertToShiftDigit(value) else convertToDigit(value)

      val msg = if(upper) result.toUpperCase() else result

      sender ! FormatResult(msg)
  }
}
