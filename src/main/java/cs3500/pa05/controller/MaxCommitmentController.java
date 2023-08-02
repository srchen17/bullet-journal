package cs3500.pa05.controller;

import cs3500.pa05.constants.ColorConstants;
import cs3500.pa05.constants.MessageConstants;
import cs3500.pa05.view.DialogView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Represents a Controller for a Dialog that sets a max number of
 * tasks and events
 */
public class MaxCommitmentController implements ImaxCommitmentController {
  private boolean hasValidCommitment;
  private final Dialog dialog;
  private DialogView view;
  @FXML
  private TextField maxEvents;
  @FXML
  private TextField maxTasks;
  @FXML
  private Label userWarning;

  /**
   * Constructs a controller for a dialog that sets a
   * maximum number of tasks and events
   *
   * @param dialog given dialog
   */
  public MaxCommitmentController(Dialog dialog) {
    this.dialog = dialog;
  }

  /**
   * Runs a controller for a dialog that sets a max number of commitments:
   * shows and then waits for user input
   */
  @Override
  @FXML
  public void run() {
    // show and wait for a button type to be clicked
    final Button finish = (Button) dialog.getDialogPane().lookupButton(ButtonType.FINISH);
    finish.addEventFilter(ActionEvent.ACTION,
        event -> {
          if (validateCommitment()) {
            this.userWarning.setText(MessageConstants.GOOD_INPUTS);
            this.userWarning.setStyle(ColorConstants.GREEN.setBackground(false));
            this.hasValidCommitment = true;
          } else {
            event.consume();
            this.userWarning.setStyle(ColorConstants.RED.setBackground(false));
            this.userWarning.setText(MessageConstants.INVALID_INPUTS);
          }
        }
    );
    this.dialog.showAndWait();
  }

  /**
   * Shows an invalid input warning to a user
   */
  @Override
  public void showInvalidInputWarning() {
    this.userWarning.setText(MessageConstants.INVALID_INPUTS);
  }

  /**
   * Validates a max number of commitments the user has input
   *
   * @return Whether the user has input valid inputs
   */
  private boolean validateCommitment() {
    boolean valid;
    int maxEventNum;
    int maxTaskNum;
    try {
      maxEventNum = Integer.parseInt(this.maxEvents.getText());
      maxTaskNum = Integer.parseInt(this.maxTasks.getText());
      valid = maxEventNum >= 0 && maxTaskNum >= 0;
    } catch (NumberFormatException e) {
      valid = false;
    }
    return valid;
  }

  /**
   * Gets the maximum number of tasks the user has set
   *
   * @return The int for the max number of tasks
   */
  @Override
  public int getMaxTasks() {
    if (validateCommitment()) {
      int max = Integer.parseInt(this.maxTasks.getText());
      return max;
    } else {
      throw new IllegalStateException(MessageConstants.INVALID_INPUTS);
    }
  }

  /**
   * Gets the maximum number of events the user has set
   *
   * @return The int for the max number of events
   */
  @Override
  public int getMaxEvents() {
    if (validateCommitment()) {
      int max = Integer.parseInt(this.maxEvents.getText());
      return max;
    } else {
      throw new IllegalStateException(MessageConstants.INVALID_INPUTS);
    }
  }
}

