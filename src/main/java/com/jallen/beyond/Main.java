package com.jallen.beyond;

import java.io.IOException;

import com.jallen.beyond.controller.gem.GemEditDialogController;
import com.jallen.beyond.controller.gem.GemNewDialogController;
import com.jallen.beyond.controller.gem.GemOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.jallen.beyond.model.Gem;

public class Main extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Gems.
     */
    private ObservableList<Gem> gemData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main() {
        //Add some sample data
        gemData.add(new Gem("Azurite", 10));
        gemData.add(new Gem("Banded Agate", 10));
        gemData.add(new Gem("Black Opal", 1000));
        gemData.add(new Gem("Amethyst", 100));
        gemData.add(new Gem("Peridot", 500));
    }

    /**
     * Returns the data as an observable of Gems.
     * @return
     */
    public ObservableList<Gem> getGemData() {
        return gemData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Beyond Clone");

        initRootLayout();

        showGemOverview();
    }

    //Initializes the root layout
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/RootLayout.fxml"));
            //loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Shows the gem overview inside the root layout
    public void showGemOverview() {
        try {
            // Load gem overview
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gem/GemOverview.fxml"));
            //loader.setLocation(Main.class.getResource("view/GemOverview.fxml"));
            AnchorPane gemOverview = (AnchorPane) loader.load();

            // Set gem overview into the center of root layout.
            rootLayout.setCenter(gemOverview);

            // Give the controller access to the main app.
            GemOverviewController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to add details for the new gem. If the user
     * clicks OK, the new gem is saved into the gem object and true
     * is returned.
     *
     * @param gem the gem object to be added
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showGemNewDialog(Gem gem) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gem/GemNewDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add New Gem");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the gem into the controller.
            GemNewDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGem(gem);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for the specified gem. If the user
     * clicks OK, the changes are saved into the provided gem object and true
     * is returned.
     *
     * @param gem the gem object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showGemEditDialog(Gem gem) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gem/GemEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Gem");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the gem into the controller.
            GemEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGem(gem);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
