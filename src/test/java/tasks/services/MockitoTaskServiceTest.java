package tasks.services;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MockitoTaskServiceTest")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MockitoTaskServiceTest {
    private ArrayTaskList list;

    private TasksService service;

    @BeforeAll
    public void init(){
        list = Mockito.mock(ArrayTaskList.class);
        service = new TasksService(list);
    }

    @Test
    public void testFilterName() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("07-07-2020 08:00:00");
        Task task1 = new Task("task1", date);
        Task task2 = new Task("task2", date);

        Mockito.when(list.getAll()).thenReturn(Arrays.asList(task1, task2));

        Task filtered = service.filterTasks("task1");

        assertNotNull(filtered);
    }

    @Test
    public void testFilterDate() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("07-07-2020 08:00:00");
        Task task1 = new Task("task1", date);
        Task task2 = new Task("task2", date);

        Mockito.when(list.getAll()).thenReturn(Arrays.asList(task1, task2));

        Iterable<Task> filtered = service.filterTasks(date, date);

        assertNotNull(filtered);
    }
}
