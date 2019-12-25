/*
  Rozwiązując poniższe ćwiczenia NIE korzystaj z następujących
  standardowych operacji na listach:
    – length/size
    – sum
    – map
    – filter
    – ::: (oraz „odmian” typu ++)
    – flatten
    – flatMap
    – reverse (oraz „odmian” tzn. reverseMap, reverse_:::)

  Nie używaj też zmiennych (wprowadzanych za pomocą „var”).
 */

object Lab02 extends App {

  def succ(n: Int) = n + 1
  def pred(n: Int) = n - 1

  // Ćwiczenie 1
  // Nie używaj + ani - na Int. Użyj succ/pred zdefiniowanych powyżej.
  // @annotation.tailrec
  def add(x: Int, y: Int): Int = {
    println(s"x=$x, y=$y")
    if(y > 0)
      add(succ(x), pred(y))
    else{
      if(y != 0) add(pred(x), succ(y))
      else x
    }
  }

  println(s"1+2=${add(1,2)}")

  // Ćwiczenie 2
  
  def sum(x: List[Int]): Int = {
    @annotation.tailrec
    def sumAcc(l: List[Int], acc: Int) : Int = l match {
      case List() => acc
      case head::tail => sumAcc(tail, acc + head)
    }
    sumAcc(x, 0)
  }
  println(s"sum = ${sum(List(1,2,3,4))}")

  // Ćwiczenie 3
  def length[A](x: List[A]): Int = {
    @annotation.tailrec
    def lengthAcc[A](l: List[A], acc: Int) : Int = l match {
      case List() => acc
      case head::tail => lengthAcc(tail, succ(acc))
    }
    lengthAcc[A](x, 0)
  }
  println(s"length = ${length[Int](List(1,2,3,4))}")

  // Ćwiczenie 4
  def map[A, B](x: List[A], f: A => B): List[B] = {
    @annotation.tailrec
    def mapAcc[A,B](l:List[A], f: A => B, res: List[B]) : List[B] = l match {
      case List() => res
      case head::tail => mapAcc(tail, f, res :+ f(head))
    }
    mapAcc(x, f, List[B]())
  }

  // Ćwiczenie 5
  // @annotation.tailrec
  def filter[A](x: List[A], f: A => Boolean): List[A] = {
    @annotation.tailrec
    def filterAcc[A](list:List[A], f: A => Boolean, result:List[A]) : List[A] = list match {
      case List() => result
      case head::tail => head match {
        case x if f(x) => filterAcc(tail, f, result :+ head)
        case _ => filterAcc(tail, f, result)
      }        
    }
    filterAcc(x, f, List[A]())
  }

  println(filter(List(1,2,3,4), (x:Int) => x > 2))

  // Ćwiczenie 6
  // funkcja "cleanup" powinna usuwać wielokrotne, następujące po sobie
  // wystąpienia tego samego elementu na liście
  // np. cleanup(List(1,1,2,1,3,3)) == List(1,2,1,3)
  // @annotation.tailrec
  def cleanup(x: List[Int]): List[Int] = {
    @annotation.tailrec
    def cleanupAcc(list: List[Int], result:List[Int], known:List[Int]) : List[Int] = list match {
      case List() => result
      case head::tail => head match {
        case x if known.contains(head) => cleanupAcc(tail, result, known)
        case _ => cleanupAcc(tail, result :+ head, known :+ head)
      }
    }
    cleanupAcc(x, List(), List())
  }

  println(cleanup(List(1,2,2,2,3,4,2,4,2,5)))

  // Ćwiczenie 7
  // funkcja "chop" powinna wycinać „podlistę” zaczynającą się od elementu
  // o numerze "b" i kończącą na elemencie o numerze "e" - przyjmijmy, że
  // pierwszy element listy ma numer 1. Przykład
  // chop(List('a,2,'b,3,'c,4),2,4) == List(2,'b,3)
  // @annotation.tailrec
  def chop[A](x: List[A], b: Int, e: Int): List[A] = {
    @annotation.tailrec
    def chopAcc(list: List[A], b: Int, e: Int, result :List[A] = List(), iter : Int = 0) : List[A] = list match {
      case List() => result
      case x if iter > e => result
      case head::tail if iter >= b => chopAcc(tail, b, e, result :+ head, iter + 1)
      case head::tail => chopAcc(tail, b, e, result, iter + 1)
    }
    chopAcc(x, b, e)
  }

  println(chop(List(1,2,3,4,5,6), 2, 10))

  // Ćwiczenie 8
  // funkcja "remEls" powinna usuwać co "k"-ty element listy. Przykład:
  // remEls(List(1,1,2,1,3,3),3) == List(1,1,1,3)
  // @annotation.tailrec
  def remEls[A](x: List[A], k: Int): List[A] = {
    @annotation.tailrec
    def remElsAcc(list:List[A], interval : Int, result :List[A] = List(), counter : Int = 1) : List[A] = list match {
      case List() => result
      case head::tail if counter == interval => remElsAcc(tail, interval, result, 1)
      case head::tail => remElsAcc(tail, interval, result :+ head, counter + 1)
    }
    remElsAcc(x, k)
  }

  println(remEls(List(1,2,3,4,5,6,7), 2))

  // Ćwiczenie 9
  // funkcja "rot" powinna przesuwać cyklicznie elementy listy o wartość "k".
  // Przykład:
  // rot(List(1,2,3,4,5,6),3) == List(4,5,6,1,2,3)
  // @annotation.tailrec
  def rot[A](x: List[A], k: Int): List[A] = {
    @annotation.tailrec
    def rotAcc(list: List[A], shift: Int, result: List[A] = List(), temp: List[A] = List(), counter : Int = 0) : List[A] = list match {
      case List() => temp match {
        case List() => result
        case head::tail => rotAcc(list, shift, result :+ head, tail, counter)
      }
      case head::tail if counter < shift => rotAcc(tail, shift, result, temp :+ head, counter + 1)
      case head::tail if counter >= shift => rotAcc(tail, shift, result :+ head, temp, counter)
    }
    rotAcc(x,k)
  }

  println(rot(List(1,2,3,4,5,6),3))
}
