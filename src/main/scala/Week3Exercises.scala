import doodle.core.{Color, Image}

/**
  * Created by am_dev on 4/18/17.
  */
object Week3Exercises {

  def boxes(color: Color): Image = {

    /**  Hi Neil -- do you know why I can't seem to do ".spin(30.degrees)" here (as in the example)?
      *  I ran into the same problem in an earlier chapter and had to use the syntax below to get it working...
      *  Also note that in the text book you have mistyRose hard-coded in place of a reference to the parameter "color"
      */

    val box = Image.rectangle(40, 40).lineWidth(5.0).lineColor(color.spin(doodle.core.Angle.degrees(30))).fillColor(color)

    box beside box beside box beside box beside box

  }


  def square(d: Double): Double = d * d


  def halve(d: Double): Double = d / 2.0


  val aBox = Image.rectangle(40, 20).fillColor(Color.orange)

  def boxes(count: Int): Image =
    count match {
      case 0 => Image.empty
      case n => aBox beside boxes(n - 1)
    }


  def stackedBoxes(count: Int): Image =
    count match {
      case 0 => Image.empty
      case n => aBox above stackedBoxes(n - 1)
    }


  def noMatch(i: Int): Int =
    i match {
      case 1 => 3
    }


  def cross(count: Int): Image = {
    val aCircle = Image.circle(30)
    count match {
      case 0 => aCircle
      case n => (cross(0) beside cross(n - 1) beside cross(0)) below cross(0) above cross(0)
    }
  }


  def aSquare(color: Color): Image = Image.rectangle(20, 20).fillColor(color)

  def chessboard(count: Int): Image =
    count match {
      case 0 => (aSquare(Color.red) beside aSquare(Color.black)) above (aSquare(Color.black) beside aSquare(Color.red))
      case n => (chessboard(n - 1) beside chessboard(n - 1)) above (chessboard(n - 1) beside chessboard(n - 1))
  }


  def equilateralTriangle(sideLength: Double): Image =
    Image.triangle(sideLength, math.sqrt(square(sideLength) - square(halve(sideLength)))).lineColor(Color.darkGoldenrod)

  def sierpinski(count: Int): Image = {
    count match {
      case 0 => equilateralTriangle(30)
      case n => (sierpinski(n - 1) beside sierpinski(n - 1)) below sierpinski(n -1)
    }

  }

  def test = 1

}
