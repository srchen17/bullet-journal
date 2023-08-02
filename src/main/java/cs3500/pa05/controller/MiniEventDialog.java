package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventWrapper;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * A controller for showing a Mini Event Dialog
 */
public class MiniEventDialog implements MiniCommitmentDialog {
  private final Dialog eventDialog;
  @FXML
  private Text name;
  @FXML
  private ScrollPane description;
  @FXML
  private Text day;
  @FXML
  private Text start;
  @FXML
  private Text duration;
  @FXML
  private Label title;
  @FXML
  private DialogPane miniView;
  private EventWrapper eventWrapper;
  private HostServices hostServices;

  /**
   * Creates a MiniEventDialog, which takes in a dialog
   *
   * @param eventDialog The dialog that will be shown to the user
   */
  public MiniEventDialog(Dialog eventDialog) {
    this.eventDialog = eventDialog;
  }

  /**
   * Sets an event to be shown for this mini viewer controller
   *
   * @param eventWrapper A wrapper of the event to be shown
   */
  public void setEvent(EventWrapper eventWrapper) {
    this.eventWrapper = eventWrapper;
  }

  /**
   * Runs this controller, sets up the dialog and waits for user and input
   */
  @FXML
  public void run() {
    this.setupDialog();
    this.eventDialog.showAndWait();
  }

  /**
   * Sets up a Mini Event dialog, creates a link if a user puts one in the
   * description
   */
  public void setupDialog() {
    Event event = eventWrapper.getCommitment();
    this.name.setText(event.getName());
    String eventString = event.getDescription();
    TextFlow textFlow = new TextFlow();
    for (String word : eventString.split("https")) {
      if (word.contains("://")) {
        Hyperlink hyperlink = new Hyperlink("https" + word);
        hyperlink.setOnAction(e -> this.hostServices.showDocument(hyperlink.getText()));
        textFlow.getChildren().add(hyperlink);
      } else {
        textFlow.getChildren().add(new Text(word));
      }
    }
    description.setContent(textFlow);
    this.day.setText(String.valueOf(eventWrapper.getDay()));
    this.start.setText(event.getTime().toString());
    this.duration.setText(event.getDuration().toString());
    this.title.setStyle(eventWrapper.getDay().getColor().setBackground(false));
    this.miniView.setStyle(eventWrapper.getDay().getColor().setBackground(true));
  }

  /**
   * Sets host services for this mini event view controller
   *
   * @param hostServices the HostService to open links from description in browser
   */
  public void setHostServices(HostServices hostServices) {
    this.hostServices = hostServices;
  }
}
