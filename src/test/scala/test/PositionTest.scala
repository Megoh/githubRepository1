import org.scalatest.{Matchers, FlatSpec}
import model.positioning.{Move, Position}
import model._
import algorithm._

class PositionTest extends ChessSpec {

  "If a move position is (0,0)" should "yield no change of position" in {
    val position = Position(0, 0)
    val newPosition = position + (Move(0, 0))
    assert(position == newPosition)
  }

  "If a move position is (2,2) and the current position is (0, 0) new position " should "be (2,2)" in {
    val position = Position(0, 0)
    val newPosition = position + (Move(2, 2))
    assert(newPosition == Position(2, 2))
  }
}