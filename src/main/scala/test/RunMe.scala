package test

import me.limansky.beanpuree.{BeanConverter, BeanGeneric}
import org.apache.commons.beanutils.PropertyUtilsBean
import shapeless._

object RunMe extends App {

  val gen = BeanGeneric[Foo]
  println(gen)

  val foo = gen.from(5 :: "aaa" :: HNil)
  println(foo)

  val conv = BeanConverter[Foo, Bar]
  val barCaseClass = conv.beanToProduct(foo)
  println(barCaseClass)

  val fooBean = conv.productToBean(Bar(15, "bar"))
  println(fooBean)


/*


  // Reflection
  import scala.reflect.runtime.{universe => ru}
  import ru._
  def getProperties[T: TypeTag]: Iterable[String] = {
    val tpe = ru.typeTag[T].tpe
    tpe.declarations.collect {
      case m: MethodSymbol if m.isCaseAccessor => m.name.toString
    }
  }
  case class User(name: String, age: Int) {
    val other: Long = 0
  }
  assert(getProperties[User] == Iterable("name", "age"))
  val user = User("Hello", 12)





  // Shapeless example
  // https://stackoverflow.com/questions/40812987/how-to-make-reflection-for-getting-the-field-value-by-its-string-name-and-its-or
  case class Hello(str: String)
  case class Intity(hello: Hello, flag: Boolean, id: Int, name: String)
  val inty = Intity(Hello("t"), false, 123, "blue")
  val intyGen = LabelledGeneric[Intity].to(inty)
  import shapeless.record._
  val id = intyGen.get(Symbol("hello"))
  println(id)


  //val pub = new PropertyUtilsBean
  //val property = pub.getProperty(inty, "flag")
  //println(property)

  val l = inty.productIterator.toList
  println(l)
*/

  import argonaut._, Argonaut._

  case class Person(name: String, age: Int, things: List[String])
  val person = Person("Bam Bam", 2, List("club"))
  implicit def PersonCodecJson =
    casecodec3(Person.apply, Person.unapply)("name", "age", "things")
  val json: Json = person.asJson
  println(json)
}
