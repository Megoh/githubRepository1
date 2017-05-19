package model.figures

import model.positioning.Move

object Rook extends Figure {

  val characterOfFigure = 'R'
  val moveInfinitely = true
  val moves = Set(Move(0, 1), Move(0, -1), Move(1, 0), Move(-1, 0))

}