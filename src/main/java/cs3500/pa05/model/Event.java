package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an Event in a bullet journal  week
 */
public class Event implements Ievent {
  private String name;
  private String description;
  private Time time;
  private Time duration;

  /**
   * Creates an event and a json representing an event
   *
   * @param name  The name of the event
   * @param description  The event description
   * @param time  The start time
   * @param duration  The event duration
   */
  @JsonCreator
  public Event(
      @JsonProperty("name") String name,
      @JsonProperty("description") String description,
      @JsonProperty("time") Time time,
      @JsonProperty("duration") Time duration) {
    this.name = name;
    this.description = description;
    this.time = time;
    this.duration = duration;
  }

  /**
   * Gets the name of this event
   *
   * @return  The event name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of this event
   *
   * @return  The event description
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the start time of this event
   *
   * @return  The event start time
   */
  @Override
  public Time getTime() {
    return this.time;
  }

  /**
   * Gets the duration time of this event
   *
   * @return  The event duration time
   */
  @Override
  public Time getDuration() {
    return this.duration;
  }

  /**
   * Sets the name of this event
   *
   * @param name  The commitment name
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the description of this event
   *
   * @param description  The commitment name
   */
  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the start time of this event
   *
   * @param time  The start time
   */
  @Override
  public void setTime(Time time) {
    this.time = time;
  }

  /**
   * Sets the duration time of this event
   *
   * @param duration  The duration time
   */
  @Override
  public void setDuration(Time duration) {
    this.duration = duration;
  }

  /**
   * Returns this event represented by a String
   *
   * @return  a String representing the event
   */
  @Override
  public String toString() {
    return this.name
        + "\n"
        + this.description
        + "\n Start Time: "
        + this.time.toString()
        + "\n Duration: "
        + this.duration.toString();
  }
}
