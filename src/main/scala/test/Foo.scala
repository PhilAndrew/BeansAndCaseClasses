package test

class Foo {
  private var a: Int = 0
  private var b: String = null

  def getA: Int = a

  def setA(a1: Int): Unit = {
    this.a = a1
  }

  def getB: String = b

  def setB(b1: String): Unit = {
    this.b = b1
  }

  override def toString: String = "Foo(" + a + ", \"" + b + "\")"
}