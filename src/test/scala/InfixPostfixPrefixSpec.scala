import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 12. 16..
  */
class InfixPostfixPrefixSpec extends FunSuite with Matchers {

  test("single parameter") {
    val num: Int = 3

    num + 4 shouldBe num.+(4)

    val str: String = "ABC"
    str.charAt(0) shouldBe 'A'
    str charAt 0 shouldBe 'A'
  }

  test("two params") {
    val str: String = "IOI"
    str.indexOf('I') shouldBe 0
    str indexOf 'I'

    str.indexOf('I', 1) shouldBe 2
    str indexOf('I', 1)
  }

  test("postfix") {
    val g: Int = 31
    g.toHexString //"1f"
    g toHexString
  }

  test("priority") {
    val str: String = "101"
    ((str indexOf "1") toString) shouldBe "0"
  }

  test("list concat") {
    val int = 10
    val list = List(11, 12)

    // :: cons
    int :: list
    list.::(int)

    class Myclass2 {
      def ::(str: String) = ???
    }
  }

  test("nt") {
    // F[A] === functor map ==? F[B]
    // F[A] === natural transformation ~> ===> G[A]
    val someInt = Some(1)
    val someString = someInt.map(_.toString)
    val listInt = someInt.toList

    //new AB[Int, String] {}

    trait ~>[F, G] {
      def apply(f: F): G
    }

    new (Int ~> String) {
      override def apply(f: Int): String = f.toString
    }
  }

  test("unary") {
    // 전위 연산자로 쓰일 수 있는 기호 +,-,!,~
    val g: Int = 31
    (-g) shouldBe -31
    g.unary_- shouldBe -31

    class Myclass {
      def unary_+ : String = "on"

      def unary_- : String = "off"
    }

    val my = new Myclass()
    +my shouldBe "on"
    -my shouldBe "off"

  }
}
