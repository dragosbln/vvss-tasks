package tasks.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CompleteIntegratonTest {
    private static ArrayTaskList list;
    private static TasksService service;

    @BeforeAll
    public static void setUp(){
        list = new ArrayTaskList();
        service = new TasksService(list);
    }

    @Test
    public void testFilterTitle() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("07-07-2020 08:00:00");
        Task task = new Task("Task", date);
        list.add(task);
        Task filtered = service.filterTasks("Task");
        assertNotNull(filtered);
    }

    @Test
    public void testFilterDate() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("07-07-2020 08:00:00");
        Task task = new Task("Task", date);
        list.add(task);
        Iterable filtered = service.filterTasks(date,date);
        assertNotNull(filtered);
    }
}
