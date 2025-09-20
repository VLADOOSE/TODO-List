package org.openjfx.listrepository;


import org.openjfx.model.Task;

import java.util.*;

public class ListRepository {
    private final Map<UUID, Task> list = new HashMap<>();

    public Task createTask(Task task) {
        list.put(task.getId(), task);
        return task;
    }

    public Optional<Task> findById(UUID id) {
        return Optional.ofNullable(list.get(id));
    }

    public Map<UUID, Task> findAll() {
        return Map.copyOf(list);
    }

    public Task update(UUID id, Task updatedTask) {
        list.put(id, updatedTask);
        return updatedTask;
    }

    public boolean delete(UUID id) {
        return list.remove(id) != null;
    }
}
