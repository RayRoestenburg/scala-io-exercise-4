package com.xebia.exercise4


import spray.testkit.Specs2RouteTest
import com.xebia.exercise4.TestSupport.AkkaTestkitContext
import org.specs2.mutable.Specification
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

class FormatActorSpec extends Specification  with Specs2RouteTest {

  // This is configured for the test
  // with fallback to the application.conf
  override def testConfigSource: String =
  """
  scala-io-exercise {

    format {
      uppercase = true
      use-shift = false
    }
  }
  """

  "The FormatActor" should {
    "Replace specific characters with similar looking digits" in new AkkaTestkitContext(system) {
      import FormatActor._

      val formatActor = system.actorOf(props, name)
      formatActor ! Format("reverse")
      expectMsg(FormatResult("R3V3R53"))
    }
  }

  // An alternate configuration is used for the next testcase
  val alternateConfigString =
   """
  scala-io-exercise {

    format {
      uppercase = false
      use-shift = true
    }
  }
  """

  val alternateConfig = ConfigFactory.parseString(alternateConfigString).withFallback(ConfigFactory.load())

  "The FormatActor" should {
    "Replace specific characters with similar looking digits" in new AkkaTestkitContext(ActorSystem("test",alternateConfig)) {
      import FormatActor._

      val formatActor = system.actorOf(props, name)
      formatActor ! Format("reverse")
      expectMsg(FormatResult("r#v#r%#"))
    }
  }

}
