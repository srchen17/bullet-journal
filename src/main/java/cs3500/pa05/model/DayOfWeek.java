package cs3500.pa05.model;

import cs3500.pa05.constants.ColorConstants;

/**
 * Represents a day of the week
 */
public enum DayOfWeek {
  /**
   * Represents Sunday
   */
  SUNDAY(ColorConstants.RED),
  /**
   * Represents Monday
   */
  MONDAY(ColorConstants.ORANGE),
  /**
   * Represents Tuesday
   */
  TUESDAY(ColorConstants.YELLOW),
  /**
   * Represents Wednesday
   */
  WEDNESDAY(ColorConstants.RED),
  /**
   * Represents Thursday
   */
  THURSDAY(ColorConstants.ORANGE),
  /**
   * Represents Friday
   */
  FRIDAY(ColorConstants.YELLOW),
  /**
   * Represents Saturday
   */
  SATURDAY(ColorConstants.RED);

  /**
   * Represents the color associated with that week when displaying it
   */
  public final ColorConstants color;

  /**
   * Private constructor for a day of the week
   *
   * @param color  The color of the day of week to be displayed in the Bujo View
   */
  DayOfWeek(ColorConstants color) {
    this.color = color;
  }

  /**
   * Gets the color associated with this day of the week
   *
   * @return  The associated color
   */
  public ColorConstants getColor() {
    return this.color;
  }
}
