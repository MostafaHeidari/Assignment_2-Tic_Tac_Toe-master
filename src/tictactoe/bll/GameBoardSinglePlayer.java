package tictactoe.bll;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The GameBoardSinglePlayer class is the optional and advanced implementation for the TicTacToe assignment.
 * It is used for games where there are one human player vs. a computer player.
 */
public class GameBoardSinglePlayer implements IGameModel {
    final static int MAX_DRAWCOUNT = 9;

    int randCol;
    int randRow;
    int winner;
    int player;
    int drawCounter;
    int[][] gameBoard = new int[3][3];

    protected GameBoardSinglePlayer() {

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
     * Attempts to let the current player play at the given coordinates. If the
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

        boolean whileCondition = true;
        boolean canplay;
        if (gameBoard[col][row] == -1 && !isGameOver())
        {
            gameBoard[col][row] = 0;
            drawCounter++;
            canplay = true;
        }else canplay = false;

        while (whileCondition && canplay && !isGameOver()){
            getNextPlayer();
            randCoords();
            if (gameBoard[randCol][randRow] == -1){

                gameBoard[randCol][randRow] = 1;
                drawCounter++;
                whileCondition = false;
            }
        }
        return canplay;
    }

    /**
     * creates a random number for the AI to move with
     */
    public void randCoords(){
        randCol = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        randRow = ThreadLocalRandom.current().nextInt(0, 2 + 1);

        System.out.println("Col:" + randCol);
        System.out.println("Row:" + randRow);
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    @Override
    public boolean isGameOver() {
        for (int i = 0; i < gameBoard.length; i++)
        {
            if (gameBoard[0][i] == 1 && gameBoard[1][i] == 1 && gameBoard[2][i] == 1)
            {
                winner = 1;
                return true;
            }
            else if (gameBoard[0][i] == 0 && gameBoard[1][i] == 0 && gameBoard[2][i] == 0){
                winner = 0;
                return true;
            }
        }
        for (int i = 0; i < gameBoard[0].length; i++)
        {
            if (gameBoard[i][0] == 1 && gameBoard[i][1] == 1 && gameBoard[i][2] == 1)
            {
                winner = 1;
                return true;
            }
            else if(gameBoard[i][0] == 0 && gameBoard[i][1] == 0 && gameBoard[i][2] == 0){
                winner = 0;
                return true;
            }
        }

        if (gameBoard[0][0] == 1 && gameBoard[1][1] == 1 && gameBoard[2][2] == 1)
        {
            winner = 1;
            return true;
        }
        else if (gameBoard[0][0] == 0 && gameBoard[1][1] == 0 && gameBoard[2][2] == 0){
            winner = 0;
            return true;
        }
        else if (gameBoard[0][2] == 1 && gameBoard[1][1] == 1 && gameBoard[2][0] == 1)
        {
            winner = 1;
            return true;
        }
        else if (gameBoard[0][2] == 0 && gameBoard[1][1] == 0 && gameBoard[2][0] == 0){
            winner = 0;
            return true;
        }

        if (drawCounter == MAX_DRAWCOUNT){
            winner = -1;
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw or if the game is still running.
     *
     * @return int id of winner, or -1 if draw or if gameOver() == false.
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
