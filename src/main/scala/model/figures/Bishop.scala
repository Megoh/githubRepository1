package model.figures

import model.positioning.Move

object Bishop extends Figure {

  val characterOfFigure = 'B'
  val moveInfinitely = true
  val moves = Set(Move(-1, 1), Move(-1, -1), Move(1, -1), Move(1,1))
}