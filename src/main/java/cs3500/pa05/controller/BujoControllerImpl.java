package cs3500.pa05.controller;

import cs3500.pa05.constants.BujoKeyCombo;
import cs3500.pa05.constants.ColorConstants;
import cs3500.pa05.constants.MessageConstants;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventWrapper;
import cs3500.pa05.model.FileHandler;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskWrapper;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A controller for a Bullet Journal showing a Week view
 */
public class BujoControllerImpl implements BujoController {
  @FXML
  private TextArea quotes;
  private BujoView view;
  private Week week;
  @FXML
  private VBox taskBar;
  @FXML
  private Label weekName;
  @FXML
  private MenuItem addEvent;
  @FXML
  private MenuItem save;
  @FXML
  private MenuItem addTask;
  @FXML
  private MenuItem open;
  @FXML
  private MenuItem template;
  @FXML
  private MenuItem newBujo;
  @FXML
  private MenuItem weekLeftMenu;
  @FXML
  private MenuItem weekRightMenu;
  private final EventDialogController eventController;
  @FXML
  private VBox sunday;
  @FXML
  private VBox monday;
  @FXML
  private VBox tuesday;
  @FXML
  private VBox wednesday;
  @FXML
  private VBox thursday;
  @FXML
  private VBox friday;
  @FXML
  private VBox saturday;
  private final TaskDialogController taskController;
  private final MaxCommitmentController maxController;
  @FXML
  private MenuItem setMax;
  @FXML
  private Label maxCommitmentWarning;
  @FXML
  private Button refreshTasks;
  @FXML
  private Text totalEvents;
  @FXML
  private Text totalTasks;
  @FXML
  private Text percentTasksComplete;
  @FXML
  private Scene mainScene;
  @FXML
  private HBox weekDays;
  private final IuploadController uploadController;
  private final IuploadController templateController;
  private final EntryDialogController createController;
  private Stage stage;
  MiniTaskDialog miniTaskController;
  MiniEventDialog miniEventController;
  @FXML
  private Button weekLeft;
  @FXML
  private Button weekRight;

  /**
   * Constructs a Bullet Journal controller
   *
   * @param eventController            A controller for an Add Event dialog
   * @param taskController             A controller for an Add Task dialog
   * @param maxController              A controller for a dialog that sets max tasks/events
   * @param week                       The week represented by the bullet journal
   * @param miniTaskController         A dialog for a mini-view of a task
   * @param miniEventController        A dialog for a mini-view of an event
   * @param uploadController           A controller to upload a .bujo file to display
   * @param createController           A controller to create a new week
   * @param templateController         A controller to upload a .bujo file from a template
   */
  public BujoControllerImpl(EventDialogController eventController,
                            TaskDialogController taskController,
                            MaxCommitmentController maxController, Week week,
                            MiniTaskDialog miniTaskController,
                            MiniEventDialog miniEventController,
                            IuploadController uploadController,
                            EntryDialogController createController,
                            IuploadController templateController) {
    this.eventController = eventController;
    this.taskController = taskController;
    this.maxController = maxController;
    this.miniTaskController = miniTaskController;
    this.miniEventController = miniEventController;
    this.week = week;
    this.uploadController = uploadController;
    this.createController = createController;
    this.templateController = templateController;
  }

  /**
   * Sets the stage for this bullet journal controller
   *
   * @param stage The stage that will hold the scene view
   */
  @Override
  public void setStage(Stage stage) {
    this.stage = stage;
  }

  /**
   * Sets the weeks password to the given input
   *
   * @param password the password to set to
   */
  @Override
  public void setWeekPassword(String password) {
    this.week.setPassword(password);
  }

  /**
   * Sets the main scene for the bullet journal, representing the weekly view
   *
   * @param mainScene The scene that will represent the weekly view
   */
  @Override
  public void setMainScene(Scene mainScene) {
    this.mainScene = mainScene;
  }

