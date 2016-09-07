import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 9. 7..
  */
class CurrySpec extends FunSuite with Matchers {

  test("curry") {
    def foo(a: Int, b: Int, c: Int): Int = ???
    // a => b => c => d
    // (a) => (b => c => d)
    // (a) => ((b) => (c => d))
    // (a) => ((b) => ((c) => (d)))

    def plus(x: Int, y: Int): Int = x + y
    val plusCurried: (Int) => (Int) => Int = (plus _).curried
    val plusOne: (Int) => Int = plusCurried(1)
    println(plusOne(10))
    println(plusOne(20))
  }

  test("syntax1") {
    def adder(x: Int)(y: Int) = x + y
    val addWithOne: (Int) => Int = adder(1)
    println(addWithOne(10))
    println(addWithOne(20))
  }

  test("syntax2") {
    def adder(x: Int) = (y: Int) => x + y
    val addWithOne = adder(1)
    println(addWithOne(10))
    println(addWithOne(20))
  }

  test("x + y + z : syntax1") {
    def adder(x: Int)(y: Int)(z: Int): Int = x + y + z
    val addWithOne: (Int) => (Int) => Int = adder(1)
    val addWithOneAndTwo: (Int) => Int = addWithOne(2)
    println(addWithOneAndTwo(10))
    println(addWithOneAndTwo(20))
  }

  test("x + y + z : syntax2") {
    def adder(x: Int) = (y: Int) => (z: Int) => x + y + z // (y: Int) => { (z: Int) => { x + y + z } }
    val addWithOne: (Int) => (Int) => Int = adder(1)
    val addWithOneAndTwo: (Int) => Int = addWithOne(2)
    println(addWithOneAndTwo(10))
    println(addWithOneAndTwo(20))
  }
}
