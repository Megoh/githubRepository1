import model.figures.{Rook, King, Knight, Figure}
import model.{OccupiedBoard, BoardItem, Board}
import model.positioning.Position

class OccupiedBoardTest extends ChessSpec {

  "2x2 empty occupiedBoard's all board items" should "return isOccupied false" in {
    val board = Board(Map(Position(0, 0) -> BoardItem(None), Position(0, 1) -> BoardItem(None), Position(0, 2) -> BoardItem(None),
      Position(1, 0) -> BoardItem(None), Position(1, 1) -> BoardItem(None), Position(1, 2) -> BoardItem(None)))
    val occupiedBoard = new OccupiedBoard(baseBoard = board)

    val position1 = Position(0, 0)
    val position2 = Position(0, 1)
    val position3 = Position(1, 0)
    val position4 = Position(1, 1)
    assert(occupiedBoard.isOccupied(position1) == false)
    assert(occupiedBoard.isOccupied(position2) == false)
    assert(occupiedBoard.isOccupied(position3) == false)
    assert(occupiedBoard.isOccupied(position4) == false)
  }

  "2x2 occupied occupiedBoard" should "return isOccupied true" in {
    val board = Board(Map(Position(0, 0) -> BoardItem(Some(King)), Position(0, 1) -> BoardItem(None), Position(0, 2) -> BoardItem(None),
      Position(1, 0) -> BoardItem(None), Position(1, 1) -> BoardItem(None), Position(1, 2) -> BoardItem(None)))
    val occupiedBoard = new OccupiedBoard(baseBoard = board)
    val position = Position(0, 0)
    occupiedBoard.occupy(Position(0,0)).isOccupied(position) should be(true)
  }

  "3x3 occupied Board includes Rook both at (0, 0) and (1, 1) " should "not find occupied and empty positions" in {
    val board = Board(Map(Position(0, 0) -> BoardItem(Some(Rook)), Position(0, 1) -> BoardItem(None), Position(0, 2) -> BoardItem(None),
      Position(1, 0) -> BoardItem(None), Position(1, 1) -> BoardItem(Some(Rook)), Position(1, 2) -> BoardItem(None),
      Position(2, 0) -> BoardItem(None), Position(2, 1) -> BoardItem(None), Position(2, 2) -> BoardItem(None)))
    val occupiedBoard = new OccupiedBoard(baseBoard = board)
    val occupiedWithPositions = occupiedBoard.occupy(Position(0, 0)).occupy(Position(1, 1)).notOccupiedEmptyPositions
    val expected = Set(Position(0, 0), Position(1, 1))
    occupiedWithPositions should not be(expected)
  }

  "3x3 occupied Board" should "find all not occupied and empty positions" in {
    val board = Board(Map(Position(0, 0) -> BoardItem(Some(Rook)), Position(0, 1) -> BoardItem(None), Position(0, 2) -> BoardItem(None),
      Position(1, 0) -> BoardItem(None), Position(1, 1) -> BoardItem(Some(Rook)), Position(1, 2) -> BoardItem(None),
      Position(2, 0) -> BoardItem(None), Position(2, 1) -> BoardItem(None), Position(2, 2) -> BoardItem(None)))
    val occupiedBoard = new OccupiedBoard(baseBoard = board)
    val occupiedWithPositions = occupiedBoard.occupy(Position(0, 0)).occupy(Position(1, 1)).notOccupiedEmptyPositions
    val expected = Set(Position(0, 1), Position(0, 2), Position(1, 0), Position(1, 2), Position(2, 0), Position(2, 1), Position(2, 2))
    occupiedWithPositions should be(expected)
  }
}