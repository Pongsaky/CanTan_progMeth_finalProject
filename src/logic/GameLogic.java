package logic;


import resource.Board;
import resource.Player;

import java.util.ArrayList;

public class GameLogic {
    private ArrayList<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private boolean setupPhase;
    private static final int VICTORY_POINTS_TO_WIN = 10;

//    public GameLogic(ArrayList<Player> players, Board board) {
//        this.players = players;
//        this.board = board;
//        this.currentPlayerIndex = 0;
//        this.setupPhase = true;
//    }

    public GameLogic(ArrayList<Player> players) {
        this.players = players;
        this.currentPlayerIndex = -1;
        this.setupPhase = true;
    }

    public void startGame() {
        System.out.println("Starting Cantan Game!");
        while (!isGameOver()) {
            if (setupPhase) performSetupPhase();
            else performNormalPhase();
            forwardToNextPlayer();
            break;
        }
        System.out.println("Game over!");
        displayWinner();
    }

    private void performSetupPhase() {
        this.setupPhase = false;

        // Initial from first to last
        for (int i = 0; i < 4; i++) {
            forwardToNextPlayer();
            Player currentPlayer = getCurrentPlayer();
            System.out.println("Forward Setup " + currentPlayer.getName() + " Choose action");
//            placeObject(currentPlayer, );
        }

        // Initial from last to first
        for (int i = 0; i < 4; i++) {
            Player currentPlayer = getCurrentPlayer();
            backwardToNextPlayer();
            System.out.println("Backward Setup " + currentPlayer.getName() + " Choose action");
//            placeObject(currentPlayer, );
        }
    }

    private void performNormalPhase() {

    }

    private void forwardToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    private void backwardToNextPlayer() {
        currentPlayerIndex = ((currentPlayerIndex - 1) + players.size()) % players.size();
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

    private void displayWinner() {
        // Determine the winner based on victory points
        Player winner = null;
        int maxVictoryPoints = 1;
        for (Player player: players) {
            if (player.getVictoryPoints() > maxVictoryPoints) {
                maxVictoryPoints = player.getVictoryPoints();
                winner = player;
            }
        }
        System.out.println("Winner: " + winner.getName());
    }

}
