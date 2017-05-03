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

    val radius = 100
    val spacer = Image.rectangle(radius / 5, radius).noLine

    val triangle = List(moveTo(polar(radius, 0.degrees)), lineTo(polar(radius, 120.degrees)), lineTo(polar(radius, 240.degrees)))
    val square = List(moveTo(polar(radius, 45.degrees)), lineTo(polar(radius, 135.degrees)), lineTo(polar(radius, 225.degrees)), lineTo(polar(radius, 315.degrees)))
    val pentagon = List(moveTo(polar(radius, 0.degrees)), lineTo(polar(radius, 72.degrees)), lineTo(polar(radius, 144.degrees)), lineTo(polar(radius, 216.degrees)), lineTo(polar(radius, 288.degrees)))

    val closedPaths = style(closedPath(triangle) beside spacer beside closedPath(square) beside spacer beside closedPath(pentagon))

}
