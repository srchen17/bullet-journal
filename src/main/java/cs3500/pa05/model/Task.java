package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.charset.StandardCharsets;

/**
 * Represents a Task in a bullet journal week
 */
public class Task implements Itask {
  private String name;
  private String description;
  private boolean complete;

  /**
   * Creates a Task, given a name and description
   *
   * @param name  Task name
   * @param description  Task description
   */
  public Task(String name, String description) {
    this.name = name;
    this.description = description;
    this.complete = false;
  }

  /**
   * Creates a Task object and a Json
   *
   * @param name Task name
   * @param description Task description
   * @param complete  Whether this task is complete
   */
  @JsonCreator
  public Task(@JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("complete") boolean complete) {
    this.name = name;
    this.description = description;
    this.complete = complete;
  }

  /**
   * Gets whether this task is complete
   *
   * @return  True if task is complete
   */
  @Override
  public boolean getComplete() {
    return this.complete;
  }

  /**
   * Gets this task's name
   *
   * @return  Task name as a String
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets this task's description
   *
   * @return  Task name as a String
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets whether this task is complete by a boolean
   *
   * @param complete  Whether the task is complete or not
   */
  @Override
  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  /**
   * Sets a task name for this Task
   *
   * @param name  The task name
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets a task description for this Task
   *
   * @param description  The task description
   */
  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns this task represented as a String
   *
   * @return  A String representing this task
   */
  @Override
  public String toString() {
    String completeString = "(done!)";
    if (!complete) {
      completeString = "(not done)";
    }

    return this.name
        + " "
        + completeString
        + "\n"
        + this.description;
  }

  /**
   * Returns a string to represent a task in a task bar
   *
   * @return  A String representing a task in a task bar
   */
  @Override
  public String toTaskBarString() {
    byte[] emojiByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85};
    String checkEmoji = new String(emojiByteCode, StandardCharsets.UTF_8);
    StringBuilder taskContent = new StringBuilder();
    taskContent.append("- ").append(this.getName()).append(" ");
    if (this.getComplete()) {
      taskContent.append(checkEmoji);
    }
    return taskContent.toString();
  }
}
