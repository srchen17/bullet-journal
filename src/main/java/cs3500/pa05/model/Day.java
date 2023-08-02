package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day of a week
 */
public class Day {
  private DayOfWeek day;
  private List<Event> listOfEvents = new ArrayList<>();
  private List<Task> listOfTasks = new ArrayList<>();
  private int maxEvents;
  private int maxTasks;

  /**
   * Creates a day of a week
   *
   * @param day  An Enum representing a Day of a Week
   */
  public Day(DayOfWeek day) {
    this.day = day;
  }

  /**
   * Creates a Day and a Json representing a Day
   *
   * @param day  The day of the week
   * @param listOfEvents  The list of events on the day
   * @param listOfTasks  The list of task son the day
   * @param maxEvents  The max number of events allowed
   * @param maxTasks  The max number of tasks allowed
   */
  @JsonCreator
  public Day(@JsonProperty("day") DayOfWeek day,
              @JsonProperty("events") List<Event> listOfEvents,
              @JsonProperty("tasks") List<Task> listOfTasks,
              @JsonProperty("max-events") int maxEvents,
              @JsonProperty("max-tasks") int maxTasks) {
    this.day = day;
    this.listOfEvents = listOfEvents;
    this.listOfTasks = listOfTasks;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
  }

  public String toString() {
    return null;
  }

  /**
   * Returns the list of events on this day
   *
   * @return  A list of events
   */
  public List<Event> getListOfEvents() {
    return this.listOfEvents;
  }

  /**
   * Returns a list of tasks on this day
   *
   * @return  A list of tasks
   */
  public List<Task> getListOfTasks() {
    return this.listOfTasks;
  }

  /**
   * Returns the max number of events allowed on this day
   *
   * @return  The max number events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Returns the max number of tasks allowed on this day
   *
   * @return  The max number tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Adds an event to the list of events on this day
   *
   * @param event  The event to be added
   */
  public void addEvent(Event event) {
    listOfEvents.add(event);
  }

  /**
   * Adds a task to the list of tasks on this day
   *
   * @param task  The task to be added
   */
  public void addTask(Task task) {
    listOfTasks.add(task);
  }

  /**
   * Checks whether the amount of events is over the max amount events allowed
   *
   * @return  True if amount of events is greater than max
   */
  public boolean checkNumOfEvents() {
    return (listOfEvents.size() > maxEvents);
  }

  /**
   * Checks whether the amount of tasks is over the max amount tasks allowed
   *
   * @return  True if amount of tasks is greater than max
   */
  public boolean checkNumOfTasks() {
    return (listOfTasks.size() > maxTasks);
  }

  /**
   * Sets the max number of events allowed on this day
   *
   * @param max  The max number allowed
   */
  public void setMaxEvents(int max) {
    this.maxEvents = max;
  }

  /**
   * Sets the max number of tasks allowed on this day
   *
   * @param max  The max number allowed
   */
  public void setMaxTasks(int max) {
    this.maxTasks = max;
  }

  /**
   * Gets the Enum of the day of the week of this Day
   *
   * @return  A DayOfWeek Enum
   */
  public DayOfWeek getDay() {
    return this.day;
  }

  /**
   * Sets the DayOfWeek of this Day
   *
   * @param day  The DayOfWeek for this day
   */
  public void setDay(DayOfWeek day) {
    this.day = day;
  }

  /**
   * Sets the list of events occurring on this day
   *
   * @param listOfEvents  The list of events
   */
  public void setListOfEvents(List<Event> listOfEvents) {
    this.listOfEvents = listOfEvents;
  }

  /**
   * Sets the list of tasks occurring on this day
   *
   * @param listOfTasks  The list of tasks
   */
  public void setListOfTasks(List<Task> listOfTasks) {
    this.listOfTasks = listOfTasks;
  }
}
