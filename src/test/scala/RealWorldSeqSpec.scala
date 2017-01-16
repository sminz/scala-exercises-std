import org.scalatest.{FunSuite, Matchers}

import scala.collection.immutable.Iterable

/**
  * Created by ronnie on 2017. 1. 16..
  */
class RealWorldSeqSpec extends FunSuite with Matchers {

  /**
    * account_id, page
    * 1, home
    * 1, hotdeal
    * 1, best
    * 2, home
    * 2, best
    * 3, home
    * 3, best
    * 3, giftbox
    * 3, hotdeal
    * 4, home
    * 4, best
    * ...
    *
    * home-hotdeal-best 1
    * home-best 2
    * home-best-giftbox-hotdeal 1
    */
  test("test") {
    val filePath = "/Users/ronnie/Workspace/study/scala-exercise-std/src/test/resources/visit.csv"
    val iter: Iterator[String] = io.Source.fromFile(filePath).getLines()
    //iter.foreach(println)

    val grouped: Map[String, List[String]] = iter.toList
      .map(line => {
        val spl: Array[String] = line.split(',')
        (spl(0), spl(1).trim)
      })
      .groupBy { case (key, value) => key }
      .map(x => (x._1, x._2.map(y => y._2)))

    val trans: Map[String, String] = grouped.mapValues(_.mkString("-"))
    trans.foreach(println)

    val values = trans.map { case (key, value) => value}
    values.foreach(println)

    val grouped2: Map[String, Iterable[(String, Int)]] = values.map(v => (v, 1))
      .groupBy{ case(value, count) => value}

    val sum: Map[String, Int] = grouped2.map(x => (x._1, x._2.map(y => y._2).sum))
    sum.foreach(println)
  }
}
