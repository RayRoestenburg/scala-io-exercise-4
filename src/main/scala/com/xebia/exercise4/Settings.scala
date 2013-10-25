package com.xebia.exercise4

import com.typesafe.config.Config
import akka.actor._
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

//TODO create a Settings class that extends from Extension
//TODO add a constructor with a config and an ExtendedActorSystem
class Settings(config: Config, extendedSystem: ExtendedActorSystem) extends Extension {

  object Http {
    val Port = config.getInt("scala-io-exercise.http.port")
    val Host = config.getString("scala-io-exercise.http.host")
  }

  //TODO create a Format object that contains the uppercase, useShift and the conversion maps
  object Format {
    // TODO read the uppercase and useshift from your configuration
    val uppercase = config.getBoolean("scala-io-exercise.format.uppercase")
    val useShift = config.getBoolean("scala-io-exercise.format.use-shift")
  }

  val askTimeout = FiniteDuration(config.getMilliseconds("scala-io-exercise.ask-timeout"), TimeUnit.MILLISECONDS)

}


//TODO create a Settings object that extends ExtensionId[Settings] and ExtensionIdProvider
object Settings extends ExtensionId[Settings] with ExtensionIdProvider {
  //TODO override lookup and return the canonical extension id (the Settings
  override def lookup = Settings
  //TODO override createExtension and create a new Settings
  override def createExtension(system: ExtendedActorSystem) = new Settings(system.settings.config, system)
}
