package cs3500.pa05.constants;

/**
 * Contains the HEX values for the color palette of the bullet journal
 */
public enum ColorConstants {
  /**
   * Commitment red
   */
  RED("#ffafa3"),
  /**
   * Commitment orange
   */
  ORANGE("#ffc470"),
  /**
   * Commitment yellow
   */
  YELLOW("#ffd966"),
  /**
   * Completed green
   */
  GREEN("#bff4c6"),
  /**
   * Standard black
   */
  BLACK("#000000");

  /**
   * The hex value of the color
   */
  public final String hex;

  /**
   * Constructor
   *
   * @param hex the hex value of the color as a String
   */
  ColorConstants(String hex) {
    this.hex = hex;
  }

  /**
   *
   * @return the Hex value of a given color constant
   */
  public String getHex() {
    return this.hex;
  }

  /**
   *
   * @param lighter boolean to set the box to the lighter version of that color or not
   * @return a String with the style command to set a background to that color
   */
  public String setBackground(boolean lighter) {
    if (lighter) {
      return "-fx-background-color: " + this.getLighter();
    } else {
      return "-fx-background-color: " + this.getHex();
    }
  }

  /**
   *
   * @return the hex value of the lighter version of the color
   */
  public String getLighter() {
    return switch (this) {
      case RED -> "#fde4de";
      case ORANGE -> "#fff2df";
      case YELLOW -> "#fff7df";
      case GREEN -> "#d1fdc7";
      default -> "#ffffff";
    };
  }
}
