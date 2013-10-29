package com.xebia
package exercise4

import akka.actor.{Props, ActorSystem}
import akka.io.IO

import spray.can.Http
import spray.can.Http.Bind

object Main extends App {
  implicit val system = ActorSystem("exercise-1")

  val receptionist = system.actorOf(Props[Receptionist], "receptionist")

  //TODO use the Settings to configure the host and port
  IO(Http) ! Bind(listener= receptionist, interface = "0.0.0.0", port=8000)
}

