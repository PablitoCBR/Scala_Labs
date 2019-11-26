

object Main extends App {
  implicit class ExtendedInt(val x: Int) extends AnyVal {
      def + (q: Q) : Q = Q(x* q.mian + q.licz, q.mian)
  }
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
  // val zbiór = Set(Q(1,2), Q(1,3), Q(1,4), Q(1,5), Q(1,6))

  // println(zbiór contains Q(1,2))
  // println(Q(1,2).hashCode)
  // println(Q(1,2).hashCode)
  // println(-Q(1,-2))
}
