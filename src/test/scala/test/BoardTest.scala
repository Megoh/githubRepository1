import org.scalatest.{Matchers, FlatSpec}
import model.figures._
import model.positioning.{Move, Position}
import model.Board
import algorithm._
import model.{OccupiedBoard, BoardItem, Board}

class BoardTest extends ChessSpec {

  "An empty board size" should "be 0" in {
    val board = Board.createEmptyBoard(0, 0)
    assert(board.boardItems.size == 0)
  }

  "An empty board size" should "be 4 and print - - \n - - " in {
    val board = Board.createEmptyBoard(2, 2)
    assert(board.boardItems.keySet.size == 4)
    assert(board.toString == " - -\n - -\n")
  }

}