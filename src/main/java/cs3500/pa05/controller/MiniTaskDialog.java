package cs3500.pa05.controller;

import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskWrapper;
import cs3500.pa05.view.DialogView;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represents a controller for a Mini Task view Dialog
 */
public class MiniTaskDialog implements MiniCommitmentDialog {
  private final Dialog taskDialog;
  private DialogView view;
  @FXML
  private Text name;
  @FXML
  private ScrollPane description;
  @FXML
  private Text day;
  @FXML
  private Text completion;
  @FXML
  private Button markComplete;
  private boolean markedComplete;
  private TaskWrapper taskWrapper;
  private HostServices hostServices;
  @FXML
  private Label title;
  @FXML
  private DialogPane miniView;

  /**
   * Creates a mini task dialog controller
   *
   * @param taskDialog The dialog that will be shown
   */
  public MiniTaskDialog(Dialog taskDialog) {
    this.taskDialog = taskDialog;
  }



  /**
   * Sets the task to be shown and interacted with through this controller
   *
   * @param taskWrapper A wrapper for the task
   */
  public void setTask(TaskWrapper taskWrapper) {
    this.taskWrapper = taskWrapper;
  }

  /**
   * Runs the mini task dialog controller
   */
  @FXML
  public void run() {
    this.setupDialog();
    this.markComplete.setOnAction(e -> handleMarkComplete());
    this.taskDialog.showAndWait();
  }

  /**
   * Sets up the mini event view dialog controller,
   * Creates any clickable links for the event
   */
  public void setupDialog() {
    Task task = taskWrapper.getCommitment();
    this.name.setText(task.getName());
    String taskString = task.getDescription();
    TextFlow textFlow = new TextFlow();
    for (String word : taskString.split("https")) {
      if (word.contains("://")) {
        Hyperlink hyperlink = new Hyperlink("https" + word);
        hyperlink.setOnAction(e -> this.hostServices.showDocument(hyperlink.getText()));
        textFlow.getChildren().add(hyperlink);
      } else {
        textFlow.getChildren().add(new Text(word));
      }
    }
    description.setContent(textFlow);
    this.day.setText(String.valueOf(taskWrapper.getDay()));
    String complete;
    if (task.getComplete()) {
      complete = "Complete";
    } else {
      complete = "Incomplete";
    }
    this.completion.setText(complete);
    this.title.setStyle(taskWrapper.getDay().getColor().setBackground(false));
    this.miniView.setStyle(taskWrapper.getDay().getColor().setBackground(true));
  }

  /**
   * Handles a user clicking the Mark Complete button by marking this
   * task as complete
   */
  public void handleMarkComplete() {
    this.markedComplete = true;
  }

  /**
   * Returns whether this task was marked as complete
   *
   * @return whether the task was marked complete
   */
  public boolean markedComplete() {
    return this.markedComplete;
  }

  /**
   * Sets host services for this mini task controller
   *
   * @param hostServices host services for this controller
   */
  public void setHostServices(HostServices hostServices) {
    this.hostServices = hostServices;
  }
}
