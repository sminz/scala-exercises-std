import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 7. 27..
  */
class AssertSpec extends FunSuite with Matchers {
  
  test("hello scala") {
    println("world")
  }
  
  test("assert") {
    val left = 2
    val right = 1
    assert(left != right)
  }
  
  test("boolean") {
    true shouldBe true
  }
  
  test("number") {
    val v1 = 4
    v1 shouldBe 4
  }
  
  test("value") {
    assert(2 == 1 + 1)
  }
}
