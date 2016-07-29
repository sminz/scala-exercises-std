/**
  * Created by ronnie on 2016. 7. 29..
  */
class Point(x: Int, y: Int) {
  override def toString: String = s"($x, $y)"
}

object Classes1 extends App {
  def Point(x: Int, y: Int): Unit = {
    def toString: String = s"($x, $y)"
  }
  
  println((new Point(1, 2)).toString)
  println(this.Point(2,3).toString)
  
}

class HelloWorld {
  override def toString: String = "Hello world"
}

object Classes2 extends App {
  val hw = new HelloWorld()
  println(hw.toString)
}

object Classes3 {
  def main(args: Array[String]): Unit = {
    val pt = new Point(10, 20)
    println(pt.toString)
  }
}