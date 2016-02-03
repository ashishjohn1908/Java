package ch35;

// Demonstrate a TreeView

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TreeViewDemo extends Application {

    Label response;

    public static void main(String[] args) {

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("Demonstrate a TreeView");

        // Use a FlowPane for the root node. In this case,
        // vertical and horizontal gaps of 10.
        FlowPane rootNode = new FlowPane(10, 10);

        // Center the controls in the scene.
        rootNode.setAlignment(Pos.CENTER);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 310, 460);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create a label that will report the state of the
        // selected tree item.
        response = new Label("No Selection");

        // Create tree items, starting with the root.
        TreeItem<String> tiRoot = new TreeItem<String>("Food");

        // Now add subtrees, begining with fruit.
        TreeItem<String> tiFruit = new TreeItem<String>("Fruit");

        // Construct the Apple subtree.
        TreeItem<String> tiApples = new TreeItem<String>("Apples");
        // Add chilc nodes to the Apple node.
        tiApples.getChildren().add(new TreeItem<String>("Fuji"));
        tiApples.getChildren().add(new TreeItem<String>("Winesap"));
        tiApples.getChildren().add(new TreeItem<String>("Jonathan"));

        // Add varieties to the friut node.
        tiFruit.getChildren().add(tiApples);
        tiFruit.getChildren().add(new TreeItem<String>("Pears"));
        tiFruit.getChildren().add(new TreeItem<String>("Oranges"));

        // Finally, add the fruit node to the root.
        tiRoot.getChildren().add(tiFruit);

        // Now, add vegetables subtree, using the same general process.
        TreeItem<String> tiVegetables = new TreeItem<String>("Vegetables");
        tiVegetables.getChildren().add(new TreeItem<String>("Corn"));
        tiVegetables.getChildren().add(new TreeItem<String>("Peas"));
        tiVegetables.getChildren().add(new TreeItem<String>("Broccoli"));
        tiVegetables.getChildren().add(new TreeItem<String>("Beans"));
        tiRoot.getChildren().add(tiVegetables);

        // Likewise, add nuts subtree.
        TreeItem<String> tiNuts = new TreeItem<String>("Nuts");
        tiNuts.getChildren().add(new TreeItem<String>("Walnuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Peanuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Pecans"));
        tiRoot.getChildren().add(tiNuts);

        // Create tree view using the tree just created.
        TreeView<String> tvFood = new TreeView<String>(tiRoot);

        // Get the tree view selection model.
        MultipleSelectionModel<TreeItem<String>> tvSelModel =
                tvFood.getSelectionModel();

        // Use a change listener to respond to a selection within
        // a tree view
        tvSelModel.selectedItemProperty().addListener(
                new ChangeListener<TreeItem<String>>() {
                    public void changed(
                            ObservableValue<? extends TreeItem<String>> changed,
                            TreeItem<String> oldVal, TreeItem<String> newVal) {

                        // Display the selection and its complete path from the root.
                        if (newVal != null) {

                            // Construct the entire path to the selected item.
                            String path = newVal.getValue();
                            TreeItem<String> tmp = newVal.getParent();
                            while (tmp != null) {
                                path = tmp.getValue() + " -> " + path;
                                tmp = tmp.getParent();
                            }

                            // Display the selection and the entire path.
                            response.setText("Selection is " + newVal.getValue() +
                                    "\nComplete path is " + path);
                        }
                    }
                });

        // Add controls to the scene graph.
        rootNode.getChildren().addAll(tvFood, response);

        // Show the stage and its scene.
        myStage.show();
    }
}