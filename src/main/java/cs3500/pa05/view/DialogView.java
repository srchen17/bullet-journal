package cs3500.pa05.view;

import cs3500.pa05.controller.DialogController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DialogPane;

/**
 * Represents a Dialog that can be shown to users
 */
public class DialogView {
  /**
   * Loads a scene from a Bullet Journal GUI layout.
   *
   */
  private final FXMLLoader loader;

  /**
   * Represents creating a simple Welcome Screen Gui
   *
   * @param controller the DialogController object to display on
   * @param fxml the fxml document containing the display details
   */
  public DialogView(DialogController controller, String fxml) {
    // look up and store the layout
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxml));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a Bujo GUI layout.
   *
   * @return the layout
   * @throws IllegalStateException if the dialog cannot be loaded
   */
  public DialogPane load() throws IllegalStateException {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
