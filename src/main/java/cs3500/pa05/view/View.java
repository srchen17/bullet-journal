package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * A View for a Bullet Journal display of a week
 */
public class View implements BujoView {
  /**
   * Loads a scene from a Bullet Journal GUI layout.
   */
  private final FXMLLoader loader;

  /**
   * Represents creating a simple Welcome Screen Gui
   *
   * @param controller the Controller to run the welcome screen
   * @param fxml the fxml document containing the visual information
   */
  public View(Controller controller, String fxml) {
    // look up and store the layout
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxml));
    this.loader.setController(controller);
  }


  /**
   * Loads a scene from a Bujo GUI layout.
   *
   * @return the layout
   */
  public Scene load() throws IllegalStateException {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
