package cs3500.pa05.constants;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ColorConstantsTest {

  @Test
  void testGetLighter() {
    assertEquals("#fde4de", ColorConstants.RED.getLighter());
    assertEquals("#fff2df", ColorConstants.ORANGE.getLighter());
    assertEquals("#fff7df", ColorConstants.YELLOW.getLighter());
    assertEquals("#d1fdc7", ColorConstants.GREEN.getLighter());
    assertEquals("#ffffff", ColorConstants.BLACK.getLighter());
  }

  @Test
  void testGetHex() {
    assertEquals("#000000", ColorConstants.BLACK.getHex());
  }

  @Test
  void testSetBackground() {
    assertEquals("-fx-background-color: #fde4de", ColorConstants.RED.setBackground(true));
    assertEquals("-fx-background-color: #ffafa3", ColorConstants.RED.setBackground(false));
  }

}