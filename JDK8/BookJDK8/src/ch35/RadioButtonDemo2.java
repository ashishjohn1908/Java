package ch35;

// This radio button example demonstrates how the
// currently selected button in a group can be obtained
// under program control, when it is needed, rather
// than responding to action or change events.
//
// In this example, no events related to the radio
// buttons are handled. Instead, the current selection
// is simply obtained when the Confirm Selection push
// button is pressed.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class RadioButtonDemo2 extends Application {

    Label response;
    ToggleGroup tg;

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Demonstrate Radio Buttons");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 10.
        FlowPane rootNode = new FlowPane(10, 10);

        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 200, 140);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create two labels.
        Label choose = new Label("    Select a Transport Type    ");
        response = new Label("No transport confirmed");

        // Create push button will be used to confirm the selection.
        Button btnConfirm = new Button("Confirm Transport Selection");

        // Create the radio buttons.
        RadioButton rbTrain = new RadioButton("Train");
        RadioButton rbCar = new RadioButton("Car");
        RadioButton rbPlane = new RadioButton("Airplane");

        // Create a toggle group.
        tg = new ToggleGroup();

        // Add each button to a toggle group.
        rbTrain.setToggleGroup(tg);
        rbCar.setToggleGroup(tg);
        rbPlane.setToggleGroup(tg);

        // Intially select one of the radio buttons.
        rbTrain.setSelected(true);

        // Handle action events for the confirm button.
        btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Get the radio button that is currently selected.
                RadioButton rb = (RadioButton) tg.getSelectedToggle();

                // Display the selection.
                response.setText(rb.getText() + " is confirmed.");
            }
        });

        // Use a separator to better organize the layout.
        Separator separator = new Separator();
        separator.setPrefWidth(180);

        // Add the label and buttons to the scene graph.
        rootNode.getChildren().addAll(choose, rbTrain, rbCar, rbPlane,
                separator, btnConfirm, response);

        // Show the stage and its scene.
        myStage.show();
    }
}