  /**
   * Runs the upload controller to open an existing .bujo file
   */
  private void handleOpen() {
    uploadController.run();
    BujoControllerImpl controller = new BujoControllerImpl(eventController, taskController,
        maxController, week, miniTaskController, miniEventController, uploadController,
        createController, templateController);
    View view = new View(controller, "bujoBeautiful.fxml");
    try {
      Week week = uploadController.getWeek();
      week.verifyPassword(uploadController.getPassword());
      this.setWeek(week);
      final String name = uploadController.getName();
      // load and place the view's scene onto the stage
      Scene mainScene = view.load();
      controller.setMainScene(mainScene);
      stage.setScene(mainScene);
      controller.run();
      stage.setTitle("Bullet Journal");
      // render the stage
      stage.show();
      controller.setWeek(week);
      controller.setWeekName(name);
      controller.setStage(stage);
    } catch (IOException e) {
      this.uploadController.showInvalidInputWarning();
    }
  }

  /**
   * Runs the template controller to open an existing .bujo file as a template
   */
  private void handleTemplate() {
    templateController.run();
    BujoControllerImpl controller = new BujoControllerImpl(eventController, taskController,
        maxController, week, miniTaskController, miniEventController, uploadController,
        createController, templateController);
    View view = new View(controller, "bujoBeautiful.fxml");
    try {
      Week week = templateController.getWeek();
      this.week.setPassword(templateController.getPassword());
      this.setWeek(week);
      final String name = templateController.getName();
      // load and place the view's scene onto the stage
      Scene mainScene = view.load();
      controller.setMainScene(mainScene);
      stage.setScene(mainScene);
      controller.run();
      stage.setTitle("Bullet Journal");
      // render the stage
      stage.show();
      controller.setWeek(week);
      controller.setWeekName(name);
      controller.setStage(stage);
    } catch (IOException e) {
      this.templateController.showInvalidInputWarning();
    }
  }

  /**
   * Runs the create controller to create a new week
   */
  private void handleCreate() {
    createController.run();
    BujoControllerImpl controller = new BujoControllerImpl(eventController, taskController,
        maxController, week, miniTaskController, miniEventController, uploadController,
        createController, templateController);
    View view = new View(controller, "bujoBeautiful.fxml");
    try {
      final String name = createController.getName();
      this.week.setPassword(createController.getPassword());
      // load and place the view's scene onto the stage
      Scene mainScene = view.load();
      controller.setMainScene(mainScene);
      stage.setScene(mainScene);
      controller.run();
      stage.setTitle("Bullet Journal");
      // render the stage
      stage.show();
      controller.setWeekName(name);
      controller.setStage(stage);
    } catch (IllegalStateException e) {
      this.createController.showInvalidInputWarning();
    }
  }

  /**
   * Runs the bullet journal controller and sets each button to an action
   *
   * @throws IllegalStateException when a key press state is not allowed
   */
  @Override
  @FXML
  public void run() throws IllegalStateException {
    this.addEvent.setOnAction(e -> this.handleAddEvent());
    this.addTask.setOnAction(e -> this.handleAddTask());
    this.setMax.setOnAction(e -> this.handleSetMax());
    this.save.setOnAction(e -> this.handleSaveBujo());
    this.newBujo.setOnAction(e -> this.handleCreate());
    this.open.setOnAction(e -> this.handleOpen());
    this.template.setOnAction(e -> this.handleTemplate());
    this.weekLeft.setOnAction(e -> this.handleChangeWeekStartLeft());
    this.weekRight.setOnAction(e -> this.handleChangeWeekStartRight());
    this.weekLeftMenu.setOnAction(e -> this.handleChangeWeekStartLeft());
    this.weekRightMenu.setOnAction(e -> this.handleChangeWeekStartRight());

    this.mainScene.addEventHandler(KeyEvent.KEY_RELEASED, this::handleKeyEvents);
    this.week.setQuotes(this.quotes.getText());
  }

  /**
   * Sets the name of the week to be the input
   *
   * @param name The name of the journal, String
   */
  @Override
  @FXML
  public void setWeekName(String name) {
    this.week.setName(name);
    this.weekName.setText(name);
  }

