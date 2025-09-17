package org.openjfx.listcontroller;

import org.openjfx.enums.TaskStatus;
import org.openjfx.exceptions.NotFoundException;
import org.openjfx.exceptions.NotValidData;
import org.openjfx.listrepository.ListRepository;
import org.openjfx.listservice.ListService;
import org.openjfx.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ListController {
    private ListService listService;
    public ListController(ListService listService){
        this.listService = listService;
    }

    private final Scanner scanner = new Scanner(System.in);
    public void addTask() throws NotValidData {
        Task task = new Task();
        System.out.print("Введите название таски: ");
        String title = scanner.nextLine();
        System.out.print("Введите описание таски: ");
        String description = scanner.nextLine();
        System.out.print("Введите дату YYYY-MM-DD: ");
        String dueDate = scanner.nextLine();
        listService.addTask(title, description, LocalDate.parse(dueDate));
        System.out.println("Таска успешно создана!");
    }
    public void showTasks(){
        List<Task> list = listService.listTasks();
        System.out.println("Все задачи:");
        list.forEach(System.out::println);
    }
    public void editTask() throws NotFoundException {
        System.out.print("Введите id таски: ");
        UUID id = UUID.fromString(scanner.nextLine());
        System.out.print("Введите название: ");
        String newTitle = scanner.nextLine();
        System.out.print("Введите описание: ");
        String newDescription = scanner.nextLine();
        System.out.print("Введите дату: ");
        String newDueDate = scanner.nextLine();
        listService.editTask(id, newTitle, newDescription, LocalDate.parse(newDueDate));
        System.out.println("Таска успешно изменена!");
    }
    public void updateStatus() throws NotFoundException {
        System.out.print("Введите id таски: ");
        UUID id = UUID.fromString(scanner.nextLine());
        System.out.print("Введите новый статус: ");
        TaskStatus newStatus =  TaskStatus.valueOf(scanner.nextLine());
        listService.updateStatus(id, newStatus);
        System.out.println("Статус успешно обновлён!");
        System.out.println(listService.listTask(id));
    }
    public void deleteTask() throws NotFoundException {
        System.out.print("Введите id таски: ");
        UUID id = UUID.fromString(scanner.nextLine());
        listService.deleteTask(id);
        System.out.println("Таска успешно удалена!");
    }
    public void filterTasks(){
        System.out.print("Введите статус для фильтра: ");
        TaskStatus taskStatus = TaskStatus.valueOf(scanner.nextLine());
        System.out.println(listService.filterByStatus(taskStatus));
    }
    public void sortTasks(){
        System.out.print("Введите критерий для сортировки: ");
        switch(scanner.nextLine()){
            case "Статус":
                System.out.println(listService.sortByStatus());
            case "Дата":
                System.out.println(listService.sortByDueDate());
        }
    }
    public void exit(){
        System.out.println("До свидания!");
    }
}
