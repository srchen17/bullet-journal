package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Time (in military time) in a bullet journal
 */
public class Time {
  private int hour;
  private int minute;

  /**
   * Constructs a time and a Json representing a time
   *
   * @param hour The hour of the time (military time)
   * @param minute  The minute of the time
   */
  @JsonCreator
  public Time(@JsonProperty("hour") int hour,
              @JsonProperty("minute") int minute) {
    this.hour = hour;
    this.minute = minute;
    if (this.hour < 0 || this.hour > 12 || this.minute < 0 || this.minute > 59) {
      throw new IllegalArgumentException("Invalid time values");
    }
  }

  /**
   * Gets the hour of this time
   *
   * @return  An int representing the hour
   */
  public int getHour() {
    return this.hour;
  }

  /**
   * Gets the minutes of this time
   *
   * @return  An int representing the minutes
   */
  public int getMinute() {
    return this.minute;
  }

  /**
   * Sets the hours of this time
   *
   * @param hour  The hour of this time
   */
  public void setHour(int hour) {
    this.hour = hour;
  }

  /**
   * Sets the minutes of this time
   *
   * @param minute  The minutes of this time
   */
  public void setMinute(int minute) {
    this.minute = minute;
  }

  /**
   * Gets this time represented as a String
   *
   * @return  a string representing this time
   */
  public String toString() {
    if (minute < 10) {
      return hour + ":0" + minute;
    } else {
      return hour + ":" + minute;
    }
  }
}
