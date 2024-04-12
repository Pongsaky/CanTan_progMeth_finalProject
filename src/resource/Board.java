package resource;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Map;

public class Board extends Application {
  
  private final static double HEX_RAD_DELTA = Math.PI / 3;
  
  public static Polygon createHexagon (double centerX, double centerY, double radius, Paint fill) {
    Polygon hex = new Polygon();
    
    // comparing to 6 is enough to ensure every angle is used once here
    // since (5/6) * 2 * PI < 6 < 2 * PI
    for (double rad = 0; rad < 6; rad += HEX_RAD_DELTA) {
      hex.getPoints().addAll(Math.cos(rad) * radius + centerX, Math.sin(rad) * radius + centerY);
    }
    
    hex.setFill(fill);
    hex.setStroke(Color.BLACK);
    return hex;
  }
  
  @Override
  public void start (Stage primaryStage) {
    Color[] fills = new Color[]{
            Color.GREEN,
            Color.BLUE,
    };
    
    Integer[] landsizeForY234 = new Integer[]{1, 5};
    Map<Integer, Integer[]> toFills = Map.of(
            1, new Integer[]{3, 3},
            2, landsizeForY234,
            3, landsizeForY234,
            4, landsizeForY234,
            5, new Integer[]{2, 4}
    );
    
    final double radius = 50;
    final double dY = radius * Math.sqrt(3) / 2;
    
    Pane root = new Pane();
    for (int y = 0; y < 7; y++) {
      double offsetY = 2 * dY * y + 50;
      
      boolean isLand = y > 0 && y < 6;
      int landL = 0;
      int landR = 0;
      if (isLand) {
        landL = toFills.get(y)[0];
        landR = toFills.get(y)[1];
      }
      
      
      for (int x = 0; x < 7; x++) {
        if (y == 0 && x%2 == 0) continue;
        
        Color fill;
        if (isLand && x >= landL && x <= landR) fill = fills[0];
        else fill = fills[1];
        
        root.getChildren().add(createHexagon(
                1.5 * radius * x + 50,
                (x & 1) == 0 ? offsetY : offsetY + dY,
                radius,
                fill
        ));
      }
    }
    
    Scene scene = new Scene(root);
    
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}