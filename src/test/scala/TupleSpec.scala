import java.util.Date

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 8. 3..
  */
class TupleSpec extends FunSuite with Matchers {

  test("tuple") {
    val tu: (Int, Long, String, Some[Int]) = (1, 2L, "3", Some(4))
    tu._1 shouldBe 1
    
    val (one, two, three, four) = tu
    one shouldBe tu._1
  }
}