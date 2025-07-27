package com.irayspace.learnfx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Map;
import java.util.HashMap;

import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FxApplication extends Application {

    private static ConfigurableApplicationContext springContext;

    public static void setContext(ConfigurableApplicationContext context) {
        springContext = context;
    }

    @Override
	public void start(Stage stage) throws FileNotFoundException {
        final VBox firstScreen = new VBox(20);
        final Label firstLabel = new Label("First Label");
        final Button firstButton = new Button("Proceed");
        firstScreen.getChildren().addAll(firstLabel, firstButton);

        final StackPane root = new StackPane(firstScreen);
        final Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        final Pane secondPane = new Pane();
        final Image mapImage = new Image(new FileInputStream("src/main/resources/map.png"));
        final ImageView mapView = new ImageView(mapImage);
        mapView.setFitWidth(800);
        mapView.setPreserveRatio(true);
        secondPane.getChildren().add(mapView);

        // Buttons
        final Map<String, Button> secondButtons = new HashMap<>();
        secondButtons.put("GS", createButton("GS", 60, 315));
        secondButtons.put("LPS", createButton("LPS", 370, 315));
        secondButtons.put("PG", createButton("PG", 700, 315));
        secondPane.getChildren().addAll(secondButtons.values());

        final Pane secondScreen = new VBox(secondPane);
        
        // Events
        firstButton.setOnAction(e -> {
            root.getChildren().setAll(secondScreen);
        });

        for (Map.Entry<String, Button> entry : secondButtons.entrySet()) {
            entry.getValue().setOnAction(e -> {
                System.out.println("Button: " + entry.getKey());
            });
        }
        
        // Post
        stage.setTitle("Learnfx Application");
        stage.show();
	}
    
    private Button createButton(String label, double x, double y) {
        final Button button = new Button(label);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setStyle("-fx-background-color: rgba(0, 0, 255, 0.5); -fx-text-fill: white;");
        return button;
    }
}
