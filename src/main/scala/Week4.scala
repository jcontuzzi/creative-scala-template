import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._
import cats.Monoid
import cats.implicits._
import cats.syntax.semigroup._

/**
  * Created by am_dev on 4/27/17.
  */
object Week4 {

  def rose(angle: Angle) = Point.cartesian((angle * 7).cos * angle.cos, (angle * 7).cos * angle.sin)

  val roseFn = rose _

  //roseFn as a function literal
  (a: Angle) => Point.cartesian((a * 7).cos * a.cos, (a * 7).cos * a.sin)


  def concentricShapes(count: Int, singleShape: Int => Image): Image =
    count match {
      case 0 => Image.empty
      case n => singleShape(n) on concentricShapes(n - 1, singleShape)
    }


  def coloredCircle(n: Int) = {
    val shape = Image.circle(50 + 10 * n)
    val color = Color.purple.spin((30 * n).degrees)

    shape.lineColor(color).lineWidth(10)
  }

  def shadedTriangle(n: Int) = {
    val shape = Week3Exercises.equilateralTriangle(100 + 20 * n)
    val color = Color.darkBlue.alpha((0.1 * n).normalized)

    shape.lineColor(color).lineWidth(10)
  }

  def coloredSquare(n: Int) = {
    val shape = Image.rectangle(100 + 20 * n, 100 + 20 * n)
    val color = Color.purple.spin((30 * n).degrees)

    shape.lineColor(color).lineWidth(10)
  }

  //Hi Noel -- I was a bit confused as to why you didn't have to convert things like coloredCircle or shadedTriangle to functions before passing them into concentricShapes.  Will bring this up in class.

  val finalShape = concentricShapes(10, coloredCircle) beside concentricShapes(10, shadedTriangle) beside concentricShapes(10, coloredSquare)


  //Waving the white flag on this -- seem to be struggling with it.

  val baseSize = 50

  def stem(size: Int, color: Color) = Image.rectangle(size, 2 * size).fillColor(color)

  def center(size: Int, color: Color) = Image.circle(size).lineColor(color).lineWidth(baseSize / 10)

  def petalDot(angle: Angle): Point = Point.cartesian((angle * 7).cos * angle.cos, (angle * 7).cos * angle.sin)

  //Leveraging code from section 8.5
  def petals(start: Angle, numDots: Int, location: Angle => Point): Image = {
    val step = Angle.one / numDots
    val dot = triangle(100, 100).lineColor(Color.blue)

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n => dot.at(location(angle).toVec) on loop(n - 1)
      }
    }

    loop(numDots)
  }

  val flower = (center(baseSize, Color.yellow) on petals(0.degrees, 100, petalDot)) above stem(baseSize, Color.green)

}
