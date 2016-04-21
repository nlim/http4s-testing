import scala.language.postfixOps

import argonaut._, Argonaut._
import scalaz.concurrent.Task
import scalaz.stream._
import scala.language.higherKinds

object DemoService extends org.http4s.argonaut.ArgonautInstances {
  import org.http4s._, org.http4s.dsl._
  //import doobie.imports._

  implicit def entityEncoder[A: EncodeJson]:  EntityEncoder[A] = jsonEncoderOf[A]

  def asJsonArray[T[_], A: EncodeJson](p: Process[T, A]): Process[T, String] = {
    Process.emit("[") ++
    p.map(_.asJson.nospaces).intersperse(",") ++
    Process.emit("]")
  }

  def service = HttpService {
    case GET -> Root / "people" =>
      Ok(asJsonArray(Person.allStreamDelayed))
  }
}



object Main {
  def main(args: Array[String]): Unit = {
    import org.http4s.server.blaze._
    val server = BlazeBuilder.bindHttp(9090).mountService(DemoService.service).run
    server.awaitShutdown()
  }
}

