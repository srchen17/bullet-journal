package cs3500.pa05.controller;

/**
 * An interface for a controller of a Dialog
 */
public interface DialogController {
  /**
   * Runs the controller of the dialog
   */
  public void run();

  /**
   * Shows an invalid input warning to a user
   */
  public void showInvalidInputWarning();
}
