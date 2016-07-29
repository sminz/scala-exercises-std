import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 7. 29..
  */
class ClassSpec extends FunSuite with Matchers {
  
  test("class with val") {
    class ClassWithVal(val name: String)
    val myVal = new ClassWithVal("my value")
    myVal.name shouldBe "my value"
  }
  
  test("class with var") {
    class ClassWithVar(var name: String)
    val myVar = new ClassWithVar("my variable")
    myVar.name shouldBe "my variable"
    myVar.name = "my var"
    myVar.name shouldBe "my var"
  }
}
