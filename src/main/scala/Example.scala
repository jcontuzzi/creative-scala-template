import doodle.core.Color
import doodle.core.Image._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(30)
  val test = circle(1)
}


object Chapter3Exercises {
  val exercise1 = circle(100).beside(circle(100)).beside(circle(100)).on(circle(300)).lineWidth(2)
  val exercise2 = circle(10).fillColor(Color.black).on(circle(20).fillColor(Color.green)).on(circle(30).fillColor(Color.white)).on(circle(50).fillColor(Color.orange))
  val exercise3 = triangle(40,60).fillColor(Color.yellow).beside(triangle(40,60).fillColor(Color.yellow.spin(doodle.core.Angle.degrees(15)))).below(triangle(40,60).fillColor(Color.yellow.spin(doodle.core.Angle.degrees(60))))
  val exercise4 = circle(10).fillColor(Color.red).on(circle(20).fillColor(Color.white)).on(circle(30).fillColor(Color.red)).above(rectangle(10,30).lineWidth(2).above(rectangle(30,10).fillColor(Color.brown).above(rectangle(150,40).fillColor(Color.green))))
}
