package tictactoe.bll;

import tictactoe.gui.model.ScoreModel;

import java.util.Arrays;

/**
 * The GameBoardTwoPlayer class is the mandatory implementation for the TicTacToe assignment.
 * It is used for games where there are two human players.
 */
public class GameBoardTwoPlayer implements IGameModel {
    final static int MAX_DRAWCOUNT = 9;

    int winner;
    int player;
    int drawCounter;
    int[][] gameBoard = new int[3][3];

    ScoreModel scoreModel = new ScoreModel();
    protected GameBoardTwoPlayer() {

        newGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer() {
        if (player == 1)
        {
            player = 0;
        }
        else
        {
            player = 1;
        }
        return player;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It then
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row) {
        if (gameBoard[col][row] == -1)
        {
            gameBoard[col][row] = player;
            drawCounter++;
            return true;
        }
        return false;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    @Override
    public boolean isGameOver() {
        //TODO Implement this method
        for (int i = 0; i < gameBoard.length; i++)
        {
            if (gameBoard[0][i] == player && gameBoard[1][i] == player && gameBoard[2][i] == player)
            {
                winner = player;
                return true;
            }
        }
        for (int i = 0; i < gameBoard[0].length; i++)
        {
            if (gameBoard[i][0] == player && gameBoard[i][1] == player && gameBoard[i][2] == player)
            {
                winner = player;
                return true;
            }
        }

        if (gameBoard[0][0] == player && gameBoard[1][1] == player && gameBoard[2][2] == player)
        {
            winner = player;
            return true;
        }
        else if (gameBoard[0][2] == player && gameBoard[1][1] == player && gameBoard[2][0] == player)
        {
            winner = player;
            return true;
        }

        if (drawCounter == MAX_DRAWCOUNT){
            winner = -1;
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        winner = -1;
        drawCounter = 0;
        getNextPlayer();
        for (int r = 0; r < gameBoard.length; r++)
        {
            for (int c = 0; c < gameBoard[0].length; c++)
            {
                gameBoard[r][c] = -1;
            }
        }
    }

    /**
     * Returns the value representing which players has played the given field.
     *
     * @param col The column to look at.
     * @param row The row to look at.
     * @return Will return 0 if player 0 has played the field, 1 for player one, and -1 if no player has played the field.
     */
    @Override
    public int getPlayerAt(int col, int row) {
        return gameBoard[col][row];
    }

}