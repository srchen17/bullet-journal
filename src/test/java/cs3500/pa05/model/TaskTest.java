package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

  Task task;

  @BeforeEach
  void initialize() {
    this.task = new Task("sample task", "description");
  }

  @Test
  void getName() {
    assertEquals("sample task", this.task.getName());
    this.task.setName("new sample task");
    assertEquals("new sample task", this.task.getName());
  }

  @Test
  void getDescription() {
    assertEquals("description", this.task.getDescription());
    this.task.setDescription("new description");
    assertEquals("new description", this.task.getDescription());
  }

  @Test
  void getComplete() {
    assertFalse(this.task.getComplete());
    this.task.setComplete(true);
    assertTrue(this.task.getComplete());
  }

  @Test
  void testToString() {
    String taskNotDone =  "sample task"
        + " "
        + "(not done)"
        + "\n"
        + "description";
    String taskDone =  "sample task"
        + " "
        + "(done!)"
        + "\n"
        + "description";

    assertEquals(taskNotDone, this.task.toString());
    this.task.setComplete(true);
    assertEquals(taskDone, this.task.toString());
  }

  @Test
  void testTaskBarToString() {
    byte[] emojiByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85};
    String checkEmoji = new String(emojiByteCode, StandardCharsets.UTF_8);
    String taskNotDone =  "- sample task ";
    String taskDone =  "- sample task " + checkEmoji;
    assertEquals(taskNotDone, this.task.toTaskBarString());
    this.task.setComplete(true);
    assertEquals(taskDone, this.task.toTaskBarString());
  }

}