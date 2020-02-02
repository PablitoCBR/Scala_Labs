import akka.actor.{Actor, ActorLogging, Props, ActorSystem}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import com.typesafe.config.ConfigFactory

case object Tick
case class Point(x: Int, y: Int)

object Demo extends App {

  val system = ActorSystem.create("DispDemo", ConfigFactory.load())
  // wprowadźmy sobie skrótową nazwę na „planistę” systemowego:
  val scheduler = system.scheduler

  val r = new util.Random()

  val dim = 50
  val amountOfShips = 50
  val shipRange = 2


  val referee = system.actorOf(Props(new Referee()))
  var positions = List[Point]()
  val actors = for (i <- 1 to amountOfShips) {
    var position = Point(r.nextInt(dim), r.nextInt(dim))
    while(positions contains position)
      position = Point(r.nextInt(dim), r.nextInt(dim))
    positions = position :: positions
    referee ! Watch(system.actorOf(Props(new Ship(position, shipRange, dim)), s"ship$i"))
  }



  /*
    Operacjom które wymagają wykorzystania wątków, a taką
    jest cykliczne wysyłanie wiadomości, musimy dostarczyć
    domyślne źródło wątków – tzw. „kontekst wykonawczy”.
    Można to zrobić na kilka sposobów:
  */
  import ExecutionContext.Implicits.global
  /*
    powyższe jest równoważne zdefiniowaniu „niejawnego kontekstu”:

    implicit val ec: ExecutionContext = ExecutionContext.global

    Mając do dyspozycji system aktorów możemy też użyć jego „dyspozytora”
    jako kontektu wykonawczego:

    import system.dispatcher

    Obie powyższe możliwości są dobre w naszym przypadku, ale w sytuacji
    gdy operacja może zablokować wątek warto użyć kontekstu wykonawczego
    stworzonego specjalnie do tego celu – patrz dokumentacja Akki, hasło
    „Dedicated dispatcher for blocking operations”.
  */
  val cancellable = scheduler.scheduleWithFixedDelay(1.seconds, 350.milliseconds) {
    new Runnable {
      def run(): Unit = {
        system.actorSelection("/user/ship*") ! Tick
      }
    }
  }

}
