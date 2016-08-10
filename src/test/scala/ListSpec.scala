import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 9..
  */
class ListSpec extends FunSuite with Matchers {
  
  test("cons") {
    val list1 = List(1, 2, 3, 4)
    val list2 = 1 :: 2 :: 3 :: 4 :: Nil
    val list3 = Nil.::(4).::(3).::(2).::(1)
    val list4 = List().::(4).::(3).::(2).::(1)
    val list5 = list1.::(5)
    list1 shouldBe list2
    list1 shouldBe list3
    list1 shouldBe list4
    list5 shouldBe List(5, 1, 2, 3, 4)

    list5 :: list1 shouldBe List(List(5, 1, 2, 3, 4), 1, 2, 3, 4)
    list5 ::: list1 shouldBe List(5, 1, 2, 3, 4, 1, 2, 3, 4)
  }
  
  test("pattern matching") {
    def listMatchers(list : List[Any]) = {
      list match {
        case a :: b :: c :: d :: Nil => println(a, b, c, d)
        case a :: b :: c :: Nil => println(s"[3] $a, $b, $c")
        case a :: b :: Nil => println(s"[2] $a, $b")
        case a :: Nil => println(s"[1] $a")
        case Nil => println(s"Nil")
      }
    }
    
    val list1 = List(1, 2, 3, 4)
    val list2 = List(1, 2, 3)
    val list3 = List(1, 2)
    val list4 = List(1)
    
    listMatchers(list1)
    listMatchers(list2)
    listMatchers(list3)
    listMatchers(list4)
    listMatchers(Nil)
  }
  
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
