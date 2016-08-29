import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 29..
  */
class CaseClassSpec extends FunSuite with Matchers {
  
  test("case class") {
    val aOption = Option(1)
    val aValue = aOption match {
      case Some(value) => value
      case None => 0
    }
    println(aValue)
  }
  
  test("usage") {
    case class Foo(a: Int, b: Boolean)
    val foo = Foo(1, false) // new를 사용하지 않음.
    println(foo.a, foo.b)
    println(foo)
    
    // 값 비교
    val foo1 = Foo(1, false)
    val foo2 = Foo(2, false)
    assert(foo == foo1)
    
    // 상속
    // case to case class (X)
    // class (O)
    
    // immutable
    val foo3 = foo.copy(a = 10)
    println(foo, foo3)
  }
  
  test("method") {
    case class Foo(a: Int, b: Int) {
      def sum() = a + b
    }
    val foo = Foo(10, 20)
    println(foo.sum())
  }
  
  test("val, var") {
    case class Foo(val a:Int, var b:Int)
    val foo = Foo(10, 20)
    // foo.a = 100 (immutable)
    foo.b = 200 // (mutatle)
  }
}