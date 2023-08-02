package cs3500.pa05.controller;

import cs3500.pa05.view.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controls scene swapping
 */
public class SwapController implements Controller {
  private final BujoController controller;
  private final View view;
  private final Stage stage;

  /**
   * Constructor
   *
   * @param controller the Bullet Journal controller
   * @param view the view of the bullet journal main page
   * @param stage the main stage to display on
   */
  public SwapController(BujoController controller, View view, Stage stage) {
    this.controller = controller;
    this.view = view;
    this.stage = stage;
  }

  /**
   * Runs the controller and delegates events to their specific handlers
   */
  @Override
  public void run() {
    try {
      // load and place the view's scene onto the stage
      Scene mainScene = view.load();
      controller.setMainScene(mainScene);
      stage.setScene(mainScene);
      controller.run();
      stage.setTitle("Bullet Journal");
      // render the stage
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }
}
