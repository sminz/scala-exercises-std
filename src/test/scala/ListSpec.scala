import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 9..
  */
class ListSpec extends FunSuite with Matchers {
  
  test("eq") {
    val a = List(1, 2, 3)
    val b = List(1, 2, 3)
    (a eq b) shouldBe false
  }
  
  test("==") {
    val a = List(1, 2, 3)
    val b = List(1, 2, 3)
    (a == b) shouldBe true
  }
  
  test("Nil") {
    val a: List[String] = Nil
    val b: List[Int] = Nil
    
    (a eq b) shouldBe true
    (a == b) shouldBe true
    
    (a eq Nil) shouldBe true
    (a == Nil) shouldBe true
    
    (b eq Nil) shouldBe true
    (b == Nil) shouldBe true
  }
  
  test("head and tail") {
    val a = List(1, 2, 3)
    a.headOption should equal (Option(1))
    a.tail should equal (List(2, 3))
  }
  
  test("access") {
    val a = List(1, 2, 3)
    a(0) shouldBe 1
    a(2) shouldBe 3
    intercept[IndexOutOfBoundsException] {
      a(5)
    }
  }
  
  test("immutable") {
    val a = List(1, 2, 3)
    val b = a.filter(_%2 == 0)
    
    a shouldBe List(1, 2, 3)
    b shouldBe List(2)
  }
  
  test("list api") {
    val a = List(1, 2, 3)
    a.length shouldBe 3
    a.reverse shouldBe List(3, 2, 1)
    a.map(_ * 2) shouldBe List(2, 4, 6)
    a.reduceLeft(_ * _) shouldBe 6
    a.foldLeft(1)(_ + _) shouldBe 7 
  }
  
  test("range") {
    val a = (1 to 5).toList
    a shouldBe List(1, 2, 3, 4, 5)
  }
  
  test("recursive tail") {
    val d = Nil
    val c = 3 :: d
    val b = 2 :: c
    val a = 1 :: b
    
    a shouldBe List(1, 2, 3)
    a.tail shouldBe List(2, 3)
    b.tail shouldBe List(3)
    c.tail shouldBe Nil
    
    val m = 1 :: 2 :: 3 :: Nil
    m shouldBe List(1, 2, 3)
  }
}
