import org.scalatest.{Matchers, FlatSpec}
import model.positioning.{Move, Position}
import model.figures.{Bishop, Figure, King, Knight, Queen, Rook}
import model.Board
import algorithm.Algorithm
import model.{OccupiedBoard, BoardItem, Board}

class AlgorithmTest extends FlatSpec with Matchers  {

  "3x3 board containing 2xK and 1xR" should "return with 4 solutions " in {
    val board3x3 = Board.createEmptyBoard(3, 3)
    val solutions : Set[Board] = Algorithm.calculate(board3x3, List(King, King, Rook))
    solutions.size should be(4)
  }

  "4x4 board containing 2xR and 4xN" should "return with 8 solutions " in {
    val board4x4 = Board.createEmptyBoard(4, 4)
    val solutions : Set[Board] = Algorithm.calculate(board4x4, List(Rook, Rook, Knight, Knight, Knight, Knight))
    solutions.size should be(8)
  }

  "8x8 board containing 8xQ" should "return with 92 solutions " in {
    val board8x8 = Board.createEmptyBoard(8, 8)
    val solutions : Set[Board] = Algorithm.calculate(board8x8, List(Queen, Queen, Queen, Queen, Queen, Queen, Queen, Queen))
    solutions.size should be(92)
  }

  "7x7 board containing 2xQ, 2xK, 2xB, 1xN and " should "return with 3063828 solutions " in {
    val board7x7 = Board.createEmptyBoard(7, 7)
    val solutions = Algorithm.calculate(board7x7, List(Queen, Queen, King, King, Bishop, Bishop, Knight))
    solutions.size should be(3063828)
  }
  
  "0x0 board with occupied Rook(0,0)" should " have no possible location" in {
    val board = Board(Map(Position(0, 0) -> BoardItem(Some(Rook))))
    val occupiedBoard = new OccupiedBoard(baseBoard = board)
    val startingPosition = Position(0, 1)
    val move = Move(0, -1)
    val indefinite = false
    val actual = Algorithm.validateFigurePlacement(startingPosition, occupiedBoard, move, indefinite)
    actual should be(None)
  }

}