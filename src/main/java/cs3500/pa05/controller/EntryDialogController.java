package cs3500.pa05.controller;

/**
 * Represents a controller interface for either creating a new or uploading
 * an existing bullet journal
 */
public interface EntryDialogController extends DialogController {
  /**
   *
   * @return the name of the week entered
   */
  String getName();

  /**
   *
   * @return the password for that bullet journal entered
   */
  String getPassword();

  /**
   * Set the swap controller to the input
   *
   * @param swap the SwapController object to set to
   */
  void setSwap(SwapController swap);

}
