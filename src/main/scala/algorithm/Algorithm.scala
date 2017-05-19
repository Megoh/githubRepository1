package algorithm

import model.figures._
import model.positioning.{Position, Move}
import model.{BoardItem, OccupiedBoard, Board}
import scala.annotation.tailrec


object Algorithm {

  def calculate(board: Board, figures: List[Figure]): Set[Board] = {

    val occupiedBoard = new OccupiedBoard(baseBoard = board)
    val results: Set[OccupiedBoard] = figures.foldLeft(Set(occupiedBoard)) {
      (boards, aFigure) => placeBoards(boards, aFigure)
    }
    results.map {
      result => {
        result.baseBoard
      }
    }
  }

  private def placeBoards(occupiedBoards: Set[OccupiedBoard], figure: Figure): Set[OccupiedBoard] = {
    occupiedBoards.flatMap {
      occupiedBoard =>
        placeBoard(occupiedBoard, figure)
    }
  }

  private def placeBoard(occupiedBoard: OccupiedBoard, figure: Figure): Set[OccupiedBoard] = {
    occupiedBoard.notOccupiedEmptyPositions
      .flatMap {
      position =>
        maybePlace(occupiedBoard, position, figure)
    }
  }

  private def maybePlace(occupiedBoard: OccupiedBoard, position: Position, figure: Figure): Option[OccupiedBoard] = {
    val boardWFigure = occupiedBoard.putFigure(position, figure)

    figure.moves.foldLeft(Some(boardWFigure): Option[OccupiedBoard]) {
      case (Some(board: OccupiedBoard), move: Move) =>
        validateFigurePlacement(position, board, move, figure.moveInfinitely)
      case (None, _) => None
    }
  }

  @annotation.tailrec
  def validateFigurePlacement(startingPosition: Position, board: OccupiedBoard, move: Move, indefinite: Boolean): Option[OccupiedBoard] = {
    val currentPosition = startingPosition + move

    val (partialResult, positionInsideBoard) = board.baseBoard.boardItems.get(currentPosition) match {
      case Some(bi: BoardItem) => {
        if (bi.figure.isEmpty) {
          val occupiedBoard = board.occupy(currentPosition)
          (Some(occupiedBoard), true)
        } else {
          (None, true)
        }
      }
      case None => {
        (Some(board), false)
      } 
    }

    if (partialResult.isDefined && positionInsideBoard && indefinite) {
      validateFigurePlacement(currentPosition, partialResult.get, move, indefinite)
    } else {
      partialResult
    }
  }
  def getFigures(figures : String) : List[Figure] = {      
        val listFiguresTypes: List[Figure] = figures.toUpperCase.toList.map {
          figure => figure match {
            case 'Q' => Queen
            case 'R' => Rook
            case 'B' => Bishop
            case 'N' => Knight
            case 'K' => King
            case _ => throw new Exception("WRONG FIGURE TYPE!")
          }
        }
        return listFiguresTypes
  }
}