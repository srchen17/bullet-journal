package cs3500.pa05.controller;

import cs3500.pa05.constants.ColorConstants;
import cs3500.pa05.constants.MessageConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * Handles creating a new week in a bullet journal
 */
public class CreateController implements EntryDialogController {
  @FXML
  private TextField name;
  @FXML
  private Button enter;

  private final Dialog createDialog;
  private SwapController swap;

  @FXML
  private Label userWarning;
  @FXML
  private PasswordField passwordField;

  /**
   * Constructor
   *
   * @param createDialog the Dialog object to display on
   */
  public CreateController(Dialog createDialog) {
    this.createDialog = createDialog;
  }

  /**
   * Runs a controller that creates a new week
   */
  @Override
  public void run() {
    Window window = createDialog.getDialogPane().getScene().getWindow();
    window.setOnCloseRequest((e) -> createDialog.hide());
    this.enter.setOnAction(e -> {
      window.hide();
      swap.run();
    });
    createDialog.showAndWait();
  }

  /**
   * Gets the name of this week
   *
   * @return  the week name as a String
   */
  @Override
  public String getName() {
    return this.name.getText();
  }

  /**
   * Gets the password of this week
   *
   * @return  the week's password
   */
  @Override
  public String getPassword() {
    return this.passwordField.getText();
  }

  /**
   * Shows invalid input warning to user if unable ot
   * create a bujo file
   */
  @Override
  public void showInvalidInputWarning() {
    this.userWarning.setText(MessageConstants.INVALID_BUJO_FILE);
    this.userWarning.setStyle(ColorConstants.RED.setBackground(false));
  }

  /**
   * Sets a swap controller in this class
   *
   * @param swap the SwapController object to set to
   */
  @Override
  public void setSwap(SwapController swap) {
    this.swap = swap;
  }
}
