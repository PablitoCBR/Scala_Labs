object Lab05 extends App {
  // program głóewny – tutaj przetestuj swoją implementację MyList
  var l = new MyNonEmptyList[Int](1, MyEmptyList).add(2).add("hola")
  println(l.toString())
}
