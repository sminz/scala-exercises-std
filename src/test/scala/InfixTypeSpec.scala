import java.time.{Instant, LocalDate, ZoneId, ZonedDateTime}
import java.util.Date

import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 12. 14..
  */
class InfixTypeSpec extends FunSuite with Matchers {

  test("infix type") {
    case class Person(name: String)
    class Loves[A, B](val a: A, val b: B)

    def announceCouple(couple: Person Loves Person) = {
      couple.a.name + " is in love with " + couple.b.name
    }

    def announceCouple2(couple: Loves[Person, Person]) = {
      couple.a.name + " is in love with " + couple.b.name
    }


    val romeo = new Person("Romeo")
    val juliet = new Person("Juliet")

    println(announceCouple(new Loves(romeo, juliet)))
  }

  test("more elegant") {
    case class Person(name: String) {
      def loves(person: Person): Loves[Person, Person] = new Loves(this, person)
    }
    class Loves[A, B](val a: A, val b: B)

    def announceCouple(couple: Person Loves Person) = {
      //Notice our type: Person loves Person!
      couple.a.name + " is in love with " + couple.b.name
    }

    val romeo = new Person("Romeo")
    val juliet = new Person("Juliet")

    println(announceCouple(romeo loves juliet))
  }

  test("time") {
    val date: Date = new Date()
    val instant: Instant = Instant.ofEpochMilli(date.getTime)
    val dateStr = instant.toString // 2016-12-15T02:42:56.440Z
    println(dateStr)

    val local = Instant.parse(dateStr)
    println(local.toString)
  }
}
