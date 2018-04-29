package com.jallen.beyond.controller.gem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.jallen.beyond.model.Gem;
import com.jallen.beyond.Main;

public class GemOverviewController {

    @FXML
    private TableView<Gem> gemTable;
    @FXML
    private TableColumn<Gem, String> gemNameCol;
    @FXML
    private TableColumn<Gem, Integer> gemValueCol;

    @FXML
    private Label gemNameLabel;
    @FXML
    private Label gemValueLabel;
    @FXML
    private Label gemDescripLabel;

    // Reference to the main application
    private Main main;

    /**
     * The constructor
     * The constructor is called before the initialize() method.
     */
    public GemOverviewController() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        gemNameCol.setCellValueFactory(cellData -> cellData.getValue().gemNameProperty());
        gemValueCol.setCellValueFactory(cellData -> cellData.getValue().gemValueProperty().asObject());

        // Clear gem details.
        showGemDetails(null);

        // Listen for selection changes and show the person details when changed.
        gemTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showGemDetails(newValue)));
    }

    /**
     * Fills all text fields to show details about the gem.
     * If the specified gem is null, all text fields are cleared.
     *
     * @param gem the gem or null
     */
    private void showGemDetails(Gem gem) {
        if (gem != null) {
            // Fill the labels with info from the gem object
            gemNameLabel.setText(gem.getGemName());
            gemValueLabel.setText(Integer.toString(gem.getGemValue()));
            gemDescripLabel.setText(gem.getDescription());
        }

        else {
            // Gem is null, remove all the text.
            gemNameLabel.setText("");
            gemValueLabel.setText("");
            gemDescripLabel.setText("");
        }
    }

    /**
     * Is called by the main application to give a reference back to itself
     *
     * @parm main
     */
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
        gemTable.setItems(main.getGemData());
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to create
     * details for a new gem.
     */
    @FXML
    private void handleNewGem() {
        Gem tempGem = new Gem();
        boolean okClicked = main.showGemNewDialog(tempGem);
        if (okClicked) {
            main.getGemData().add(tempGem);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditGem() {
        Gem selectedGem = gemTable.getSelectionModel().getSelectedItem();
        if (selectedGem != null) {
            boolean okClicked = main.showGemEditDialog(selectedGem);
            if (okClicked) {
                showGemDetails(selectedGem);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Gem Selected");
            alert.setContentText("Please select a gem in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteGem() {
        int selectedIndex = gemTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            gemTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Gem Selected");
            alert.setContentText("Please select a gem in the table.");

            alert.showAndWait();
        }
    }
}
