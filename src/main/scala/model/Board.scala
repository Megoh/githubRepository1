package model

import model.figures.Figure
import model.positioning.Position

case class BoardItem(figure: Option[Figure] = None) {
  override def toString = figure.map(_.toString).getOrElse("-")
}

case class Board(boardItems: Map[Position, BoardItem]) {

  override def toString = {
    val width = boardItems.keys.maxBy(position => position.x).x
    val height = boardItems.keys.maxBy(position => position.y).y

    (0 to height).foldLeft("") {
      (str, y) =>
        (0 to width).foldLeft(str) {
          (str, x) =>
            val boardItem = boardItems(Position(x, y)).toString
            s"$str $boardItem"
        } + "\n"
    }
  }
}

object Board {
  def createEmptyBoard(width: Int, height: Int): Board = {
    val boardsMap : Map[Position, BoardItem] = (0 until width).foldLeft(Map[Position, BoardItem]()) {
      (boardMap, curX) =>
        (0 until height).foldLeft(boardMap) {
          (boardMap, curY) =>
            boardMap.+(Position(curX, curY) -> BoardItem())
        }
    }
    Board(boardsMap)
  }
}

case class OccupiedBoard(baseBoard: Board, occupiedPositions: Set[Position] = Set.empty) {
  def isOccupied(position: Position) = occupiedPositions.contains(position)

  def notOccupiedEmptyPositions: Set[Position] = {
    baseBoard.boardItems
      .filter {
      case (position: Position, boardItem: BoardItem) =>
        !occupiedPositions.contains(position) && boardItem.figure.isEmpty
    }.keySet
  }

  def putFigure(p: Position, f: Figure): OccupiedBoard = {
    this.copy(baseBoard = Board(boardItems = baseBoard.boardItems + (p -> BoardItem(figure = Some(f)))))
  }

  def occupy(p: Position): OccupiedBoard = {
    this.copy(occupiedPositions = occupiedPositions + p)
  }
}