import akka.actor.{Actor, ActorRef, Terminated}

case class Watch(shipRef: ActorRef)

class Referee() extends Actor {
    var ships = Map[Int, ActorRef]()

    def receive: Receive = {
        case Watch(shipRef) => {
            context.watch(shipRef)
            ships = ships.++(Map(shipRef.hashCode()-> shipRef))
            }
        case Terminated(shipRef) => {
            println(s"${shipRef.path.name} sinking down...")
            ships -= shipRef.hashCode()
            if(ships.size < 2){
                println(s"Only one ship last. Game Ends.")
                this.context.system.terminate()
            }
        }
    }
}