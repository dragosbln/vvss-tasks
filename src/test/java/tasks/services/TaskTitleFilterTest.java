package tasks.services;

import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskTitleFilterTest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskTitleFilterTest {

    private static String title;
    private static ArrayTaskList savedTasksList = new ArrayTaskList();
    private TasksService service = new TasksService(savedTasksList);

    @BeforeAll
    static void initData() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("07-07-2020 08:00:00");
        savedTasksList.add(new Task("Task1", date));
        savedTasksList.add(new Task("Task2", date));
        savedTasksList.add(new Task("Task3", date));
        savedTasksList.add(new Task("Task4", date));
        savedTasksList.add(new Task("Task5", date));
    }

    @Test
    @Order(1)
    void testEmptyTaskTitle() {
        title = "";
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.filterTasks(title),
                "Expected validate() to throw IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Title must not be empty"));
    }

    @Test
    @Order(2)
    void testLengthExceededTaskTitle() {
        title = "Task1Task1Task1";
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.filterTasks(title),
                "Expected validate() to throw IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Length exceeded"));
    }

    @Test
    @Order(3)
    void test1Iteration() {
        title="Task1";
        Task filteredTask = service.filterTasks(title);
        assertNotNull(filteredTask);
    }

    @Test
    @Order(4)
    void test2Iterations() {
        title="Task2";
        Task filteredTask = service.filterTasks(title);
        assertNotNull(filteredTask);
    }

    @Test
    @Order(5)
    void testMIterations() {
        title="Task3";
        Task filteredTask = service.filterTasks(title);
        assertNotNull(filteredTask);
    }

    @Test
    @Order(6)
    void testNminus1Iterations() {
        title="Task4";
        Task filteredTask = service.filterTasks(title);
        assertNotNull(filteredTask);
    }

    @Test
    @Order(7)
    void testNIterations() {
        title="Task5";
        Task filteredTask = service.filterTasks(title);
        assertNotNull(filteredTask);
    }

    @Test
    @Order(8)
    void testNPlus1Iterations() {
        title="Task6";
        Task filteredTask = service.filterTasks(title);
        assertNull(filteredTask);
    }

    @Test
    @Order(9)
    void test0Iterations() throws ParseException {
        title="Task1";
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("07-07-2020 08:00:00");
        savedTasksList.remove(new Task("Task1", date));
        savedTasksList.remove(new Task("Task2", date));
        savedTasksList.remove(new Task("Task3", date));
        savedTasksList.remove(new Task("Task4", date));
        savedTasksList.remove(new Task("Task5", date));

        assertEquals(savedTasksList.size(), 0);
        Task filteredTask = service.filterTasks(title);
        assertNull(filteredTask);
    }

}
