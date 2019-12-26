/*
    PRZYPOMNIENIE:
    =============

    Ćwiczenie 6
    funkcja "cleanup" powinna usuwać wielokrotne, następujące po sobie
    wystąpienia tego samego elementu na liście
    np. cleanup(List(1,1,2,1,3,3)) == List(1,2,1,3)

    Ćwiczenie 7
    funkcja "chop" powinna wycinać „podlistę” zaczynającą się od elementu
    o numerze "b" i kończącą na elemencie o numerze "e" - przyjmijmy, że
    pierwszy element listy ma numer 1. Przykład:
    chop(List('a,2,'b,3,'c,4),2,4) == List(2,'b,3)

    Ćwiczenie 8
    funkcja "remEls" powinna usuwać co "k"-ty element listy. Przykład:
    remEls(List(1,1,2,1,3,3),3) == List(1,1,1,3)
 */

object Lab03 extends App {

  // Ćwiczenie 10. Zdefiniuj funkcję flatenList, która „spłaszcza” ewentualne listy
  // zagnieżdżone w liście będącej jej argumentem – zobacz przykłady poniżej.
  // W definicji flatenList wykorzystaj rekurencję ogonową i wzorce.
   @annotation.tailrec
  def flatenList(x: List[Any], res: List[Any] = Nil): List[Any] =  x match {
    case Nil => res.reverse
    case (head:List[Any])::tail => flatenList(head:::tail, res)
    case head::tail => flatenList(tail, head::res)
  }
  println(flatenList(List(1,"kwarg",3,2, List(4,List(2,2),2)), List[Any]()))
  
  // println(flatenList(List(1, List('a', List(2, 'b'), 'c'))))
  // println(flatenList(List(List(List(1, true), 2), 3)))

  // Ćwiczenie 11. Zaimplementuj funkcję chop z Ćwiczenia 7. stosując
  // standardowe operacje drop i take (Scala API - IterableOps)
  def chop2[A](x: List[A], b: Int, e: Int): List[A] = x.drop(b).take(e-b)
  println(chop2(List(1,2,3,4,5),2,4))

  // Ćwiczenie 12. Zaimplementuj uogólnienie funkcji cleanup z Ćwiczenia 6.
  // stosując standardową metodę foldRight (Scala API - IterableOps)
  def cleanup2[A](x: List[A]): List[A] = x.foldRight(List[A]()) {(element, list)  => element match {
    case x if list.contains(element) => list
    case _ => list :+ element
  }}.reverse
  println(cleanup2(List(1,2,2,3,3,2,3,4)))

  // Ćwiczenie 13. Zaimplementuj funkcję remEls z Ćwiczenia 8. przy pomocy
  // standardowych metod: filter, map i zipWithIndex (Scala API - IterableOps)
  def remEls2[A](x: List[A], k: Int): List[A] = x.zipWithIndex.filter(x => (x._2 + 1) % k != 0).map(x => x._1)
  println(remEls2(List(1,2,3,4,5,6,7),3))
}
