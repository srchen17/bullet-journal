package cs3500.pa05.constants;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * Contains key combinations for different menu options
 */
public final class BujoKeyCombo {
  /**
   * Keyboard combination Ctrl + E for adding a new event on windows
   */
  public static final KeyCombination addEventWin =
      new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
  /**
   * Keyboard combination Cmd + E for adding a new event on mac
   */
  public static final KeyCombination addEventMac =
      new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN);
  /**
   * Keyboard combination Ctrl + T for adding a new task on windows
   */
  public static final KeyCombination addTaskWin =
      new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
  /**
   * Keyboard combination Cmd + T for adding a new task on mac
   */
  public static final KeyCombination addTaskMac =
      new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN);
  /**
   * Keyboard combination Ctrl + S for saving a bullet journal on windows
   */
  public static final KeyCombination saveBujoWin =
      new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
  /**
   * Keyboard combination Cmd + S for saving a bullet journal on mac
   */
  public static final KeyCombination saveBujoMac =
      new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
  /**
   * Keyboard combination Ctrl + O for opening a bullet journal on windows
   */
  public static final KeyCombination openBujoWin =
      new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
  /**
   * Keyboard combination Cmd + S for opening a bullet journal on mac
   */
  public static final KeyCombination openBujoMac =
      new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN);
  /**
   * Keyboard combination Ctrl + N for creating a new bullet journal on windows
   */
  public static final KeyCombination newBujoWin =
      new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
  /**
   * Keyboard combination Cmd + N for creating a new bullet journal on mac
   */
  public static final KeyCombination newBujoMac =
      new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN);
  /**
   * Keyboard combination Ctrl + M for setting task and event maximums on windows
   */
  public static final KeyCombination setMaxWin =
      new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
  /**
   * Keyboard combination Cmd + M for setting task and event maximums on mac
   */
  public static final KeyCombination setMaxMac =
      new KeyCodeCombination(KeyCode.M, KeyCombination.SHORTCUT_DOWN);
  /**
   * Keyboard combination left arrow key for shifting the week view one day left
   */
  public static KeyCombination weekStartLeft =
      new KeyCodeCombination(KeyCode.LEFT);
  /**
   * Keyboard combination right arrow key for shifting the week view one day right
   */
  public static KeyCombination weekStartRight =
      new KeyCodeCombination(KeyCode.RIGHT);
  /**
   * Keyboard combination ctrl + shift + t for opening a new bullet journal from template
   */
  public static KeyCombination templateWin =
      new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
  /**
   * Keyboard combination cmd + shift + t for opening a new bullet journal from template
   */
  public static KeyCombination templateMac =
      new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN);
}
