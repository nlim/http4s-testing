import scala.language.postfixOps

import argonaut._, Argonaut._
import scalaz.concurrent.Task
import scalaz.stream._
import scala.language.higherKinds

case class Person(id: Long, name: String)

object Person {

  implicit val personEncoder: EncodeJson[Person] = EncodeJson { (p: Person) =>
    ("id"   := p.id) ->:
    ("name" := p.name) ->:
    jEmptyObject
  }

  val all = List(
    Person(1, "Bob"),
    Person(2, "Bob"),
    Person(3, "Bob"),
    Person(4, "Bob")
  )

  val allStream: Process[Task, Person] = Process.emitAll(all)

  import scala.concurrent.duration._
  import scalaz.concurrent.Strategy
  val tick: Process[Task, Duration] = {
    scalaz.stream.time.awakeEvery(100 milliseconds)(
      Strategy.DefaultStrategy,
      Strategy.DefaultTimeoutScheduler
    )
  }



  val allStreamDelayed: Process[Task, Person] = {
    allStream.zip(tick).map(t => t._1).repeat.take(100)
  }
}


