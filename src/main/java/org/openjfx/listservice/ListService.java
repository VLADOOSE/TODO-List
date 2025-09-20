package org.openjfx.listservice;

import org.openjfx.enums.TaskStatus;
import org.openjfx.exceptions.NotFoundException;
import org.openjfx.exceptions.InvalidDataTime;
import org.openjfx.listrepository.ListRepository;
import org.openjfx.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ListService {
    private final ListRepository listRepository;

    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public Task addTask(String title, String description, LocalDate dueDate) throws InvalidDataTime {
        if (!title.isEmpty() && dueDate.isAfter(LocalDate.now())) {
            Task task = new Task(title, description, dueDate);
            listRepository.createTask(task);
            return task;
        } else {
            throw new InvalidDataTime("Дата должна быть позже текущей");
        }
    }

    public List<Task> listTasks() {
        return new ArrayList<>(listRepository.findAll().values());
    }

    public Task listTask(UUID id) throws NotFoundException {
        return listRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Таска не найдена"));
    }

    public Task editTask(UUID id, String newTitle, String newDescription, LocalDate newDueDate)
            throws NotFoundException, InvalidDataTime {
        if (!newTitle.isEmpty() && newDueDate.isAfter(LocalDate.now())) {
            Task updatedTask = listRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Таска не найдена"));

            updatedTask.setTitle(newTitle);
            updatedTask.setDescription(newDescription);
            updatedTask.setDueDate(newDueDate);

            return listRepository.update(id, updatedTask);
        } else {
            throw new InvalidDataTime("Дата должна быть позже текущей");
        }
    }

    public Task updateStatus(UUID id, TaskStatus newStatus) throws NotFoundException {
        Task updatedTask = listRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Таска не найдена"));

        updatedTask.setTaskStatus(newStatus);

        return listRepository.update(id, updatedTask);
    }

    public void deleteTask(UUID id) throws NotFoundException {
        boolean deleted = listRepository.delete(id);
        if (!deleted) {
            throw new NotFoundException("Таска не найдена");
        }
    }

    public List<Task> filterByStatus(TaskStatus taskStatus) {
        return listRepository.findAll().values().stream()
                .filter(task -> task.getTaskStatus() == taskStatus)
                .toList();
    }

    public List<Task> sortByDueDate() {
        return listRepository.findAll().values().stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
    }

    public List<Task> sortByStatus() {
        return listRepository.findAll().values().stream()
                .sorted(Comparator.comparing(Task::getTaskStatus))
                .toList();
    }
}
