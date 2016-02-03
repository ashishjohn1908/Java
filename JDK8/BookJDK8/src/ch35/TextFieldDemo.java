package ch35;

// Demonstrate a text field.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TextFieldDemo extends Application {

    TextField tf;
    Label response;

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Demonstrate a TextField");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 10.
        FlowPane rootNode = new FlowPane(10, 10);

        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 230, 140);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a label that will report the state of the
        // selected checkbox.
        response = new Label("Search String: ");

        // Create a button that gets the text.
        Button btnGetText = new Button("Get Search String");

        // Create a text field
        tf = new TextField();

        // Set the prompt.
        tf.setPromptText("Enter Search String");

        // Set preferred column count.
        tf.setPrefColumnCount(15);

        // Handle action events for the text field. Action
        // events are generated when ENTER is pressed while
        // the textfield has input focus. In this case, the
        // text in the field is obtained and displayed.
        tf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Search String: " + tf.getText());
            }
        });

        // Get text from the text field when the button is pressed
        // and display it.
        btnGetText.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                response.setText("Search String: " + tf.getText());
            }
        });

        // Use a separator to better organize the layout.
        Separator separator = new Separator();
        separator.setPrefWidth(180);

        // Add controls to the scene graph.
        rootNode.getChildren().addAll(tf, btnGetText, separator, response);

        // Show the stage and its scene.
        myStage.show();
    }
}
