package tictactoe.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScoreModel {

    private final ObservableList<String> winners;

    public ScoreModel() {

        winners = FXCollections.observableArrayList();
    }

    public ObservableList<String> getWinners() {

        return winners;
    }

    public void setNextWinner(String winner) {
        int winner2 = Integer.parseInt(winner);

        if (winner2 == -1){
            winners.add("Draw :(");
        }
        else if (winner2 == 0){
            winners.add("Player 0");
        }
        else if (winner2 == 1){
            winners.add("Player 1");
        }
    }

}