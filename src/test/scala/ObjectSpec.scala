import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 1..
  */
class ObjectSpec extends FunSuite with Matchers {

  object Foo {
    def x = "x"
    def y = "y"
  }
  
  class Boo {
    def x = "x"
    def y = "y"
  }
  
  test("object") {
    val x = Foo
    val y = x
    val z = Foo
    
    x eq y shouldBe true
    x eq z shouldBe true
    
    val booX = new Boo
    val booY = booX
    val booZ = new Boo

    booX eq booY shouldBe true
    booX eq booZ shouldBe false
  }


  test("companion object") {
    class Person(val name: String, private val age: Int)

    object Person {
      def getInstance(name:String, age: Int): Person = new Person(name, age)
      def showAge(p: Person): Int = p.age 
    }
    
    val ronnie = Person.getInstance("ronnie", 32)
    ronnie.name shouldBe "ronnie"
    Person.showAge(ronnie) shouldBe 32
  }
  
}
