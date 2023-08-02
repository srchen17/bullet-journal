package cs3500.pa05.controller;

import cs3500.pa05.constants.ColorConstants;
import cs3500.pa05.constants.MessageConstants;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskWrapper;
import cs3500.pa05.view.DialogView;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * A controller for an Add Task Dialog, where a user can add a task to a week
 */
public class TaskDialogController implements IcommitmentDialogController {
  private boolean hasValidCommitment;
  private final Dialog taskDialog;
  private DialogView view;
  @FXML
  private TextField taskName;
  @FXML
  private TextField taskDescription;
  private ObservableList<String> daysOfWeek = FXCollections.observableArrayList(
      "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
  @FXML
  private ChoiceBox dayOfWeekChoiceBox;
  @FXML
  private Label userWarning;

  /**
   * Creates a controller for a Task Dialog, which accepts inputs to add a task
   *
   * @param taskDialog The dialog to be shown
   */
  public TaskDialogController(Dialog taskDialog) {
    this.taskDialog = taskDialog;
  }


  /**
   * Runs this Task Dialog controller: sets the days of the week as possible
   * choices for the task, and sets buttons to receive user input
   */
  @Override
  @FXML
  public void run() {
    // set possible choices in day of week choice box
    this.dayOfWeekChoiceBox.setItems(daysOfWeek);
    // show and wait for a button type to be clicked
    final Button finish = (Button) taskDialog.getDialogPane().lookupButton(ButtonType.FINISH);
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
    this.taskDialog.showAndWait();
  }

  /**
   * Shows an invalid input warning to a user
   */
  public void showInvalidInputWarning() {
    this.userWarning.setText(MessageConstants.INVALID_INPUTS);
  }

  /**
   * Validates info about a task the user is attempting to create
   *
   * @return Whether the input info is valid
   */
  private boolean validateCommitment() {
    return !this.taskName.getText().isEmpty()
        && !Objects.isNull(dayOfWeekChoiceBox.getValue());
  }

  /**
   * Gets the newly created task
   *
   * @return The new task
   */
  @Override
  public TaskWrapper getCommitment() {
    if (validateCommitment()) {
      String name = taskName.getText();
      String description = taskDescription.getText();
      DayOfWeek day = DayOfWeek.valueOf(this.dayOfWeekChoiceBox.getValue().toString());
      Task newTask = new Task(name, description);
      taskDescription.setText("");
      taskName.setText("");
      return new TaskWrapper(newTask, day);
    } else {
      throw new IllegalStateException(MessageConstants.INVALID_INPUTS);
    }
  }
}
