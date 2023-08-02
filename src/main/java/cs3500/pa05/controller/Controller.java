package cs3500.pa05.controller;

import javafx.fxml.FXML;

/**
 * An interface for a Bullet Journal controller
 */
public interface Controller {
  /**
   * Runs the Bullet Journal controller
   *
   * @throws IllegalStateException  if a key press is an illegal state
   */
  @FXML
  void run() throws IllegalStateException;
}
