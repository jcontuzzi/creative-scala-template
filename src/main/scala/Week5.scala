import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
import doodle.core.Point._
import doodle.core.PathElement._

/**
  * Created by am_dev on 5/3/17.
  */
object Week5 {

    def style(image: Image): Image = image.lineWidth(5.0).lineColor(Color.orangeRed).fillColor(Color.darkOrange)

    val radius: Double = 100
    val spacer = Image.rectangle(radius / 5, radius).noLine

    val triangle = List(moveTo(polar(radius, 0.degrees)), lineTo(polar(radius, 120.degrees)), lineTo(polar(radius, 240.degrees)))
    val square = List(moveTo(polar(radius, 45.degrees)), lineTo(polar(radius, 135.degrees)), lineTo(polar(radius, 225.degrees)), lineTo(polar(radius, 315.degrees)))
    val pentagon = List(moveTo(polar(radius, 0.degrees)), lineTo(polar(radius, 72.degrees)), lineTo(polar(radius, 144.degrees)), lineTo(polar(radius, 216.degrees)), lineTo(polar(radius, 288.degrees)))

    val straightShapes = style(closedPath(triangle)) beside spacer beside style(closedPath(square)) beside spacer beside style(closedPath(pentagon))


    def curve(radius: Double, angle: Angle, numSides: Int): PathElement = curveTo(polar(radius * 1.5, angle - ((360.degrees/numSides) * (2/3))), polar(radius * 1.25, angle - ((360.degrees/numSides) * (1/3))), polar(radius, angle))

    val curvyTriangle = List(moveTo(polar(radius, 0.degrees)), curve(radius, 120.degrees, 3), curve(radius, 240.degrees, 3), curve(radius, 360.degrees, 3))
    val curvySquare = List(moveTo(polar(radius, 45.degrees)), curve(radius, 135.degrees, 4), curve(radius, 225.degrees, 4), curve(radius, 315.degrees, 4), curve(radius, 45.degrees, 4))
    val curvyPentagon = List(moveTo(polar(radius, 0.degrees)), curve(radius, 72.degrees, 5), curve(radius, 144.degrees, 5), curve(radius, 216.degrees, 5), curve(radius, 288.degrees, 5), curve(radius, 360.degrees, 5))

    val curvyShapes = style(closedPath(curvyTriangle)) beside spacer beside style(closedPath(curvySquare)) beside spacer beside style(closedPath(curvyPentagon))



    def ones(n: Int): List[Int] =
      n match {
        case 0 => Nil
        case n => 1 :: ones(n - 1)
      }

    // Hi Noel, I noticed that I get a stack overflow when trying to pass in a negative number here; not terribly surprising, but I wondered if there was a way to control the allowable inputs to just be positive integers?
    def descending(n: Int): List[Int] =
      n match {
        case 0 => Nil
        case n => n :: descending(n - 1)
      }


    def ascending(n: Int): List[Int] =
      n match {
        case 0 => Nil
        case n => ascending(n - 1) :+ n
      }


    def fill[A](n: Int, a: A): List[A] =
      n match {
        case 0 => Nil
        case n => a :: fill(n - 1, a)
      }


    def double(list: List[Int]): List[Int] = list.map(x => 2 * x)


    def product(list: List[Int]): Int =
      list match {
        case Nil => 1
        case hd :: tl => hd * product(tl)
      }


    def contains[A](list: List[A], a: A): Boolean = list.contains(a)


    def first[A](list: List[A], a: A): A =
      list match {
        case Nil => a
        case hd :: tl => hd
      }


    //I take it this isn't quite what you had in mind for the stretch exercise?  :-)
    def reverse[A](list: List[A]): List [A] = list.reverse


    def polygonPath(radius: Double, numSides: Int, startingAngle: Angle): List[PathElement] =
      numSides match {
        case 0 => List(moveTo(polar(radius, startingAngle)))
        case numSides => polygonPath(radius, numSides - 1, startingAngle) :+ lineTo(polar(radius, startingAngle + 360.degrees))
      }


    val polygon: Image = style(closedPath(polygonPath(100, 3, 45.degrees)))

}
