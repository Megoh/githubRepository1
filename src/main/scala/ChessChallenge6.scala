import model._
import model.figures._
import algorithm.Algorithm

import scala.io.StdIn

object ChessChallenge6 {
	def main(args: Array[String]) {
		println("Please enter the board size")
		val size = StdIn.readInt()
		println("Please enter the figures of type")
		val figuresType = StdIn.readLine().trim
		println(s"The play is starting with ${size}*${size} and ${figuresType}")
		val board = Board.createEmptyBoard(size, size)
		val startedTimer = System.nanoTime()
		val solutions: Set[Board] = Algorithm.calculate(board, Algorithm.getFigures(figuresType))
		val stoppedTimer = System.nanoTime()

		solutions.toSeq.zipWithIndex.foreach {
		case (r, i) => {
			val ii = i + 1
					println(s"Solution $ii")
					println(r.toString)
					println()
		}
		}

		val elapsedTime = (stoppedTimer - startedTimer) / (1000 * 1000)
				println(s"Total elapsed time: ${elapsedTime} ms")
				println(s"Total solution: ${solutions.size}")
	}
}
