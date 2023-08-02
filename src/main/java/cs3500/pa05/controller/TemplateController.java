package cs3500.pa05.controller;

import cs3500.pa05.constants.ColorConstants;
import cs3500.pa05.constants.MessageConstants;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.FileHandler;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Controls the upload dialog
 */
public class TemplateController implements IuploadController {
  @FXML
  private TextField file;
  @FXML
  private Button openFile;
  @FXML
  private TextField name;
  @FXML
  private Button enter;
  @FXML
  private Label userWarning;
  @FXML
  private PasswordField passwordField;
  private final Dialog templateDialog;
  private SwapController swap;
  private final Stage mainStage;

  /**
   * Constructor
   *
   * @param templateDialog  a dialog for the template
   * @param mainStage  the main stage to show the scene
   */
  public TemplateController(Dialog templateDialog, Stage mainStage) {
    this.templateDialog = templateDialog;
    this.mainStage = mainStage;
  }

  /**
   * Runs the dialog, processes input from the text fields, and delegate handlers
   */
  @Override
  public void run() {
    Window window = templateDialog.getDialogPane().getScene().getWindow();
    window.setOnCloseRequest(e -> templateDialog.hide());
    this.enter.setOnAction(e -> {
      window.hide();
      swap.run();
    });
    this.openFile.setOnAction(e -> this.handleOpenFile());
    enter.addEventFilter(ActionEvent.ACTION,
        event -> {
          try {
            FileHandler.readFromFiles(file.getText());
          } catch (IOException | IllegalStateException e) {
            event.consume();
            showInvalidInputWarning();
          }
        }
    );
    templateDialog.showAndWait();
  }

  /**
   * Handles opening a file
   */
  @Override
  public void handleOpenFile() {
    final FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open bullet journal");
    fileChooser.setInitialDirectory(
        new File(System.getProperty("user.home"))
    );
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("BUJO", "*.bujo"));
    File file = fileChooser.showOpenDialog(mainStage);
    if (file != null) {
      this.file.setText(file.toString());
    }
  }

  /**
   * Returns the Week object after reading the specified file and deleting events and tasks
   *
   * @return the Week object read from the file
   * @throws IOException if the file can't be read
   */
  @Override
  public Week getWeek() throws IOException {
    Week templateWeek = FileHandler.readFromFiles(file.getText());
    List<Day> templateDays = new ArrayList<>();
    for (Day day : templateWeek.getDays()) {
      templateDays.add(new Day(day.getDay()));
    }

    templateWeek.setDays(templateDays);
    return templateWeek;
  }

  /**
   *
   * @return the name of the file from the .bujo file
   */
  @Override
  public String getName() {
    return this.name.getText();
  }

  /**
   *
   * @return The password entered by the user
   */
  @Override
  public String getPassword() {
    return this.passwordField.getText();
  }

  /**
   * Display an invalid input warning to the user
   */
  @Override
  public void showInvalidInputWarning() {
    this.userWarning.setText(MessageConstants.INVALID_BUJO_FILE);
    this.userWarning.setStyle(ColorConstants.RED.setBackground(false));
  }

  /**
   *
   * @param swap the swap controller object to be set to
   */
  @Override
  public void setSwap(SwapController swap) {
    this.swap = swap;
  }
}
