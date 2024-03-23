package logic;


import resource.Player;

import java.util.ArrayList;

public class GameLogic {
    private ArrayList<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private boolean setupPhase;
    private static final int VICTORY_POINTS_TO_WIN = 10;

    public GameLogic(ArrayList<Player> players, Board board) {
        this.players = players;
        this.board = board;
        this.currentPlayerIndex = 0;
        this.setupPhase = true;
    }

    public void startGame() {
        while (!isGameOver()) {
            if (setupPhase) performSetupPhase();
            else performNormalPhase();

            switchToNextPlayer();
        }
    }

    private void performSetupPhase() {

    }

    private void performNormalPhase() {

    }

    private void switchToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private boolean isGameOver()  {
        for (Player player : players) {
            if (player.getVictoryPoints() >= VICTORY_POINTS_TO_WIN) return true;
        }

        return false;
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }


}
