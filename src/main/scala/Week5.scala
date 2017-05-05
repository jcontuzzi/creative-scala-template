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
        case numSides => polygonPath(radius, numSides - 1, startingAngle) :+ LineTo(polar(radius, startingAngle + 360.degrees / numSides))
      }

    def polygon(numSides: Int, radius: Double, startingAngle: Angle): Image = {
      def polygonPath(count: Int, angle: Angle): List[PathElement] =
        count match {
          case 0 => List(moveTo(polar(radius, startingAngle)))
          case count => polygonPath(count - 1, angle) :+ LineTo(polar(radius, startingAngle + angle * count))
        }

      closedPath(polygonPath(numSides, 360.degrees / numSides))
    }

    def drawShapes(n: Int, radius: Double, startingAngle: Angle): Image =
      n match {
        case 0 => Image.empty
        case n => polygon(n, radius + 20 * (n - 1), startingAngle * n) on drawShapes(n - 1, radius, startingAngle * (n - 1))
      }

  //Can I do ".toList" at the end instead?  It looks like I can -- is that because there's a map method on range as well?
  def ones2(n: Int): List[Int] = (0 until n).toList.map(x => 1)

  def descending2(n: Int): List[Int] = (n until 0 by -1).toList

  def ascending2(n: Int): List[Int] = (0 until n).toList.map(x => x + 1)

  def double2(list: List[Int]): List[Int] = list.map(x => 2 * x)

  def polygon2(sides: Int, radius: Double, startingAngle: Angle): Image = {
    val initialPoint = moveTo(polar(radius, startingAngle))
    val pointRotation = 360.0 / sides
    val linePath = (0.0 to 360.0 by pointRotation).toList.map { x => lineTo(polar(radius, startingAngle + x.degrees)) }
    closedPath(initialPoint :: linePath)
  }

    def ascending3(n: Int): List[Int] = (1 to n).toList

    def star(p: Int, n:Int, radius: Double): Image = {
      val pointRotation = 360.0 / p
      val linePath = (0.0 to 360.0 by pointRotation).toList.map{x => lineTo(polar(radius, (n * x).degrees))}
      closedPath(moveTo(polar(radius, 0.degrees)) :: linePath)
    }


  def allBeside(images: List[Image]): Image =
    images match {
      case Nil => Image.empty
      case hd :: tl => hd beside allBeside(tl)
    }

  def allAbove(images: List[Image]): Image =
    images match {
      case Nil => Image.empty
      case hd :: tl => hd above allAbove(tl)
    }

  //Was trying something on my own here; eventually needed some help from the answer key to get to "neatImage" below
  def listOfStars(p: Int, n:Int, radius: Double): List[Image] = (1 to n).map(n => star(p, n, radius)).toList

  def neatImage(p: Int, radius: Double): Image = {
    allAbove((3 to p by 2).toList.map{p => allBeside((1 to p/2).toList.map{x => star(p, x, radius)})})
  }



}