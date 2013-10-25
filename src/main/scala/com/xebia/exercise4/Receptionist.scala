package com.xebia.exercise4

import spray.routing._

import spray.httpx.SprayJsonSupport._
import scala.concurrent.ExecutionContext
import akka.actor.{Props, ActorRef}
import akka.util.Timeout
import spray.http.StatusCodes
import scala.util.{Failure, Success}

trait Receptionist extends HttpServiceActor
                      with ReverseRoute
                      with CreationSupport {

  import ReverseActor._

  def receive = runRoute(reverseRoute)

}

trait ReverseRoute extends HttpService {

  def createChild(props:Props, name:String):ActorRef

  private val reverseActor = createChild(ReverseActor.props, ReverseActor.name)
  private val formatActor = createChild(FormatActor.props, FormatActor.name)

  def reverseRoute:Route = path("reverse") {
    post {
      entity(as[ReverseRequest]) { request =>
        // We will fix this import and the timeout definition in a next exercise
        import ExecutionContext.Implicits.global
        import scala.concurrent.duration._
        implicit val timeout = Timeout(20 seconds)
        import akka.pattern.ask

        import ReverseActor._

        val futureResponse = reverseActor.ask(Reverse(request.value)).mapTo[Result]

        onComplete(futureResponse) {
          case Success(PalindromeResult) => complete(ReverseResponse(request.value, true))
          case Success(ReverseResult(value)) => complete(ReverseResponse(value, false))
          case Success(NotInitialized) => complete(StatusCodes.ServiceUnavailable)
          case Failure(e) => complete(StatusCodes.InternalServerError)
        }
      }
    }
  } ~ path("format") {
    post {
      entity(as[FormatRequest]) { request =>
        // We will fix this import and the timeout definition in a next exercise
        import ExecutionContext.Implicits.global
        import scala.concurrent.duration._
        implicit val timeout = Timeout(20 seconds)
        import akka.pattern.ask

        import FormatActor._

        val futureResponse = formatActor.ask(Format(request.value)).mapTo[FormatResult].map(r=> FormatResponse(r.value))
        complete(futureResponse)
      }
    }
  }
}

