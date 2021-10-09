package tictactoe.bll;

public class GameBoardFactory {

    public static IGameModel getGameModel(GAME_MODE gameMode) {
        return switch (gameMode) {
            case SINGLE_PLAYER -> new GameBoardSinglePlayer();
            case TWO_PLAYERS -> new GameBoardTwoPlayer();
        };
    }

    public enum GAME_MODE {
        SINGLE_PLAYER, TWO_PLAYERS;

        @Override
        public String toString() {
            return switch (this) {
                case TWO_PLAYERS -> "Two players";
                case SINGLE_PLAYER -> "Single player";
            };
        }
    }

}
