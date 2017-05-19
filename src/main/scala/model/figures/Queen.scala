package model.figures

import model.positioning.Move

object Queen extends Figure {

  val characterOfFigure = 'Q'
  val moveInfinitely = true
  val moves = Set(Move(0, 1), Move(0, -1), Move(1, -1), Move(1, 0), Move(1, 1), Move(-1, 0), Move(-1, 1), Move(-1, -1))

}