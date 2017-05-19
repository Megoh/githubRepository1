package model.figures

import model.positioning.Move

object Knight extends Figure {

  val characterOfFigure = 'N'
  val moveInfinitely = false
  val moves = Set(Move(1, 2), Move(-1, 2), Move(1, -2), Move(-1, -2), Move(-2, 1), Move(-2, -1), Move(2, 1), Move(2, -1))

}