package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.constants.MessageConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Week in a bullet journal
 */
public class Week {
  private String name;
  private List<Day> days;
  private List<Task> taskQueue;
  private int maxTasks;
  private int maxEvents;
  private String quotes;
  private String password;

  /**
   * Constructs a Week in a bullet journal given a list of days
   *
   * @param days  The days in the week
   */
  public Week(List<Day> days) {
    this.days = days;
    this.taskQueue = new ArrayList<>();
  }

  /**
   * Creates a week and a Json representing a week
   *
   * @param name  Week name
   * @param days  Days in the week
   * @param taskQueue  Tasks in the week
   * @param quotes  Quotes that the user writes for the week
   * @param password the password for the week
   */
  @JsonCreator
  public Week(@JsonProperty("name") String name,
              @JsonProperty("days") List<Day> days,
              @JsonProperty("taskQueue") List<Task> taskQueue,
              @JsonProperty("quotes") String quotes,
              @JsonProperty("password") String password) {
    this.name = name;
    this.days = days;
    this.taskQueue = taskQueue;
    this.quotes = quotes;
    this.password = password;
  }

  /**
   * Gets the quotes written by the user
   *
   * @return The quotes as a String
   */
  public String getQuotes() {
    return this.quotes;
  }

  /**
   * Gets the week name
   *
   * @return  the week name as a String
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the Days in this week
   *
   * @return  a list of days in this week
   */
  public List<Day> getDays() {
    return this.days;
  }

  /**
   * Gets the tasks in this week
   *
   * @return  a list of tasks in this week
   */
  public List<Task> getQueue() {
    return this.taskQueue;
  }

  /**
   * Sets the name of this week
   *
   * @param name Week name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Adds an event to this week, given the day of the event and the event
   *
   * @param day  The day of the event
   * @param event  The event to be added
   */
  public void addEvent(DayOfWeek day, Event event) {
    for (Day d : this.days) {
      if (d.getDay() == (day)) {
        d.addEvent(event);
      }
    }
  }

  /**
   * Sets the max amount of tasks and events for each day
   *
   * @param maxTaskNum  The max number of tasks a user can add without a warning
   * @param maxEventNum  The max number of events a user can add without a warning
   */
  public void setMaxCommitments(int maxTaskNum, int maxEventNum) {
    this.maxEvents = maxEventNum;
    this.maxTasks = maxTaskNum;

    for (Day d : this.days) {
      d.setMaxTasks(maxTaskNum);
      d.setMaxEvents(maxEventNum);
    }
  }

  /**
   * Checks if any days have commitments over the max set by the user. Returns a
   * string representing the status of how many days are over the max.
   *
   * @return  A String representing the commitment warning for the user
   */
  public String checkIfOverMaxCommitments() {
    StringBuilder warning = new StringBuilder();
    warning.append("Warning: ");
    boolean showWarning = false;
    for (Day d : this.days) {
      if (d.checkNumOfEvents()) {
        showWarning = true;
        warning.append(d.getDay().toString()).append(" has over " + maxEvents + " events! ");
      }
      if (d.checkNumOfTasks()) {
        showWarning = true;
        warning.append(d.getDay().toString() + " has over " + maxTasks + " tasks! ");
      }
    }
    if (showWarning) {
      return warning.toString();
    } else {
      return MessageConstants.COMMITMENTS_GOOD;
    }
  }

  /**
   * Adds a task to this week, given the day of the task and the task
   *
   * @param day  The day of the task
   * @param task  The task to be added
   */
  public void addTask(DayOfWeek day, Task task) {
    for (Day d : this.days) {
      if (d.getDay() == (day)) {
        d.addTask(task);
      }
    }
    this.taskQueue.add(task);
  }

  /**
   * Get a string representation of this week
   *
   * @return  A String representing this Week
   */
  @Override
  public String toString() {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode weekNode = mapper.convertValue(
        this, JsonNode.class);
    return weekNode.toString();
  }

  /**
   * Sets the days of this week
   *
   * @param days  The days of this Week
   */
  public void setDays(List<Day> days) {
    this.days = days;
  }

  /**
   * Sets the quotes the user adds for this week
   *
   * @param quotes the quotes the user adds for this week
   */
  public void setQuotes(String quotes) {
    this.quotes = quotes;
  }

  /**
   * Sets the queue of tasks for this week
   *
   * @param queue The tasks the user adds for this week
   */
  public void setQueue(List<Task> queue) {
    this.taskQueue = queue;
  }

  /**
   * Counts all total events in this week
   *
   * @return  An int, representing the number of events in this week
   */
  public int countTotalEvents() {
    int count = 0;
    for (Day d : this.days) {
      count = count + d.getListOfEvents().size();
    }
    return count;
  }

  /**
   * Returns a percentage of the complete tasks in this week
   *
   * @return  A String, representing a percentage of complete tasks this week
   */
  public String countPercentCompleteTasks() {
    double completeTasks = 0;
    for (Task t : this.taskQueue) {
      if (t.getComplete()) {
        completeTasks = completeTasks + 1;
      }
    }
    double completeAsDecimal = (completeTasks / this.taskQueue.size()) * 100;
    DecimalFormat df = new DecimalFormat("###.###");
    return df.format(completeAsDecimal);
  }

  /**
   * Set the password to the input
   *
   * @param password the inputted String password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   *
   * @return the Password field information
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Verify that the week password matches the given password
   *
   * @param password the inputted password
   * @return true if the passwords match
   * @throws IllegalStateException if the passwords don't match
   */
  public boolean verifyPassword(String password) throws IllegalStateException {
    if (this.password.equals(password)) {
      return true;
    } else {
      throw new IllegalStateException("Invalid password");
    }
  }

  /**
   * Shift the order of the days left
   */
  public void shiftLeft() {
    Day moveToBack = this.days.get(0);
    this.days.remove(0);
    this.days.add(moveToBack);
  }

  /**
   * Shift the order of the days right
   */
  public void shiftRight() {
    Day moveToFront = this.days.get(this.days.size() - 1);
    this.days.remove(this.days.size() - 1);
    this.days.add(0, moveToFront);
  }

  /**
   *
   * @return the first day of the week in the list of days
   */
  public DayOfWeek firstDayOfWeek() {
    return this.days.get(0).getDay();
  }
}
