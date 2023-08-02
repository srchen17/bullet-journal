package cs3500.pa05.controller;

import cs3500.pa05.constants.ColorConstants;
import cs3500.pa05.constants.MessageConstants;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventWrapper;
import cs3500.pa05.model.Time;
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
 * Represents adding a new event to the bullet journal
 */
public class EventDialogController implements IcommitmentDialogController {
  private boolean hasValidEvent;
  private final Dialog eventDialog;
  private DialogView view;
  @FXML
  private TextField eventName;
  @FXML
  private TextField eventDescription;
  private final ObservableList<String> daysOfWeek = FXCollections.observableArrayList(
      "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
  @FXML
  private TextField startHour;
  @FXML
  private TextField startMinute;
  @FXML
  private TextField durationHour;
  @FXML
  private TextField durationMinute;
  @FXML
  private ChoiceBox dayOfWeekChoiceBox;
  @FXML
  private Label userWarning;

  /**
   * Constructor
   *
   * @param eventDialog the Dialog object to be displayed
   */
  public EventDialogController(Dialog eventDialog) {
    this.eventDialog = eventDialog;
  }



  /**
   * Runs the dialog and delegates input to the proper handle methods
   */
  @Override
  @FXML
  public void run() {
    // set possible choices in day of week choice box
    this.dayOfWeekChoiceBox.setItems(daysOfWeek);
    // show and wait for a button type to be clicked
    final Button finish = (Button) eventDialog.getDialogPane().lookupButton(ButtonType.FINISH);
    finish.addEventFilter(ActionEvent.ACTION, event -> {
      if (validateEvent()) {
        this.userWarning.setStyle(ColorConstants.GREEN.setBackground(false));
        this.userWarning.setText(MessageConstants.GOOD_INPUTS);
        this.hasValidEvent = true;
      } else {
        event.consume();
        this.userWarning.setStyle(ColorConstants.RED.setBackground(false));
        this.userWarning.setText(MessageConstants.INVALID_INPUTS);
      }
    });
    this.eventDialog.showAndWait();
  }

  /**
   * Shows a warning if the input is not valid
   */
  public void showInvalidInputWarning() {
    this.userWarning.setText(MessageConstants.INVALID_INPUTS);
  }

  /**
   * Validates that the event fields are inputted correctly
   *
   * @return true if the event has valid inputs
   */
  private boolean validateEvent() {
    return !this.eventName.getText().isEmpty()
        && validTimeInput(startHour.getText(), startMinute.getText())
        && validTimeInput(durationHour.getText(), durationMinute.getText())
        && startMinute.getText().length() == 2
        && durationMinute.getText().length() == 2
        && !Objects.isNull(dayOfWeekChoiceBox.getValue());
  }

  /**
   * Checks that the time input was valid
   *
   * @param hour    the hour of the time
   * @param minutes the minute of the time
   * @return true if the time input was valid
   */
  private boolean validTimeInput(String hour, String minutes) {
    boolean valid;
    try {
      int hourInt = Integer.parseInt(hour);
      int minutesInt = Integer.parseInt(minutes);
      valid = (hourInt <= 23) && (hourInt >= 0)
          && (minutesInt >= 0) && (minutesInt <= 59);
    } catch (NumberFormatException e) {
      valid = false;
    }
    return valid;
  }

  /**
   * @return an EventWrapper containing an event and day with all the inputted details
   */
  public EventWrapper getCommitment() {
    if (validateEvent()) {
      String name = eventName.getText();
      String description = eventDescription.getText();
      int startHourInt = Integer.parseInt(startHour.getText());
      int startMinInt = Integer.parseInt(startMinute.getText());
      int durationHourInt = Integer.parseInt(durationHour.getText());
      int durationMinInt = Integer.parseInt(durationMinute.getText());
      final DayOfWeek day = DayOfWeek.valueOf(this.dayOfWeekChoiceBox.getValue().toString());
      final Event newEvent = new Event(name, description, new Time(startHourInt, startMinInt),
          new Time(durationHourInt, durationMinInt));
      this.eventName.setText("");
      this.eventDescription.setText("");
      this.startHour.setText("");
      this.startMinute.setText("");
      this.durationHour.setText("");
      this.durationMinute.setText("");
      return new EventWrapper(newEvent, day);
    } else {
      throw new IllegalStateException(MessageConstants.INVALID_INPUTS);
    }
  }
}
