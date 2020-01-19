

object Main extends App {
  // println(Q(2,4))
  // println(Q(1,2))
  // println(Q(2,4) == Q(1,2))
  // println(Q(2,4)*Q(1,2))
  println(Q(1,2) + Q(1,4))
  println(Q(1,2) - Q(3,4))
  println(Q(1,2) / Q(1,4))
  println(-Q(1,2))
  println(-Q(-1,2))
  println(-Q(1,-2))
  println(-Q(-1,-2))

  println(2 + Q(1,2))
  println(Q(1,2) + 2)
  var x:Q = 1
  println(x)

  if((Q(1,4) compare (Q(1,3))) < 0) println("JEST GIT")
  // val zbiór = Set(Q(1,2), Q(1,3), Q(1,4), Q(1,5), Q(1,6))

  // println(zbiór contains Q(1,2))
  // println(Q(1,2).hashCode)
  // println(Q(1,2).hashCode)
  // println(-Q(1,-2))
}
