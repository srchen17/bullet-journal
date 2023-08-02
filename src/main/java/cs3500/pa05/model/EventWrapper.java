package cs3500.pa05.model;

/**
 * Represents a wrapper class for an Event and the Day of Week it is on
 */
public class EventWrapper implements CommitmentWrapper {
  private final Event event;
  private final DayOfWeek day;

  /**
   * Constructor
   *
   * @param event the event
   * @param day the day
   */
  public EventWrapper(Event event, DayOfWeek day) {
    this.event = event;
    this.day = day;
  }

  /**
   *
   * @return returns the event
   */
  public Event getCommitment() {
    return event;
  }

  /**
   *
   * @return returns the day
   */
  public DayOfWeek getDay() {
    return day;
  }
}
