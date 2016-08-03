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
}
