import doodle.core.{Color, Image}

/**
  * Created by am_dev on 4/18/17.
  */
object Week3Exercises {

  def boxes(color: Color): Image = {

    val box =
      Image.rectangle(40, 40).lineWidth(5.0).lineColor(Color.mistyRose.spin(30.degrees)).fillColor(Color.mistyRose)

    box beside box beside box beside box beside box
  }

}
