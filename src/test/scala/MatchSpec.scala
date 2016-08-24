import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 24..
  */
class MatchSpec extends FunSuite with Matchers {
  
  test("basic") {

    def func1(num : Int) : String = num match {
      case 1 => "one"
      case _ => "other"
    }

    func1(1) shouldBe "one"
    func1(2) shouldBe "other" // 1외의 아무 숫자나 넣었을 때


    def func2(num : Int) : Any = num match {
      case 1 => "one"
      case n => n
    }

    func2(1) shouldBe "one"
    func2(2) shouldBe 2 // 1외의 아무 숫자나 넣었을 때
  }

  test("type") {

    def func(obj : Any) : String = obj match {
      case _ : String => "string"
      case _ : Int => "number"
    }

    func("str") shouldBe "string" // 문자열을 넣으면
    func(1) shouldBe "number" // 숫자를 넣으면
    func(List(1))
  }

  test("type2") {

    trait Link
    class Facebook extends Link {
      def getID() = "아이디"
    }
    class Kakao extends Link {
      def getName() = "본명"
    }
    class KakaoStrory extends Link {
      def getNickName() = "닉네임"
    }

    def getName(link : Link) : Option[String] = link match {
      case fb : Facebook => Some(fb.getID())
      case kk : Kakao => Some(kk.getName())
      case ks : KakaoStrory => Some(ks.getNickName())
      case _ => None
    }

    getName(new Facebook).get shouldBe "아이디"
  }
}
