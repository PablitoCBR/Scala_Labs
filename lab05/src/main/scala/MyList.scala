abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B>:A](a: B): MyList[B]
    protected def elements : String
    override def toString: String = s"[${elements}]"
}
/*
  Zadanie 1: Pusta lista powinna być jedna. Jak to zrobić?
  Wskazówka: dla dowolnego typu X w Scali mamy „Nothing <: X”
*/
object MyEmptyList extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B](a: B): MyList[B] = new MyNonEmptyList[B](a, this)
    override def elements : String = ""
}
class MyNonEmptyList[A](h: A, t: MyList[A] = MyEmptyList) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](a: B): MyList[B] = new MyNonEmptyList[B](a, this)
    override def elements : String ={
      def acum(list: MyList[A], acc : String = "") : String = list.isEmpty match {
        case true => acc dropRight 1
        case false => acum(list.tail, acc concat s"${list.head},")
      }
      acum(this)
    }
}
/*
  Zadanie 2: Spowoduj, żeby możliwe było definiowanie list tak, jak poniżej?

  MyList()
  MyList(1, 2, 3)
*/

object MyList{
    def apply() = MyEmptyList
    def apply[A](args:A*) = {
      var result : MyList[A] = MyEmptyList
      for (arg <- args reverse) result = result add arg
      result
    }
}


/*
  Zadanie 3: Zdefiniuj (przesłoń) metodę toString dla klasy MyList tak, żeby
  produkowała napisy postaci „[el1, ... , eln]”.
*/
