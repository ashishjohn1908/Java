package ch35;

// Demonstrate an image in a label.

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class LabelImageDemo extends Application {

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Use an Image in a Label");

        // Use a FlowPane for the root node.
        FlowPane rootNode = new FlowPane();

        // Use center alignment.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 300, 200);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create an ImageView that contains the specified image.
        ImageView hourglassIV = new ImageView("hourglass.png");

        // Create a label that contains both an image and text.
        Label hourglassLabel = new Label("Hourglass", hourglassIV);

        // Add the label to the scene graph.
        rootNode.getChildren().add(hourglassLabel);

        // Show the stage and its scene.
        myStage.show();
    }
}
