package cs3500.pa05.model;

/**
 * Wraps a commitment and the Day Of Week it is on
 */
public interface CommitmentWrapper {
  /**
   *
   * @return the Commitment object
   */
  Commitment getCommitment();

  /**
   *
   * @return the DayOfWeek object
   */
  DayOfWeek getDay();
}
