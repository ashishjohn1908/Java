package ch34;

// Demonstrate drawing.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DirectDrawDemo extends Application {

    GraphicsContext gc;

    Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK};
    int colorIdx = 0;

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Draw Directly to a Canvas.");

        // Use a FlowPane for the root node.
        FlowPane rootNode = new FlowPane();

        // Center the nodes in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 450, 450);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a canvas.
        Canvas myCanvas = new Canvas(400, 400);

        // Get the graphics context for the canvas.
        gc = myCanvas.getGraphicsContext2D();

        // Create a push button.
        Button btnChangeColor = new Button("Change Color");

        // Handle the action events for the Change Color button.
        btnChangeColor.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {

                // Set the stroke and fill color.
                gc.setStroke(colors[colorIdx]);
                gc.setFill(colors[colorIdx]);

                // Redraw the line, text, and filled rectangle in the
                //  new color. This leaves the color of the other nodes
                // unchanged.
                gc.strokeLine(0, 0, 200, 200);
                gc.fillText("This is drawn on the canvas.", 60, 50);
                gc.fillRect(100, 320, 300, 40);

                // Change the color.
                colorIdx++;
                if (colorIdx == colors.length) colorIdx = 0;
            }
        });

        // Draw initial output on the canvas.
        gc.strokeLine(0, 0, 200, 200);
        gc.strokeOval(100, 100, 200, 200);
        gc.strokeRect(0, 200, 50, 200);
        gc.fillOval(0, 0, 20, 20);
        gc.fillRect(100, 320, 300, 40);

        // Set the font size to 20 and draw text.
        gc.setFont(new Font(20));
        gc.fillText("This is drawn on the canvas.", 60, 50);

        // Add the canvas and button to the scene graph.
        rootNode.getChildren().addAll(myCanvas, btnChangeColor);

        // Show the stage and its scene.
        myStage.show();
    }
}


