package Test;


import logic.GameLogic;
import resource.Board;
import resource.Player;

import java.util.ArrayList;

public class TestGameLogicCLI {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        // Initial players
        for (int i = 0; i < 4; i++) players.add(new Player("Player"+ (i +1)));
        new GameLogic(players).startGame();
    }
}
