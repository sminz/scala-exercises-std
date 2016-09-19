import org.scalatest.{FunSuite, Matchers}

import scala.annotation.tailrec

/**
  * Created by ronnie on 2016. 9. 19..
  */
class RecursionSpec extends FunSuite with Matchers {
  
  test("factorial") {
    
    //@tailrec  <- compile error
    def factorial(n: Int): BigInt = {
      if (n == 0) {
        1
      } else {
        factorial(n - 1) * n
      }
    }

    factorial(4) shouldBe 24
    // factorial(20, 1) shouldBe 2432902008176640000L <- stack overflow exception
  }
  
  test("factorial tail-recursion") {
    
    @tailrec
    def factorial(n: Int, acc: BigInt): BigInt = {
      if (n == 0) {
        acc
      } else {
        factorial(n - 1, acc * n)
      }
    }
    
    factorial(4, 1) shouldBe 24
    factorial(20, 1) shouldBe 2432902008176640000L
  }
  
  test("factorial loop") {
    
    def factorial(n: Int): BigInt = {
      var acc: BigInt = 1
      var loop = n
      while (loop != 0) {
        acc = acc * loop
        loop = loop - 1
      }
      acc
    }
    
    factorial(4) shouldBe 24
    factorial(20) shouldBe 2432902008176640000L
  }
}
