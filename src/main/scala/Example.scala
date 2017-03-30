import doodle.core.Color
import doodle.core.Image._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(30)
}

object Chapter3Exercises {
  val exercise1 = circle(100).beside(circle(100)).beside(circle(100)).on(circle(300))
  val exercise2 = circle(10).fillColor(Color.black).on(circle(20).fillColor(Color.green)).on(circle(30)).on(circle(40).fillColor(Color.red))

}
