package ch35;

// Demonstrate a combo box.

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class ComboBoxDemo extends Application {

    ComboBox<String> cbTransport;
    Label response;

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("ComboBox Demo");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 10.
        FlowPane rootNode = new FlowPane(10, 10);

        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 280, 120);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a label.
        response = new Label();

        // Create an ObservableList of entries for the combo box.
        ObservableList<String> transportTypes =
                FXCollections.observableArrayList("Train", "Car", "Airplane");

        // Create a combo box.
        cbTransport = new ComboBox<String>(transportTypes);

        // Set the default value.
        cbTransport.setValue("Train");

        // Set the response label to indicate the default selection.
        response.setText("Selected Transport is " + cbTransport.getValue());

        // Listen for action events on the combo box.
        cbTransport.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Selected Transport is " + cbTransport.getValue());
            }
        });

        // Add the label and combo box to the scene graph.
        rootNode.getChildren().addAll(cbTransport, response);

        // Show the stage and its scene.
        myStage.show();
    }
}
