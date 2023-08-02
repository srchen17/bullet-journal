package cs3500.pa05.controller;

import cs3500.pa05.constants.BujoKeyCombo;
import cs3500.pa05.model.Week;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controls the welcome scene
 */
public class WelcomeController implements Controller {

  private final IuploadController templateController;
  @FXML
  private Button open;
  @FXML
  private Button create;
  @FXML
  private Button template;
  private final BujoController bujoController;
  private final IuploadController uploadController;
  private final EntryDialogController createController;
  @FXML
  private Scene mainScene = null;
  @FXML
  private final Stage mainStage;


  /**
   * Constructor
   *
   * @param bujoController given bujoController
   * @param uploadController given uploadController
   * @param createController given createController
   * @param templateController given templateController
   * @param stage given stage
   */
  public WelcomeController(BujoController bujoController, IuploadController uploadController,
                           EntryDialogController createController,
                           IuploadController templateController, Stage stage) {
    this.bujoController = bujoController;
    this.uploadController = uploadController;
    this.createController = createController;
    this.templateController = templateController;
    this.mainStage = stage;
  }

  /**
   *
   * @param mainScene the scene object to set the main scene to
   */
  public void setMainScene(Scene mainScene) {
    this.mainScene = mainScene;
  }

  /**
   * handle pressed key commands and delegate to the appropriate handler method
   *
   * @param event the KeyEvent being pressed
   */
  private void handleKeyEvents(KeyEvent event) {
    if (BujoKeyCombo.openBujoWin.match(event) || BujoKeyCombo.openBujoMac.match(event)) {
      this.handleOpen();
    } else if (BujoKeyCombo.newBujoWin.match(event) || BujoKeyCombo.newBujoMac.match(event)) {
      this.handleCreate();
    }
  }

  /**
   * Runs the dialog and handles events when the user clicks on options
   *
   * @throws IllegalStateException if the file cannot be read when uploading
   */
  @Override
  @FXML
  public void run() throws IllegalStateException {
    this.open.setOnAction(e -> handleOpen());
    this.create.setOnAction(e -> handleCreate());
    this.template.setOnAction(e -> handleTemplate());
    this.mainScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> this.handleKeyEvents(event));
  }

  /**
   * Handle opening a .bujo file and displaying it
   */
  private void handleOpen() {
    uploadController.run();
    try {
      Week week = uploadController.getWeek();
      bujoController.setWeek(week);
      bujoController.setWeekName(uploadController.getName());
      week.verifyPassword(uploadController.getPassword());
    } catch (IllegalStateException | IOException e) {
      this.uploadController.showInvalidInputWarning();
    }
    this.bujoController.run();
  }

  /**
   * Handle opening a .bujo file and using it as a template for a new week
   */
  private void handleTemplate() {
    templateController.run();
    try {
      String password = createController.getPassword();
      Week week = templateController.getWeek();
      bujoController.setWeek(week);
      bujoController.setWeekName(templateController.getName());
      bujoController.setWeekPassword(password);
    } catch (IllegalStateException | IOException e) {
      this.uploadController.showInvalidInputWarning();
    }
    this.bujoController.run();
  }

  /**
   * Handle creating a new week and displaying it
   */
  private void handleCreate() {
    createController.run();
    try {
      String name = createController.getName();
      String password = createController.getPassword();
      bujoController.setWeekName(name);
      bujoController.setWeekPassword(password);
    } catch (IllegalStateException e) {
      this.createController.showInvalidInputWarning();
    }
    this.bujoController.run();
  }
}
