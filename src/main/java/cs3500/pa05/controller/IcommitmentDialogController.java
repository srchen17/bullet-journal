package cs3500.pa05.controller;

import cs3500.pa05.model.CommitmentWrapper;

/**
 * Represents an interface for a controller of adding a new commitment
 */
public interface IcommitmentDialogController extends DialogController {
  /**
   *
   * @return a commitmentWrapper containing the Commitment object and DayOfWeek
   */
  CommitmentWrapper getCommitment();
}
