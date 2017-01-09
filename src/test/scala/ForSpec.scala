import org.scalatest.{FunSuite, Matchers}

/**
  * Created by ronnie on 2016. 11. 23..
  */
class ForSpec extends FunSuite with Matchers {
  val numList = List(1, 2, 3, 4)

  test("for basic") {
    for (num <- numList) println(num)

    numList.foreach((num: Int) => println(num))
    numList.foreach(num => println(num))
    numList.foreach(println)
    // 축약형 (partially applied function) : 함수 리터럴이 인자를 하나만 받을 경우..
  }

  test("filter") {
    for (num <- numList if num % 2 == 0)
      println(num)

    numList.filter(_ % 2 == 0).foreach(println)
  }

  val numsList = List(List(0), List(1, 3, 5), List(2, 4))

  test("multiple generator") {
    for (nums <- numsList; num <- nums) println(num)

    val result: List[(Int, String)] = 
      for (x <- List(1, 2); y <- List("One", "Two")) yield (x, y)
    // yield : 전체 코드 블럭이 순회마다 만들어내는 결과 값을 모은다.
    println(result)
    // for 표현식은 제너레이터에서 시작한다. 
    // 둘 이상의 제너레이터는 뒤 쪽의 제너레이터가 앞의 것보다 빨리 변화한다.
  }

  test("binding") {
    for {
      num <- numList
      numStr = "S" + num.toString
    } println(numStr)
  }
  
  test("remind") {
    //for ( seq ) yield expression
    // seq : 제너레이터, 정의, 필터
    // 제너레이터 : 리스트의 원소(pat) <- 콜렉션(expr)
    // 정의 : pat = expr (pat을 expr의 값에 바인딩)
    // 필터 : if expr
  }

  test("one generator to map") {
    val list1: List[String] = for (num <- numList) yield num.toString
    val list2: List[String] = numList.map(num => num.toString)
    
    list1 shouldBe list2
  }
  test("generator-filter to withFilter-map") {
    val expr1: List[Int] = 
      for (num <- numList if num % 2 == 0 ) yield num * 10
    
    val expr2 =
      for (num <- numList withFilter(num => num % 2 == 0)) yield num * 10
    
    val expr3 : List[Int] = 
      numList.withFilter(_ % 2 == 0).map(x => x * 10)
    
    expr1 shouldBe expr2
  }
  
  test("two generator to flatMap") {
    val numsList = List(List(0), List(1, 3, 5), List(2, 4))
    
    val expr1: List[String] = for {
      nums <- numsList
      num <- nums
    } yield num.toString
    
    val expr2: List[String] = 
      numsList.flatMap(nums => nums.map(num => num.toString))
    expr1 shouldBe expr2
  }
  
  case class MyList[A](list :List[A]) {
    def map[B](f : (A) => B): MyList[B] = MyList(list.map(x => f(x)))
    
    def flatMap[B](f : (A) => MyList[B]) : MyList[B] = MyList(list.flatMap(x => f(x).list))
  }
  // Monad
  // Option[A]
  sealed trait MyOption[A] {
    def map[B](f : (A) => B) : MyOption[B]
    def flatMap[B](f : A => MyOption[B] ) : MyOption[B]

  }

  case class MySome[A](value: A) extends MyOption[A] {
    def map[B](f : (A) => B) : MySome[B] = MySome(f(value))
    def flatMap[B](f : A => MyOption[B] ) : MyOption[B] = f(value)
  }
//  case class MyNone() extends MyOption[A] {
//    def map(f : (A) => B) : MyNone = Option
//  }
  
  
  
  test("myList") {
    val my11 = MySome(1)
    val my22: MySome[Int] = my11.map(x => x+1)
    val my33: MyOption[Int] = my11.flatMap(x => MySome(x+1))

    val my1: MyList[Int] = MyList(List(1, 2))
    val my2: MyList[Int] = MyList(List(3, 4))
    //val my2: MyList[String] = my1.map(x => x.toString)
    val my3: MyList[(Int, Int)] = for (x <- my1; y <- my2 ) yield (x, y)
    my1.flatMap(x => my2.map(y => (x, y)))
  }
}
