package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents a GUI view for a Bullet Journal
 */
public interface BujoView {
  /**
   * Loads a scene from a Bujo GUI layout
   *
   * @return  the Scene to be loaded
   * @throws IllegalStateException if it cannot be loaded
   */
  Scene load() throws IllegalStateException;

}