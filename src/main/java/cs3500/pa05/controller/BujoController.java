package cs3500.pa05.controller;

import cs3500.pa05.model.TaskWrapper;
import cs3500.pa05.model.Week;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents a controller for a bullet journal
 */
public interface BujoController extends Controller {

  /**
   * Sets the week name of the bullet journal
   *
   * @param name  The name of the journal, String
   */
  void setWeekName(String name);

  /**
   * Sets the week for this bullet journal
   *
   * @param week  The week for this bullet journal
   */
  void setWeek(Week week);

  /**
   * Handles a user clicking a task button on a week view
   *
   * @param task  The task that is clicked
   * @param button  The button that the task is linked to
   * @param label  The label that displays the task in the task bar
   */
  void handleClickTask(TaskWrapper task, Button button, Label label);

  /**
   * Sets the main scene representing a week view
   *
   * @param scene  The scene that will represent the weekly view
   */
  void setMainScene(Scene scene);

  /**
   * Sets the stage that will hold the scene representing a week view
   *
   * @param stage  The stage that will hold the scene view
   */
  void setStage(Stage stage);

  /**
   * Set the password to the inputted password
   *
   * @param password the password to set to
   */
  void setWeekPassword(String password);
}
