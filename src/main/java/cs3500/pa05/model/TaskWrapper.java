package cs3500.pa05.model;

/**
 * A wrapper class for a Task object and the day of week it is on
 */
public class TaskWrapper implements CommitmentWrapper {
  Task task;
  DayOfWeek day;

  /**
   * Constructor
   *
   * @param task the task
   * @param day the day of week
   */
  public TaskWrapper(Task task, DayOfWeek day) {
    this.task = task;
    this.day = day;
  }

  /**
   *
   * @return returns the commitment
   */
  public Task getCommitment() {
    return task;
  }

  /**
   *
   * @return returns the day
   */
  public DayOfWeek getDay() {
    return day;
  }
}
