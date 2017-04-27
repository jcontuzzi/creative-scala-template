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



  def sizedHuedCircle(size: Int, spin: Double): Image =
    Image.circle(50 + 5 * size).lineColor(Color.purple.spin((10 * (1 + spin)).degrees)).lineWidth(3.0)

  def sizedLightenedTriangle(size: Int): Image =
    Week3Exercises.equilateralTriangle(size.toDouble).lineColor(Color.purple.alpha((0.0 + 5 * size).normalized))

  def test = concentricShapes(10, (sizedLightenedTriangle _)(10))

}
