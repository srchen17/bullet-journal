package cs3500.pa05.model;

/**
 * Represents a commitment on a Bullet Journal Week
 */
public interface Commitment {
  /**
   * Generates a String representing the commitment
   *
   * @return the Commitment details as a String
   */
  String toString();

  /**
   * Sets the name of this commitment
   *
   * @param name  The commitment name
   */
  void setName(String name);

  /**
   * Gets the name of this commitment
   *
   * @return  The commitment name
   */
  String getName();

  /**
   * Sets the description of this commitment
   *
   * @param description  The commitment description
   */
  void setDescription(String description);

  /**
   * Gets the description of this commitment
   *
   * @return The commitment description
   */
  String getDescription();
}
