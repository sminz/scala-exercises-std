import org.scalatest.{FunSuite, Matchers}

import scala.concurrent.Future

/**
  * Created by ronnie on 2016. 9. 28..
  */
class ImplicitSpec extends FunSuite with Matchers {
  // implicit : 은연중의.. 암묵적 <-> explicit : 명시적
  
  test("explicit") {
    def foo(bar: String) : String = bar + " " + bar

    val str = "hello"
    println(foo(str))
  }
  
  test("implicit") {
    def foo(implicit bar: String) : String = bar + " " + bar
    
    val str = "hello"
    
    println(foo(str))
    //println(foo) <- complie error
    
    // implicit bar에 대한 정의를 찾는데.. (값이 아니라 타입)
    // 1. 로컬 스코프
    // 2. 컴패니언 오브젝트
    // 3. import 된 녀석들 중에서..
    
    implicit val str2 = "world"

    println(foo)
    
    implicit val str3 = "!!!"
    //println(foo) // 같은 스코프 내에 2개 이상의 동일한 타입에 대한 implicit가 있으면 컴파일 에러.
    
    // implicit 사용 위치
    // implicit val a                               <- 1.변수
    // def foo(implicit bar: String): String = ???  <- 2.함수의 인자
    // implicit def foo(a: String): Int             <- 3.함수
    // implicit class Foo(a: String) {              <- 4.클래스 
    // class Foo(implicit a: String)                <- 5.클래스의 인자
    // }
  }
  
  test("function implicit") {
    implicit def foo(bar: Int): String = bar.toString + " dollars"

    val a : String = 123
    val b : String = foo(123)
    println(a, b)
  }
  
  test("class implicit") {
    implicit class Foo(b: String) {
      def barbarbar: String = b + b + b
    }
    // 몽키 패칭 -> 위험(안티 패턴)
    val str : String = "hello~"
    println(str.barbarbar)
  }
  
  test("implicit part 2") {
    // 1. 암묵적인 type casting
    // Foo(str: String) ==> Bar(str: String)
    case class Foo(str: String)
    case class Bar(str: String)

    //val bar: Bar = Foo("foo") complie error
    implicit def toBar(foo: Foo): Bar = Bar(foo.str)
    val bar1: Bar = Foo("foo")
    println(bar1)

    // 암묵적으로 되는 것은 명시적으로도 된다.
    // f: Foo => Bar
    // f(foo) = bar
    // implicit f: Foo => Bar
    // bar = foo
    val bar2 = toBar(Foo("foo-bar"))
    println(bar2)


    // 2. 기존에 있는 class에 문법 추가하기
    // Foo에 name이라는 함수를 추가하기.
    val foo = Foo("foo~")
    // foo.name == "noname" compile error
    implicit class FooName(f: Foo) {
      def name = "noname"
    }
    foo.name == "noname"
    println(foo.name)

    // 클래스의 이름은 뭐가 되든 상관 없다
    // 생성자는 함수를 추가하고자 하는 해당 타입(Foo)이 들어가야 한다.


    // 3. 암묵적인 변수를 특정 클래스에 주입
    val future = Future {
      println(10)
      Thread.sleep(1000)
      1 + 1
    //} complie error - Cannot found implicit ExecutionContext
    }(scala.concurrent.ExecutionContext.Implicits.global)
  }
}
