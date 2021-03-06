import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

object ArcheryAgain {
  val target = circle(10).fillColor(Color.red).on(circle(20).fillColor(Color.white)).on(circle(30).fillColor(Color.red))
  val stand = rectangle(10,30).lineWidth(2).above(rectangle(30,10).fillColor(Color.brown))
  val grass = rectangle(150,40).fillColor(Color.green)
  val finalImage = target.above(stand).above(grass)
 }

object StreetsAhead {
  val roof = triangle(30,15).fillColor(Color.fireBrick)
  val houseBody = rectangle(30,25).fillColor(Color.red)
  val door = rectangle(6,18).fillColor(Color.black).below(rectangle(6,7).fillColor(Color.red))
  val house = door.on(houseBody).below(roof)

  val treeTop = circle(15).fillColor(Color.green)
  val treeTrunk = rectangle(6,12).fillColor(Color.brown)
  val tree = treeTop.above(treeTrunk)

  val roadStripe = rectangle(20,3).fillColor(Color.yellow).beside(rectangle(5,3).fillColor(Color.black))
  val roadSurface = rectangle(25,9).fillColor(Color.black)
  val roadPiece = roadStripe.above(roadSurface)

  val houseTreeAndRoad = house.beside(tree).above(roadPiece.beside(roadPiece).beside(roadPiece))
  val finalImage = houseTreeAndRoad.beside(houseTreeAndRoad).beside(houseTreeAndRoad).lineWidth(0.0)
}