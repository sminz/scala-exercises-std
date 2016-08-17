import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 17..
  */
class SetSpec extends FunSuite with Matchers {
  
  test("set") {
    val list = List(1, 2, 2, 3)
    val set = Set(1, 2, 2, 3)

    list shouldBe List(1, 2, 2, 3)
    set shouldBe Set(1, 2, 3)
  }
  
  test("set operation") {
    val set1 = Set(1, 2)
    val set2 = set1 + 3
    println(set2)
    
    val set3 = set1 ++ Set(3, 4, 5)
    println(set3)

    val set4 = set1.intersect(set3)
    println(set4)

    val set5 = set1.union(Set(1, 10))
    println(set5)
    
    val set6 = set1.diff(set3)
    println(set6)
  }
}
