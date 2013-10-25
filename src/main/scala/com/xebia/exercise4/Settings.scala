package com.xebia.exercise4

import com.typesafe.config.Config
import akka.actor._
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

class Settings(config: Config, extendedSystem: ExtendedActorSystem) extends Extension {

  object Http {
    val Port = config.getInt("scala-io-exercise.http.port")
    val Host = config.getString("scala-io-exercise.http.host")
  }

  //TODO create a L33t object that contains the uppercase, useShift and the conversion maps
    // TODO read the uppercase and useshift from your configuration

  val askTimeout = FiniteDuration(config.getMilliseconds("scala-io-exercise.ask-timeout"), TimeUnit.MILLISECONDS)

}

object Settings extends ExtensionId[Settings] with ExtensionIdProvider {
  override def lookup = Settings
  override def createExtension(system: ExtendedActorSystem) = new Settings(system.settings.config, system)

  def apply(context: ActorContext): Settings = apply(context.system)
}
