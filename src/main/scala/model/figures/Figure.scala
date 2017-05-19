package model.figures

import model.positioning.Move

abstract class Figure {  
  val characterOfFigure: Char
  val moveInfinitely: Boolean
  val moves: Set[Move]
  override def toString = characterOfFigure.toString
}