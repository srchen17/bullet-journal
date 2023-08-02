package cs3500.pa05;

import cs3500.pa05.constants.ColorConstants;
import cs3500.pa05.controller.BujoController;
import cs3500.pa05.controller.BujoControllerImpl;
import cs3500.pa05.controller.CreateController;
import cs3500.pa05.controller.EventDialogController;
import cs3500.pa05.controller.MaxCommitmentController;
import cs3500.pa05.controller.MiniEventDialog;
import cs3500.pa05.controller.MiniTaskDialog;
import cs3500.pa05.controller.SwapController;
import cs3500.pa05.controller.TaskDialogController;
import cs3500.pa05.controller.TemplateController;
import cs3500.pa05.controller.UploadController;
import cs3500.pa05.controller.WelcomeController;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.DialogView;
import cs3500.pa05.view.MiniCommitmentDialogView;
import cs3500.pa05.view.View;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Entry point for Bullet Journal Application
 * Includes the setup to use JavaFx and the main method
 */
public class Driver extends Application {

  /**
   * Sets up a Dialog for creating a new bujo file
   *
   * @return  A CreateController for the Dialog
   */
  private CreateController setupCreate() {
    // set up create dialog
    Dialog createDialog = new Dialog();
    CreateController createController = new CreateController(createDialog);
    DialogView createView = new DialogView(createController, "create.fxml");
    createDialog.setDialogPane(createView.load());

    return createController;
  }

  /**
   * Sets up a Dialog for uploading file info
   *
   * @return  A UplaodController for the Dialog
   */
  private UploadController setupUpload(Stage mainStage) {
    // set up upload dialog
    Dialog uploadDialog = new Dialog();
    UploadController uploadController = new UploadController(uploadDialog, mainStage);
    DialogView uploadView = new DialogView(uploadController, "upload.fxml");
    uploadDialog.setDialogPane(uploadView.load());

    return uploadController;
  }

  /**
   * Sets up a Dialog for creating a new bujo template
   *
   * @return  A TemplateController for the Dialog
   */
  private TemplateController setupTemplate(Stage mainStage) {
    // set up create dialog
    Dialog templateDialog = new Dialog();
    TemplateController templateController = new TemplateController(templateDialog, mainStage);
    DialogView templateView = new DialogView(templateController, "template.fxml");
    templateDialog.setDialogPane(templateView.load());

    return templateController;
  }

  /**
   * Sets up a BujoController
   *
   * @param createController  controller for creating a bujo
   * @param uploadController  controller for uploading bujo info
   * @param templateController  a template to start .bujo files
   * @return  A BujoController that is set up
   */
  private BujoController setup(CreateController createController,
                               UploadController uploadController,
                               TemplateController templateController) {
    // set up mini task view dialog & mini event view dialog
    Dialog miniTaskDialog = new Dialog();
    MiniTaskDialog miniTaskController = new MiniTaskDialog(miniTaskDialog);
    MiniCommitmentDialogView miniTaskView =
        new MiniCommitmentDialogView(miniTaskController, "miniTask.fxml");
    miniTaskDialog.setDialogPane(miniTaskView.load());
    miniTaskController.setHostServices(getHostServices());
    Dialog miniEventDialog = new Dialog();
    MiniEventDialog miniEventController = new MiniEventDialog(miniEventDialog);
    MiniCommitmentDialogView miniEventView =
        new MiniCommitmentDialogView(miniEventController, "miniEvent.fxml");
    miniEventDialog.setDialogPane(miniEventView.load());
    miniEventController.setHostServices(getHostServices());
    // set up add event dialog
    Dialog eventDialog = new Dialog();
    EventDialogController eventController = new EventDialogController(eventDialog);
    DialogView dialogView = new DialogView(eventController, "addEventDialog.fxml");
    eventDialog.setDialogPane(dialogView.load());
    // set up add task dialog
    Dialog taskDialog = new Dialog();
    TaskDialogController taskController = new TaskDialogController(taskDialog);
    DialogView taskView = new DialogView(taskController, "addTaskDialog.fxml");
    taskDialog.setDialogPane(taskView.load());
    // set up setting max events/tasks dialog
    Dialog maxDialog = new Dialog();
    MaxCommitmentController maxController = new MaxCommitmentController(maxDialog);
    DialogView maxView = new DialogView(maxController, "maxCommitDialog.fxml");
    maxDialog.setDialogPane(maxView.load());
    // create week
    Week week = new Week(new ArrayList<>(Arrays.asList(new Day(DayOfWeek.SUNDAY),
        new Day(DayOfWeek.MONDAY), new Day(DayOfWeek.TUESDAY), new Day(DayOfWeek.WEDNESDAY),
        new Day(DayOfWeek.THURSDAY), new Day(DayOfWeek.FRIDAY), new Day(DayOfWeek.SATURDAY))));

    return new BujoControllerImpl(eventController, taskController,
        maxController, week, miniTaskController, miniEventController, uploadController,
        createController, templateController);
  }

  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param stage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage stage) {
    // instantiate a simple welcome GUI view
    CreateController createController = this.setupCreate();
    UploadController uploadController = this.setupUpload(stage);
    TemplateController templateController = this.setupTemplate(stage);
    BujoController controller = this.setup(createController, uploadController, templateController);
    View view = new View(controller, "bujoBeautiful.fxml");
    SwapController swap = new SwapController(controller, view, stage);
    uploadController.setSwap(swap);
    createController.setSwap(swap);
    templateController.setSwap(swap);
    WelcomeController welcome =
        new WelcomeController(controller, uploadController, 
            createController, templateController, stage);
    View welcomeView = new View(welcome, "welcome.fxml");

    controller.setStage(stage);
    try {
      // load and place the view's scene onto the stage
      Scene mainScene = welcomeView.load();
      welcome.setMainScene(mainScene);
      Scene splashScreen = this.splashScreen(stage, mainScene);
      stage.setScene(splashScreen);
      welcome.run();
      stage.setTitle("Bullet Journal");
      // render the stage
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * Sets up a Splash Screen, the first "welcome" screen a user
   * is shown
   *
   * @param stage  Holds the main scene
   * @param mainScene  The main scene for this bullet journal
   * @return  a Scene, the splash screen of the project
   */
  private Scene splashScreen(Stage stage, Scene mainScene) {
    try {
      //Scene 1
      Button button1 = new Button("Continue to Bullet Journal >");
      button1.setStyle(ColorConstants.BLACK.setBackground(false));
      button1.setOnAction(e -> stage.setScene(mainScene));
      button1.setPrefHeight(20);
      button1.setFont(Font.font("Book Antiqua"));
      button1.setTextFill(Color.WHITE);
      VBox layout1 = new VBox();
      layout1.setStyle(ColorConstants.BLACK.setBackground(false));
      HBox buttonContainer = new HBox();
      buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
      buttonContainer.getChildren().addAll(button1);
      ImageView imageView = new ImageView();
      InputStream stream = new FileInputStream("src/main/resources/splashScreen.gif");
      Image image = new Image(stream);
      imageView.setImage(image);
      imageView.setPreserveRatio(true);
      imageView.setFitHeight(200);
      layout1.getChildren().addAll(imageView, button1);
      return new Scene(layout1, 352, 230);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Entry point for a bullet journal display.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}
