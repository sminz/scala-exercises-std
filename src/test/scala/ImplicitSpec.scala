import org.scalatest.{FunSuite, Matchers}

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
    
    // implicit val a                               <- 변수
    // def foo(implicit bar: String): String = ???  <- 함수의 인자
    // implicit def foo(a: String): Int             <- 함수
    // implicit class Foo(a: String) {              <- 클래스 
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
}
