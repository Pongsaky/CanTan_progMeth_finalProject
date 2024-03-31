package resource;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Board extends Application {
  private static final int BOARD_SIZE = 10; // Number of hexagons per row/column
  private static final int HEXAGON_SIZE = 50; // Size of each hexagon
  
  @Override
  public void start(Stage primaryStage) {
    Pane root = new Pane();
    drawHexagonGrid(root);
    Scene scene = new Scene(root, BOARD_SIZE * HEXAGON_SIZE * 3 / 2, BOARD_SIZE * HEXAGON_SIZE * Math.sqrt(3) / 2);
    primaryStage.setTitle("Hexagon Board");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  private void drawHexagonGrid(Pane pane) {
    for (int row = 0; row < BOARD_SIZE; row++) {
      int offsetX = (row % 2 == 0) ? 0 : HEXAGON_SIZE * 3 / 4;
      for (int col = 0; col < BOARD_SIZE; col++) {
        int x = col * HEXAGON_SIZE * 3 / 2 + offsetX;
        int y = row * HEXAGON_SIZE * (int) Math.sqrt(3) / 2;
        Polygon hexagon = createHexagon(x, y);
        pane.getChildren().add(hexagon);
      }
    }
  }
  
  private Polygon createHexagon(int x, int y) {
    Polygon hexagon = new Polygon();
    for (int i = 0; i < 6; i++) {
      double angle = 2 * Math.PI * i / 6;
      int xOffset = (int) (HEXAGON_SIZE * Math.cos(angle));
      int yOffset = (int) (HEXAGON_SIZE * Math.sin(angle));
      hexagon.getPoints().addAll((double) (x + xOffset), (double) (y + yOffset));
    }
    hexagon.setFill(Color.TRANSPARENT);
    hexagon.setStroke(Color.BLACK);
    return hexagon;
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}