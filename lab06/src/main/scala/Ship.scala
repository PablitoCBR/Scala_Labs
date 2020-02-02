import akka.actor.{Actor, ActorLogging, Props, ActorSystem, PoisonPill}

case class Echo(position: Point, range: Int)

class Ship(var position: Point, val range: Int, val seaDimension: Int) 
    extends Actor with ActorLogging {
    def receive: Receive = {
        case Tick => {
            move()
            context.actorSelection("/user/ship*") ! Echo(position, range)
        }
        case Echo(enemyPosition, enemyRange) => {
            if(this.context.self != sender() && isInRange(enemyPosition,enemyRange)){
                if(new util.Random().nextInt(100) < 90)
                    this.context.self ! PoisonPill
                else sender() ! PoisonPill
            }
        }
    }

    def move() : Unit = {
        val rnd = new util.Random()
        position = Point(
            (position.x + seaDimension + (rnd.nextInt(3)-1)) % seaDimension, 
            (position.y + seaDimension + (rnd.nextInt(3)-1)) % seaDimension)
    }

    def isInRange(enemyPosition: Point, range: Int) : Boolean = {
        ((position.x - enemyPosition.x).abs <= range 
        && (position.y - enemyPosition.y).abs <= range)
    } 
}