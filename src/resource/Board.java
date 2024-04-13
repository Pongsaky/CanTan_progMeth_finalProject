package resource;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Map;

public class Board extends Application {
  private static final Color[] TileColors = new Color[]{
          Color.GREEN,
          Color.BLUE,
  };
  private static final double RADIUS = 50;
  private static final double D_Y = RADIUS * Math.sqrt(3) / 2;
  private final static double HEX_RAD_DELTA = Math.PI / 3;
  private Color cornerColor = Color.TRANSPARENT; // house/city representation
  private final Map<Integer, Integer[]> landMap = Map.of(
          1, new Integer[]{3, 3},
          2, new Integer[]{1, 5},
          3, new Integer[]{1, 5},
          4, new Integer[]{1, 5},
          5, new Integer[]{2, 4}
  ); // this is the range of the land base on number of tile
  
  private Group createHexagon (double centerX, double centerY, Paint fill) {
    Polygon hex = new Polygon();
    
    // comparing to 6 is enough to ensure every angle is used once here
    // since (5/6) * 2 * PI < 6 < 2 * PI
    for (double rad = 0; rad < 6; rad += HEX_RAD_DELTA) {
      hex.getPoints().addAll(Math.cos(rad) * Board.RADIUS + centerX, Math.sin(rad) * Board.RADIUS + centerY);
    }
    
    hex.setFill(fill);
    hex.setStroke(Color.BLACK);
    
    // Create a Group to hold the hexagon and the corner clickable areas
    Group group = new Group(hex);
    
    // Add a click event handler to each corner of the hexagon
    for (int i = 0; i < hex.getPoints().size(); i += 2) {
      double x = hex.getPoints().get(i);
      double y = hex.getPoints().get(i + 1);
      Polygon corner = new Polygon(x - 15, y - 15, x + 15, y - 15, x + 15, y + 15, x - 15, y + 15);
      corner.setFill(this.cornerColor);
      corner.setOnMouseClicked(event -> System.out.println("Clicked on corner at (" + x + ", " + y + ")"));
      
      group.getChildren().add(corner);
    }
    
    return group;
  }
  
  @Override
  public void start (Stage primaryStage) {
    
    
    
    Pane root = new Pane();
    
    for (int row = 0; row < 7; row++) {
      double offsetY = 2 * D_Y * row + 50;
      
      boolean isLand = row > 0 && row < 6;
      int leftLand = 0;
      int rightLand = 0;
      if (isLand) {
        leftLand = landMap.get(row)[0];
        rightLand = landMap.get(row)[1];
      }
      
      for (int col = 0; col < 7; col++) {
        if (row == 0 && col % 2 == 0) continue;
        
        Color tileColor;
        
        if (isLand && col >= leftLand && col <= rightLand) tileColor = TileColors[0];
        else tileColor = TileColors[1];
        
        root.getChildren().add(createHexagon(
                1.5 * RADIUS * col + 50,
                (col & 1) == 0 ? offsetY : offsetY + D_Y,
                tileColor
        ));
      }
    }
    
    Scene scene = new Scene(root);
    
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public boolean isLandtile(double x, double y) {
    // Calculate the column and row of the tile based on the (x, y) coordinate
    int col = (int) Math.floor(x / (1.5 * RADIUS));
    int row = (int) Math.floor((y - (col % 2 == 0 ? 0 : D_Y)) / D_Y);
    
    // Check if the tile is within the 5x5 middle region
    boolean isLand = row > 0 && row < 6;
    int leftLand = landMap.get(row)[0];
    int rightLand = landMap.get(row)[1];
    
    // Return true if the tile is green, false otherwise
    return isLand && col >= leftLand && col <= rightLand;
  }
  
  public Color getCornerColor () {return this.cornerColor;}
  
  public void setCornerColor (Color color) {
    this.cornerColor = color;
  }
  
}