import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 11. 14..
  */
class TraitSpec extends FunSuite with Matchers {

  // 1. 자바의 인터페이스와 유사하다!
  test("similar to java interface") {
    trait Foo {
      def bar: String

      def toInt(int: Int): String
    }

    class FooImpl extends Foo {
      override def bar: String = "barbar"

      override def toInt(int: Int): String = int.toString
    }

    val foo = new FooImpl
    println(foo.bar)
    println(foo.toInt(10))

    val foo2 = new Foo {
      override def toInt(int: Int): String = int.toString

      override def bar: String = "barbarbar"
    }
    println(foo2.bar)
    println(foo2.toInt(100))
  }

  // 2. 함수의 구현체를 가질 수 있다.
  test("has function implementation") {
    trait Bar {
      def bar: String = "barbar"

      def toInt(int: Int): String
    }

    class BarImpl extends Bar {
      def toInt(int: Int): String = int.toString
    }
  }

  // 3. 변수를 가질 수 있다.
  test("has variables") {
    trait Bar2 {
      val a = 10

      def bar: String = "barbar"

      def toInt(int: Int): String
    }
  }

  // 4. 생성자가 없다.(오직 기본 생성자만)

  // Quiz.
  // 두개의 trait 생성
  // A, B 클래스 
  // A 안에는 a라는 함수가 구현되어 있음
  // B 안에는 a라는 함수가 구현되어 있음
  // AB... extends A with B => a
  // BA... extends B with A => a
  test("quiz") {
    trait A {
      println("hello A")

      def a: String = "a of A"
    }
    trait B {
      println("hello B")

      def a: String = "a ob B"
    }

    class AB extends A with B {
      override def a: String = super.a
    }
    class BA extends B with A {
      override def a: String = super.a
    }
    val ab = new AB
    val ba = new BA
    println(ab.a) // AA
    println(ba.a) // aa
    // scala linearization 참고 할 것

    class C {
      println("hello C")

      def a: String = "a of C"
    }

    class CAB extends C with A with B {
      override def a: String = super.a
    }

    val cab = new CAB
    println(cab.a)
  }
}
