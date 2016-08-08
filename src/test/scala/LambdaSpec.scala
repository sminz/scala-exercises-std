import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 3..
  */
class LambdaSpec extends FunSuite with Matchers {

  test("lambda") {
    val f: (Int) => String = (x:Int) => x.toString
    
    val f2 = new Function1[Int, String] {
      override def apply(v1: Int): String = v1.toString
    }
    
    val f3 = new ((Int) => String) {
      override def apply(v1: Int): String = v1.toString
    }
    
    def f4(a: Int): String = a.toString
    
    f(1) shouldBe "1"
    f2(10) shouldBe "10"
    f3(100) shouldBe "100"
    f4(1000) shouldBe "1000"
  }
  
  test("int to string") {
    def myHighOrderFunc(f : Int => String)(v : Int) = f(v)
    
    myHighOrderFunc(x => x.toString)(10) shouldBe "10"
  }
  
  test("scala high order function api") {
    val list = List(1, 2, 3)
    list.map(_ + 10) shouldBe List(11, 12, 13)
    list.reduce(_ + _) shouldBe 6
    list.fold(1)(_ * _) shouldBe 6
    list.flatMap(x => List(List(x * 11), List(x * 11 + 1))).flatten shouldBe List(11, 12, 22, 23, 33, 34) 
    // flatMap = map + flatten
  }
}