  /**
   * Sets the week to be the input week, used for uploading and creating new weeks
   *
   * @param week The week for this bullet journal
   */
  @Override
  public void setWeek(Week week) {
    this.week = new Week(weekName.getText(), new ArrayList<>(Arrays.asList(
        new Day(DayOfWeek.SUNDAY), new Day(DayOfWeek.MONDAY), new Day(DayOfWeek.TUESDAY),
        new Day(DayOfWeek.WEDNESDAY), new Day(DayOfWeek.THURSDAY), new Day(DayOfWeek.FRIDAY),
        new Day(DayOfWeek.SATURDAY))), new ArrayList<>(), "", "");
    DayOfWeek startingDay = week.firstDayOfWeek();
    while (this.week.firstDayOfWeek() != startingDay) {
      this.handleChangeWeekStartLeft();
    }
    this.week.setPassword(week.getPassword());
    for (Day day : week.getDays()) {
      this.week.setMaxCommitments(day.getMaxEvents(), day.getMaxTasks());
      for (Task task : day.getListOfTasks()) {
        try {
          this.week.addTask(day.getDay(), task);
          Label taskLabel = new Label(task.toTaskBarString());
          this.addTaskToTaskBar(taskLabel);
          Button button = new Button(task.toString());
          button.setOnAction(
              e -> handleClickTask(new TaskWrapper(task, day.getDay()), button, taskLabel));
          this.addCommitmentToDay(day.getDay(), button);
          if (task.getComplete()) {
            this.handleMarkTaskComplete(task, button, taskLabel);
          }
          this.totalTasks.setText("Total Tasks: " + this.week.getQueue().size());
          this.percentTasksComplete.setText(MessageConstants.PERCENT_COMPLETE
              + this.week.countPercentCompleteTasks() + "%");
        } catch (IllegalStateException ignore) {
          // exception ignored
        }
      }
      for (Event event : day.getListOfEvents()) {
        try {
          this.week.addEvent(day.getDay(), event);
          Button button = new Button(event.toString());
          button.setOnAction(e -> handleClickEvent(new EventWrapper(event, day.getDay())));
          this.addCommitmentToDay(day.getDay(), button);
        } catch (IllegalStateException ignore) {
          // exception ignored
        }
        this.maxCommitmentWarning.setText(this.week.checkIfOverMaxCommitments());
      }
    }
    try {
      this.week.setQuotes(week.getQuotes());
      this.quotes.setText(week.getQuotes());
    } catch (Exception ignored) {
      // exception ignored
    }
  }

  /**
   * Adds a new event to the week
   */
  @FXML
  private void handleAddEvent() {
    eventController.run();
    try {
      EventWrapper newEventWrapper = eventController.getCommitment();
      Event newEvent = newEventWrapper.getCommitment();
      this.week.addEvent(newEventWrapper.getDay(), newEvent);
      Button button = new Button(newEvent.toString());
      button.setOnAction(e -> handleClickEvent(newEventWrapper));

      this.addCommitmentToDay(newEventWrapper.getDay(), button);
    } catch (IllegalStateException e) {
      this.eventController.showInvalidInputWarning();
    }
    this.totalEvents.setText("Total Events: " + this.week.countTotalEvents());
    this.maxCommitmentWarning.setText(this.week.checkIfOverMaxCommitments());
  }

  /**
   * Handles clicking an event: shows a min view of an event
   *
   * @param eventWrapper Wraps an event object
   */
  @FXML
  private void handleClickEvent(EventWrapper eventWrapper) {
    this.miniEventController.setEvent(eventWrapper);
    this.miniEventController.run();
  }

