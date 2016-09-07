import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 9. 7..
  */
class RangeSpec extends FunSuite with Matchers {

  test("range") {
    val nums = Range(1, 10)
    println(nums)
    
    val nums2 = 1 to 10
    println(nums2)
    
    val num3 = Range(1, 10, 2)
    println(num3)
    
    val num4 = 1 until 10
    println(num4)
    
    val ch = Range('a', 'z').map(_.toChar)
    println(ch)
  }
}
