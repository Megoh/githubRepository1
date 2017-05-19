package model.figures

import model.positioning.Move

object King extends Figure {

  val characterOfFigure = 'K'
  val moveInfinitely = false
  val moves = Set(Move(0, 1), Move(0, -1), Move(1, -1), Move(1, 0), Move(1, 1), Move(-1, 0), Move(-1, 1), Move(-1, -1))
}