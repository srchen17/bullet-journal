package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import java.io.IOException;

/**
 * Represents an interface for uploading a new week from a .bujo file
 */
public interface IuploadController extends EntryDialogController {

  /**
   * Handles opening a file
   */
  void handleOpenFile();

  /**
   *
   * @return returns the week object created from the file
   * @throws IOException an I/O exception
   */
  Week getWeek() throws IOException;
}
