import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 1..
  */
class OptionSpec extends FunSuite with Matchers {

  test("option") {
    val listValue: Option[List[Int]] = Some(List(1, 2, 3))
    listValue shouldBe Some(List(1, 2, 3))
    val noneValue: Option[List[Int]] = None
    
    listValue.getOrElse(List(0)) shouldBe List(1, 2, 3)
    noneValue.getOrElse(List(0)) shouldBe List(0)
  }
  
  test("pattern matching") {
    val strValue: Option[String] = Some("val")
    val value1: String = strValue match {
      case Some(v) => v
      case None => ""
    }
    value1 shouldBe "val"

    val noneValue: Option[String] = None
    val value2: String = noneValue match {
      case Some(v) => v
      case None => ""
    }
    value2 shouldBe ""
  }
  
  test("map") {
    val doubleValue: Option[Double] = Some(5.5)
    val noneValue: Option[Double] = None
    
    val value1 = doubleValue.map(_ * 10.0)
    val value2 = noneValue.map(_ * 10.0)
    
    value1 shouldBe Some(55.0)
    value2 shouldBe None
  }
  
  test("fold") {
    val intValue: Option[Int] = Some(20)
    val noneValue: Option[Int] = None
    
    val value1 = intValue.fold(0)(_ + 1)
    val value2 = noneValue.fold(0)(_ + 1)
    value1 shouldBe 21
    value2 shouldBe 0
  }
}
