import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

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

  val finalShape = concentricShapes(10, coloredCircle) beside concentricShapes(10, shadedTriangle) beside concentricShapes(10, coloredSquare)

}
