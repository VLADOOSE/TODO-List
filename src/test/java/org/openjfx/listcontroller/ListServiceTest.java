package org.openjfx.listcontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjfx.enums.TaskStatus;
import org.openjfx.exceptions.InvalidDataTime;
import org.openjfx.exceptions.NotFoundException;
import org.openjfx.listrepository.ListRepository;
import org.openjfx.listservice.ListService;
import org.openjfx.model.Task;
import org.openjfx.listservice.ListService.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class ListServiceTest {
    ListRepository listRepository = new ListRepository();
    ListService listService = new ListService(listRepository);
    @Test
    void addTask1() throws InvalidDataTime {
        Task task = listService.addTask("New1", "Desc1", LocalDate.parse("2025-09-25"));
        Assertions.assertNotNull(task.getId());
        Assertions.assertEquals("New1", task.getTitle());
        Assertions.assertEquals("Desc1", task.getDescription());
        Assertions.assertEquals(LocalDate.parse("2025-09-25"), task.getDueDate());
        List<Task> tasks = listService.listTasks();
        Assertions.assertEquals(1, tasks.size());
    }
    @Test
    void editTask() throws InvalidDataTime, NotFoundException {
        Task task = listService.addTask("oldT", "oldD", LocalDate.parse("2025-09-25"));
        listService.editTask(task.getId(), "newT", "newD", LocalDate.parse("2025-09-26"));
        Task upd = listRepository.findById(task.getId());
        Assertions.assertEquals("newT", upd.getTitle());
        Assertions.assertEquals("newD", upd.getDescription());
        Assertions.assertEquals(LocalDate.parse("2025-09-26"), upd.getDueDate());

    }
    @Test
    void deleteTask() throws NotFoundException, InvalidDataTime {
        Task task = listService.addTask("ToDelete", "desc", LocalDate.parse("2025-09-20"));
        listService.deleteTask(task.getId());
        Assertions.assertTrue(listService.listTasks().isEmpty());
    }
    @Test
    void filterTask() throws InvalidDataTime, NotFoundException {
        Task task1 = listService.addTask("T1", "D1", LocalDate.parse("2025-09-25"));
        Task task2 = listService.addTask("T2", "D2", LocalDate.parse("2025-09-26"));
        listService.updateStatus(task1.getId(), TaskStatus.DONE);
        listService.updateStatus(task2.getId(), TaskStatus.TODO);
        List<Task> doneTasks = listService.filterByStatus(TaskStatus.DONE);
        Assertions.assertEquals(1, doneTasks.size());
        Assertions.assertEquals("T1", doneTasks.get(0).getTitle());
    }
    @Test
    void sortByDueDate() throws InvalidDataTime {
        listService.addTask("T1", "desc", LocalDate.parse("2025-09-24"));
        listService.addTask("T2", "desc", LocalDate.parse("2025-09-23"));
        List<Task> sorted = listService.sortByDueDate();
        Assertions.assertEquals("T2", sorted.get(0).getTitle());
        Assertions.assertEquals("T1", sorted.get(1).getTitle());
    }
}