  /**
   * handles setting weekly maximum tasks and events
   */
  @FXML
  private void handleSetMax() {
    maxController.run();
    try {
      int maxTaskNum = maxController.getMaxTasks();
      int maxEventNum = maxController.getMaxEvents();
      this.week.setMaxCommitments(maxTaskNum, maxEventNum);
    } catch (IllegalStateException e) {
      this.eventController.showInvalidInputWarning();
    }
    String commitmentText = this.week.checkIfOverMaxCommitments();
    String color;
    if (commitmentText != MessageConstants.COMMITMENTS_GOOD) {
      this.maxCommitmentWarning.setStyle(ColorConstants.YELLOW.setBackground(false));
    } else {
      this.maxCommitmentWarning.setStyle(ColorConstants.GREEN.setBackground(false));
    }
    this.maxCommitmentWarning.setText(commitmentText);
  }

  /**
   * Handles visually adding a task or event to its correct day
   *
   * @param day        the day of week to add the commitment to
   * @param commitment the event or task to be added
   */
  @FXML
  private void addCommitmentToDay(DayOfWeek day, Button commitment) {
    VBox.setMargin(commitment, new Insets(5, 0, 5, 0));
    String dayString = day.toString();
    switch (dayString) {
      case "SUNDAY" -> addCommitmentToVbox(this.sunday, DayOfWeek.SUNDAY, commitment);
      case "MONDAY" -> addCommitmentToVbox(this.monday, DayOfWeek.MONDAY, commitment);
      case "TUESDAY" -> addCommitmentToVbox(this.tuesday, DayOfWeek.TUESDAY, commitment);
      case "WEDNESDAY" -> addCommitmentToVbox(this.wednesday, DayOfWeek.WEDNESDAY, commitment);
      case "THURSDAY" -> addCommitmentToVbox(this.thursday, DayOfWeek.THURSDAY, commitment);
      case "FRIDAY" -> addCommitmentToVbox(this.friday, DayOfWeek.FRIDAY, commitment);
      case "SATURDAY" -> addCommitmentToVbox(this.saturday, DayOfWeek.SATURDAY, commitment);
      default -> {
      }
    }
  }

  /**
   * Adds the commitment button to its proper vbox
   *
   * @param vbox   the vbox for the correct day of week
   * @param day    the day of week
   * @param button the commitment as a button
   */
  private void addCommitmentToVbox(VBox vbox, DayOfWeek day, Button button) {
    button.setPrefWidth(161);
    button.setPrefHeight(85);
    button.setStyle(day.getColor().setBackground(false));
    vbox.getChildren().add(button);
  }


  /**
   * Handles a user selecting an Add Task menu item: adds a
   * task to a task bar and to the week view
   */
  @FXML
  private void handleAddTask() {
    taskController.run();
    try {
      TaskWrapper newTaskWrapper = taskController.getCommitment();
      Task newTask = newTaskWrapper.getCommitment();
      // add to week:
      this.week.addTask(newTaskWrapper.getDay(), newTask);
      // add to task bar:
      Label taskLabel = new Label(newTask.toTaskBarString());
      this.addTaskToTaskBar(taskLabel);
      // add to main week view:
      Button button = new Button(newTask.toString());
      button.setOnAction(e -> handleClickTask(newTaskWrapper, button, taskLabel));
      this.addCommitmentToDay(newTaskWrapper.getDay(), button);
    } catch (IllegalStateException e) {
      this.eventController.showInvalidInputWarning();
    }
    this.totalTasks.setText("Total Tasks: " + this.week.getQueue().size());
    this.percentTasksComplete.setText(MessageConstants.PERCENT_COMPLETE
        + this.week.countPercentCompleteTasks() + "%");
    this.maxCommitmentWarning.setText(this.week.checkIfOverMaxCommitments());
  }

  /**
   * Adds a task to the task bar on the weekly view
   *
   * @param taskLabel The label that represents the task in the task bar
   */
  @FXML
  private void addTaskToTaskBar(Label taskLabel) {
    taskLabel.setPadding(new Insets(5, 10, 5, 10));
    taskLabel.setPrefWidth(212);
    this.taskBar.getChildren().add(taskLabel);
  }

