import kotlin.system.exitProcess

class TicTacToe {
    private val board = MutableList(9) { ' ' }
    private var currentPlayer = 'X'

    fun play() {
        println("=== Tic-Tac-Toe ===")
        printBoard()
        while (true) {
            playerMove()
            if (checkWin(currentPlayer)) {
                printBoard()
                println("Player $currentPlayer wins!")
                return
            }
            if (isDraw()) {
                printBoard()
                println("It’s a draw!")
                return
            }
            switchPlayer()
        }
    }

    private fun printBoard() {
        println()
        for (r in 0..2) {
            val i = r * 3
            println(" ${cellChar(i)} | ${cellChar(i+1)} | ${cellChar(i+2)} ")
            if (r < 2) println("---+---+---")
        }
        println("\nPositions: 1 2 3 / 4 5 6 / 7 8 9\n")
    }

    private fun cellChar(i: Int): Char = if (board[i] == ' ') ' ' else board[i]

    private fun playerMove() {
        while (true) {
            print("Player $currentPlayer, choose a position (1-9) or Q to quit: ")
            val input = readLine()?.trim()?.uppercase() ?: ""
            if (input == "Q") {
                println("Goodbye!")
                exitProcess(0)
            }
            val pos = input.toIntOrNull()
            if (pos == null || pos !in 1..9) {
                println("Invalid input. Enter a number from 1 to 9.")
                continue
            }
            val idx = pos - 1
            if (board[idx] != ' ') {
                println("That spot is taken. Try another.")
                continue
            }
            board[idx] = currentPlayer
            printBoard()
            break
        }
    }

    private fun checkWin(p: Char): Boolean {
        val b = board
        val wins = arrayOf(
            intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8),
            intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8),
            intArrayOf(0,4,8), intArrayOf(2,4,6)
        )
        return wins.any { (a,b1,c) -> b[a] == p && b[b1] == p && b[c] == p }
    }

    private fun isDraw(): Boolean = board.all { it != ' ' }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
    }
}

fun main() {
    TicTacToe().play()
}
