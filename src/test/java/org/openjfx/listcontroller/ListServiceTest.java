package org.openjfx.listcontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjfx.enums.TaskStatus;
import org.openjfx.exceptions.InvalidDataTime;
import org.openjfx.exceptions.NotFoundException;
import org.openjfx.listrepository.ListRepository;
import org.openjfx.listservice.ListService;
import org.openjfx.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ListServiceTest {

    ListRepository listRepository = new ListRepository();
    ListService listService = new ListService(listRepository);

    @Test
    void addTask_success() throws InvalidDataTime {
        Task task = listService.addTask("New1", "Desc1", LocalDate.parse("2025-09-25"));
        Assertions.assertNotNull(task.getId());
        Assertions.assertEquals("New1", task.getTitle());
        Assertions.assertEquals("Desc1", task.getDescription());
        Assertions.assertEquals(LocalDate.parse("2025-09-25"), task.getDueDate());
        Assertions.assertEquals(1, listService.listTasks().size());
    }

    @Test
    void addTask_invalidDate() {
        Assertions.assertThrows(InvalidDataTime.class, () ->
                listService.addTask("Bad", "desc", LocalDate.now().minusDays(1))
        );
    }

    @Test
    void editTask_success() throws InvalidDataTime, NotFoundException {
        Task task = listService.addTask("oldT", "oldD", LocalDate.parse("2025-09-25"));
        listService.editTask(task.getId(), "newT", "newD", LocalDate.parse("2025-09-26"));
        Optional<Task> upd = listRepository.findById(task.getId());

        Assertions.assertTrue(upd.isPresent());
        Assertions.assertEquals("newT", upd.get().getTitle());
        Assertions.assertEquals("newD", upd.get().getDescription());
        Assertions.assertEquals(LocalDate.parse("2025-09-26"), upd.get().getDueDate());
    }

    @Test
    void deleteTask_success() throws NotFoundException, InvalidDataTime {
        Task task = listService.addTask("ToDelete", "desc", LocalDate.parse("2025-09-30"));
        listService.deleteTask(task.getId());
        Assertions.assertTrue(listService.listTasks().isEmpty());
    }

    @Test
    void deleteTask_notFound() {
        Assertions.assertThrows(NotFoundException.class, () ->
                listService.deleteTask(java.util.UUID.randomUUID())
        );
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

    @Test
    void sortByStatus() throws InvalidDataTime, NotFoundException {
        Task t1 = listService.addTask("T1", "desc", LocalDate.parse("2025-09-25"));
        Task t2 = listService.addTask("T2", "desc", LocalDate.parse("2025-09-26"));

        listService.updateStatus(t1.getId(), TaskStatus.DONE);
        listService.updateStatus(t2.getId(), TaskStatus.IN_PROGRESS);

        List<Task> sorted = listService.sortByStatus();

        Assertions.assertEquals(TaskStatus.IN_PROGRESS, sorted.get(0).getTaskStatus());
        Assertions.assertEquals(TaskStatus.DONE, sorted.get(1).getTaskStatus());
    }
}
