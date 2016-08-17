import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 17..
  */
class MapSpec extends FunSuite with Matchers {
  
  test("map") {
    val map1 = Map[Int, String]((1, "one"), (2, "two")) // tuple
    val map2 = Map((1, "one"), (2, "two")) // 타입 추론
    val map3 = Map(1 -> "one", 2 -> "two") // arrow
    
    map1 shouldBe map2
    map2 shouldBe map3
    map1 shouldBe map3
  }
  
  test("map operation") {
    val map1 = Map(1 -> "one", 2 -> "two")
    val map2 = map1.+(3 -> "three")

    // map1 shouldBe map2 ==> not equals
    map2.get(1) shouldBe Some("one")
    map2.get(4) shouldBe None
    map2.getOrElse(4, "four") shouldBe "four"
  }
}
