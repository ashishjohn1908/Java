package ch35;

// Demonstrate a scroll pane.
// This program scroll the contents of a multi-line
// label, but any Node can be scrolled.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ScrollPaneDemo extends Application {

    ScrollPane scrlPane;

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Demonstrate a ScrollPane");

        // Use a FlowPane for the root node.
        FlowPane rootNode = new FlowPane(10, 10);

        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 200, 200);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a label that will be scrolled.
        Label scrlLabel = new Label(
                "A ScrollPane streamlines the process of\n" +
                        "adding scroll bars to a window whose\n" +
                        "contents exceed the window's dimensions.\n" +
                        "It also enables a control to fit in a\n" +
                        "smaller space than it otherwise would.\n" +
                        "As such, it often provides a superior\n" +
                        "approach over using individual scrollbars.");

        // Create a scroll pane, setting scrlLabel as the content.
        scrlPane = new ScrollPane(scrlLabel);

        // Set the viewport width and height.
        scrlPane.setPrefViewportWidth(130);
        scrlPane.setPrefViewportHeight(80);

        // Enable paning.
        scrlPane.setPannable(true);


        // Create a reset label.
        Button btnReset = new Button("Reset Scroll Bar Positions");

        // Handle action events for the reset button.
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Set the scroll bars to their zero position.
                scrlPane.setVvalue(0);
                scrlPane.setHvalue(0);
            }
        });

        // Add the label to the scene graph.
        rootNode.getChildren().addAll(scrlPane, btnReset);

        // Show the stage and its scene.
        myStage.show();
    }
}
