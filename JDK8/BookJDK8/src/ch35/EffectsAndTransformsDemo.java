package ch35;

// Demonstrate rotation, scaling, glowing and inner shadow.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class EffectsAndTransformsDemo extends Application {

    double angle = 0.0;
    double glowVal = 0.0;
    boolean shadow = false;
    double scaleFactor = 1.0;

    // Create initial effects and transforms.
    Glow glow = new Glow(0.0);
    InnerShadow innerShadow = new InnerShadow(10.0, Color.RED);
    Rotate rotate = new Rotate();
    Scale scale = new Scale(scaleFactor, scaleFactor);

    // Create four push buttons.
    Button btnRotate = new Button("Rotate");
    Button btnGlow = new Button("Glow");
    Button btnShadow = new Button("Shadow off");
    Button btnScale = new Button("Scale");

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Effects and Transforms Demo");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 10 are used.
        FlowPane rootNode = new FlowPane(10, 10);

        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 300, 100);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Set the initial glow effect.
        btnGlow.setEffect(glow);

        // Add rotation to the transform list for the Rotate button.
        btnRotate.getTransforms().add(rotate);

        // Add scalling to the transform list for the Scale button.
        btnScale.getTransforms().add(scale);

        // Handle the action events for the rotate button.
        btnRotate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Each time button is pressed, it is rotated 30 degrees
                // around its center.
                angle += 30.0;

                rotate.setAngle(angle);
                rotate.setPivotX(btnRotate.getWidth() / 2);
                rotate.setPivotY(btnRotate.getHeight() / 2);
            }
        });

        // Handle the action events for the scale button.
        btnScale.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Each time button is pressed, the button's scale is changed.
                scaleFactor += 0.1;
                if (scaleFactor > 1.0) scaleFactor = 0.4;

                scale.setX(scaleFactor);
                scale.setY(scaleFactor);

            }
        });


        // Handle the action events for the Glow button.
        btnGlow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Each time button is pressed, its glow value is changed.
                glowVal += 0.1;
                if (glowVal > 1.0) glowVal = 0.0;

                // Set the new glow value.
                glow.setLevel(glowVal);
            }
        });

        // Handle the action events for the Shadow button.
        btnShadow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                // Each time button is pressed, its shadow status is changed.
                shadow = !shadow;
                if (shadow) {
                    btnShadow.setEffect(innerShadow);
                    btnShadow.setText("Shadow on");
                } else {
                    btnShadow.setEffect(null);
                    btnShadow.setText("Shadow off");
                }
            }
        });

        // Add the label and buttons to the scene graph.
        rootNode.getChildren().addAll(btnRotate, btnScale, btnGlow, btnShadow);

        // Show the stage and its scene.
        myStage.show();
    }
}


