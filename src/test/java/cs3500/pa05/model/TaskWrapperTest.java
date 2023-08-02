package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskWrapperTest {
  TaskWrapper taskWrapper;
  Task task;

  @BeforeEach
  void initialize() {
    this.task = new Task("sample event", "sample description");
    this.taskWrapper = new TaskWrapper(this.task, DayOfWeek.THURSDAY);
  }

  @Test
  void testGetTest() {
    assertEquals(this.task, this.taskWrapper.getCommitment());
  }

  @Test
  void testGetDay() {
    assertEquals(DayOfWeek.THURSDAY, this.taskWrapper.getDay());
  }

}