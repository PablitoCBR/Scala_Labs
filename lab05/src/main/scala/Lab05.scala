object Lab05 extends App {
  // program głóewny – tutaj przetestuj swoją implementację MyList
  var l = new MyNonEmptyList[Int](1, MyEmptyList).add(2).add("hola")
  println(l.toString())

  var emptyList = MyList()
  println(emptyList toString)
  println(emptyList add 2 toString)
  println(MyList(1,2,3,4) toString)
}
