package org.openjfx.listservice;

import org.openjfx.enums.TaskStatus;
import org.openjfx.exceptions.NotFoundException;
import org.openjfx.exceptions.NotValidData;
import org.openjfx.listrepository.ListRepository;
import org.openjfx.model.Task;

import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ListService {
    private ListRepository listRepository;
    public ListService(ListRepository listRepository){
        this.listRepository = listRepository;
    }
    public Task addTask(String title, String description, LocalDate dueDate) throws NotValidData {
        if(!title.isEmpty() && dueDate.isAfter(LocalDate.now())){
            Task task = new Task(title, description, dueDate);
            listRepository.createTask(task);
            return task;
        }else{
            throw new NotValidData("Не правильно переданы данные");
        }
    }
    public List<Task> listTasks(){
        return new ArrayList<>(listRepository.findAll().values());
    }
    public Task listTask(UUID id) throws NotFoundException {
        return listRepository.findById(id);
    }
    public Task editTask(UUID id, String newTitle, String newDescription, LocalDate newDueDate)
            throws NotFoundException {
        Task updatedTask = listRepository.findById(id);
        updatedTask.setTitle(newTitle);
        updatedTask.setDescription(newDescription);
        updatedTask.setDueDate(newDueDate);
        listRepository.update(id, updatedTask);
        return updatedTask;
    }
    public Task updateStatus(UUID id, TaskStatus newStatus) throws NotFoundException {
        Task updatedTask = listRepository.findById(id);
        updatedTask.setTaskStatus(newStatus);
        listRepository.update(id, updatedTask);
        return updatedTask;
    }
    public void deleteTask(UUID id) throws NotFoundException {
        listRepository.delete(id);
    }
    public List<Task> filterByStatus(TaskStatus taskStatus){
        List<Task> list = new ArrayList<>(listRepository.findAll().values());
        return list.stream()
                .filter(task -> task.getTaskStatus() == taskStatus)
                .toList();
    }
    public List<Task> sortByDueDate(){
        List<Task> list = new ArrayList<>(listRepository.findAll().values());
        return list.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
    }
    public List<Task> sortByStatus(){
        List<Task> list =  new ArrayList<>(listRepository.findAll().values());
        return list.stream()
                .sorted(Comparator.comparing(Task::getTaskStatus))
                .toList();

    }

}
