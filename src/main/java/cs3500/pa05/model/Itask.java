package cs3500.pa05.model;

/**
 * Interface for tasks
 */
public interface Itask extends Commitment {

  /**
   * Sets whether this task is complete by a boolean
   *
   * @param complete  Whether the task is complete or not
   */
  void setComplete(boolean complete);

  /**
   * Returns a string to represent a task in a task bar
   *
   * @return  A String representing a task in a task bar
   */
  String toTaskBarString();

  /**
   * Gets whether this task is complete
   *
   * @return  True if task is complete
   */
  boolean getComplete();
}