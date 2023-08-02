package cs3500.pa05.controller;

/**
 * Interface for inputting the maximum commitments per day of a week
 */
public interface ImaxCommitmentController extends DialogController {
  /**
   *
   * @return the maximum number of tasks allowed a day
   */
  int getMaxTasks();

  /**
   *
   * @return the maximum number of events allowed a day
   */
  int getMaxEvents();
}
