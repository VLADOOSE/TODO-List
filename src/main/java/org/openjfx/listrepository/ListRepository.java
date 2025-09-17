package org.openjfx.listrepository;

import org.openjfx.exceptions.NotFoundException;
import org.openjfx.model.Task;

import java.util.*;

public class ListRepository {
    Map<UUID, Task> list = new HashMap<UUID, Task>();
    public Task createTask(Task task){
        list.put(task.getId(), task);
        return list.get(task.getId());
    }
    public Task findById(UUID id) throws NotFoundException {
        if(list.get(id) != null){
            return list.get(id);
        }else{
            throw new NotFoundException("Таска не найдена");
        }
    }
    public Map<UUID, Task> findAll(){
        return Map.copyOf(list);
    }
    public Task update(UUID id, Task updatedTask) throws NotFoundException {
        if(list.get(id) != null){
            list.put(id, updatedTask);
            return updatedTask;
        }else{
            throw new NotFoundException("Таска не найдена");
        }
    }
    public boolean delete(UUID id) throws NotFoundException {
        if(list.get(id) != null){
            list.remove(id);
            return true;
        }else if(list.get(id) == null){
            throw new NotFoundException("Таска не найдена");
        }
        return false;
    }


}
