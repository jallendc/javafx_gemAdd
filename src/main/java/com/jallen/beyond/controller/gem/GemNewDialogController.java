package com.jallen.beyond.controller.gem;

import com.jallen.beyond.model.Gem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GemNewDialogController {

    @FXML
    private TextField gemNameField;
    @FXML
    private TextField gemValueField;
    @FXML
    private TextField gemDescripField;

    private Stage dialogStage;
    private Gem gem;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the gem to be edited in the dialog.
     *
     * @param gem
     */
    public void setGem(Gem gem) {
        this.gem = gem;
    }

    /**
     *  Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            gem.setGemName(gemNameField.getText());
            gem.setGemValue(Integer.parseInt(gemValueField.getText()));
            gem.setDescription(gemDescripField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (gemNameField.getText() == null || gemNameField.getText().length() == 0) {
            errorMessage += "No valid gem name!\n";
        }
        if (gemValueField.getText() == null || gemValueField.getText().length() == 0) {
            errorMessage += "No valid gem value!\n";
        } else {
            // try to parse the gem value into an int.
            try {
                Integer.parseInt(gemValueField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid gem value (must be an integer)!\n";
            }
        }
        if (gemDescripField.getText() == null || gemDescripField.getText().length() == 0) {
            errorMessage += "No valid gem description!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
