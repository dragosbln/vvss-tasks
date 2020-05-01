package tasks.model;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@DisplayName("TaskTitleFilterTest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MockitoArrayTaskListTest {

    private static TaskList list;
    private static TaskList list2;

    @BeforeAll
    public static void setUp(){
        list = new ArrayTaskList();
        list2 = new ArrayTaskList();
    }

    @Test
    public void testToString() throws ParseException {
        Task task = Mockito.mock(Task.class);
        Mockito.when(task.toString()).thenReturn("Task");
        list.add(task);
        assert list.toString().equals("ArrayTaskList{tasks=[Task, null, null, null, null, null, null, null, null, null], numberOfTasks=1, currentCapacity=10}");
    }

    @Test
    public void testEquals() {
        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);
        Mockito.doReturn(true).when(task1).equals(task1);
        list.add(task1);
        list2.add(task1);
        assert list.equals(list2);
        list2.remove(task1);
        list2.add(task2);
        assert !list.equals(list2);
    }
}