  /**
   * Handles what happens when a task is clicked: shows a dialog showing a
   * mini view of a task and allowing a user to mark it as complete
   *
   * @param task   The task that is clicked
   * @param button The button that the task is linked to
   * @param label  The label that displays the task in the task bar
   */
  @Override
  public void handleClickTask(TaskWrapper task, Button button, Label label) {
    miniTaskController.setTask(task);
    miniTaskController.run();
    if (miniTaskController.markedComplete()) {
      this.handleMarkTaskComplete(task.getCommitment(), button, label);
    }
  }


  /**
   * Handles marking a task as complete: sets the task to complete
   * and updates the view to show completed tasks
   *
   * @param task   The task to be updated
   * @param button The button representing the task in the week view
   * @param label  The label representing the task in the task bar
   */
  public void handleMarkTaskComplete(Task task, Button button, Label label) {
    task.setComplete(true);
    button.setStyle(ColorConstants.GREEN.setBackground(false));
    button.setText(task.toString());
    label.setText(task.toTaskBarString());
    label.setStyle(ColorConstants.GREEN.setBackground(true));
    this.percentTasksComplete.setText(MessageConstants.PERCENT_COMPLETE
        + this.week.countPercentCompleteTasks() + "%");
  }

  /**
   * Handles saving a bullet journal week as a .bujo file
   */
  @FXML
  private void handleSaveBujo() {
    this.week.setQuotes(this.quotes.getText());
    String fileEnding = ".bujo";
    try {
      FileHandler.writeToFile(this.weekName.getText() + fileEnding, week.toString());
      Alert a = new Alert(Alert.AlertType.INFORMATION);
      a.setTitle("Bullet journal saved");
      a.setContentText("Saved to " + this.week.getName() + fileEnding);
      a.show();
    } catch (IOException e) {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setTitle("Error saving");
      a.setContentText("Error saving " + this.week.getName() + fileEnding);
      a.show();
    }
  }

  /**
   * Handles keybord commands and delegates to the correct handle method
   *
   * @param event the KeyEvent being pressed
   */
  private void handleKeyEvents(KeyEvent event) {
    if (BujoKeyCombo.addEventWin.match(event) || BujoKeyCombo.addEventMac.match(event)) {
      this.handleAddEvent();
    } else if (BujoKeyCombo.addTaskWin.match(event) || BujoKeyCombo.addTaskMac.match(event)) {
      this.handleAddTask();
    } else if (BujoKeyCombo.saveBujoWin.match(event) || BujoKeyCombo.saveBujoMac.match(event)) {
      this.handleSaveBujo();
    } else if (BujoKeyCombo.openBujoWin.match(event) || BujoKeyCombo.openBujoMac.match(event)) {
      this.handleOpen();
    } else if (BujoKeyCombo.newBujoWin.match(event) || BujoKeyCombo.newBujoMac.match(event)) {
      this.handleCreate();
    } else if (BujoKeyCombo.setMaxWin.match(event) || BujoKeyCombo.setMaxMac.match(event)) {
      this.handleSetMax();
    } else if (BujoKeyCombo.weekStartLeft.match(event)) {
      this.handleChangeWeekStartLeft();
    } else if (BujoKeyCombo.weekStartRight.match(event)) {
      this.handleChangeWeekStartRight();
    } else if (BujoKeyCombo.templateWin.match(event) || BujoKeyCombo.templateMac.match(event)) {
      this.handleTemplate();
    }
  }

  /**
   * Moves the day the week starts on to the left
   */
  @FXML
  private void handleChangeWeekStartLeft() {
    List<Node> listOfDays = this.weekDays.getChildren();
    Node moveToBack = listOfDays.get(0);
    listOfDays.remove(0);
    listOfDays.add(moveToBack);
    this.week.shiftLeft();
  }

  /**
   * Moves the day the week starts on to the right
   */
  @FXML
  private void handleChangeWeekStartRight() {
    List<Node> listOfDays = this.weekDays.getChildren();
    Node moveToFront = listOfDays.get(listOfDays.size() - 1);
    listOfDays.remove(listOfDays.size() - 1);
    listOfDays.add(0, moveToFront);
    this.week.shiftRight();
  }
}
