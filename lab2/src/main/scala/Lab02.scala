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
  def filter[A](x: List[A], f: A => Boolean): List[A] = ???

  // Ćwiczenie 6
  // funkcja "cleanup" powinna usuwać wielokrotne, następujące po sobie
  // wystąpienia tego samego elementu na liście
  // np. cleanup(List(1,1,2,1,3,3)) == List(1,2,1,3)
  // @annotation.tailrec
  def cleanup(x: List[Int]): List[Int] = ???

  // Ćwiczenie 7
  // funkcja "chop" powinna wycinać „podlistę” zaczynającą się od elementu
  // o numerze "b" i kończącą na elemencie o numerze "e" - przyjmijmy, że
  // pierwszy element listy ma numer 1. Przykład
  // chop(List('a,2,'b,3,'c,4),2,4) == List(2,'b,3)
  // @annotation.tailrec
  def chop[A](x: List[A], b: Int, e: Int): List[A] = ???

  // Ćwiczenie 8
  // funkcja "remEls" powinna usuwać co "k"-ty element listy. Przykład:
  // remEls(List(1,1,2,1,3,3),3) == List(1,1,1,3)
  // @annotation.tailrec
  def remEls[A](x: List[A], k: Int): List[A] = ???

  // Ćwiczenie 9
  // funkcja "rot" powinna przesuwać cyklicznie elementy listy o wartość "k".
  // Przykład:
  // rot(List(1,2,3,4,5,6),3) == List(4,5,6,1,2,3)
  // @annotation.tailrec
  def rot[A](x: List[A], k: Int): List[A] = ???

}
