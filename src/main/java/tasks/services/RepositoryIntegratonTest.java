package tasks.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TaskStub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class RepositoryIntegratonTest {
    private static ArrayTaskList list;
    private static TasksService service;

    @BeforeAll
    public static void setUp(){
        list = new ArrayTaskList();
        service = new TasksService(list);
    }

    @Test
    public void testFilterTitle(){
        TaskStub task = new TaskStub();
        list.add(task);
        Task filtered = service.filterTasks("Task");
        assertNotNull(filtered);
    }

    @Test
    public void testFilterDate() throws ParseException {
        TaskStub task = new TaskStub();
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("07-07-2020 08:00:00");
        list.add(task);
        Iterable filtered = service.filterTasks(date,date);
        assertNotNull(filtered);
    }
}
