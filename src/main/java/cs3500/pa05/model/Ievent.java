package cs3500.pa05.model;

/**
 * Interface for events
 */
public interface Ievent extends Commitment {

  /**
   * Gets the start time of this event
   *
   * @return  The event start time
   */
  Time getTime();

  /**
   * Gets the duration time of this event
   *
   * @return  The event duration time
   */
  Time getDuration();

  /**
   * Sets the start time of this event
   *
   * @param time  The start time
   */
  void setTime(Time time);

  /**
   * Sets the duration time of this event
   *
   * @param duration  The duration time
   */
  void setDuration(Time duration);
}