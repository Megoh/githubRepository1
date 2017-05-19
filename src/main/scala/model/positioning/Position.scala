package model.positioning

case class Position(x: Int, y: Int) {
  def +(move: Move) = Position(this.x + move.x, this.y + move.y)
}
