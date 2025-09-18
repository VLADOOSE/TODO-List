package org.openjfx.listcontroller;

import org.openjfx.enums.TaskStatus;
import org.openjfx.exceptions.NotFoundException;
import org.openjfx.exceptions.InvalidDataTime;
import org.openjfx.listservice.ListService;
import org.openjfx.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ListController {
    private final ListService listService;
    public ListController(ListService listService){
        this.listService = listService;
    }

    private final Scanner scanner = new Scanner(System.in);
    public void addTask(){
        try {
            System.out.print("Введите название таски: ");
            String title = scanner.nextLine();
            System.out.print("Введите описание таски: ");
            String description = scanner.nextLine();
            System.out.print("Введите дату YYYY-MM-DD: ");
            String dueDate = scanner.nextLine();
            listService.addTask(title, description, LocalDate.parse(dueDate));
            System.out.println("Таска успешно создана!");
        }catch (DateTimeParseException e){
            System.err.println("Неправильный формат даты");
        }catch (InvalidDataTime e){
            System.err.println("Дата должна быть позже текущей");
        }
    }
    public void showTasks(){
        List<Task> list = listService.listTasks();
        System.out.println("Все задачи:");
        list.forEach(System.out::println);
    }
    public void editTask() throws NotFoundException {
        try {
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
        }catch (IllegalArgumentException e) {
            System.err.println("Таска не существует");
        }catch (DateTimeParseException e){
            System.err.println("Неправильный формат даты");
        }catch (InvalidDataTime e){
            System.err.println("Дата должна быть позже текущей");
        }
    }
    public void updateStatus() throws NotFoundException {
        try {
            System.out.print("Введите id таски: ");
            UUID id = UUID.fromString(scanner.nextLine());
            System.out.println("Введите новый статус: " +
                    "\n1 - TODO" +
                    "\n2 - IN_PROGRESS" +
                    "\n3 - DONE");
            String input = scanner.nextLine();
            switch(input) {
                case "1":
                    input = "TODO";
                    break;
                case "2":
                    input = "IN_PROGRESS";
                    break;
                case "3":
                    input = "DONE";
                    break;
                default:
                    System.err.println("Неизвестный статус");
                    break;
            }
            TaskStatus newStatus = TaskStatus.valueOf(input);
            listService.updateStatus(id, newStatus);
            System.out.println("Статус успешно обновлён!");
            System.out.println(listService.listTask(id));
        }catch (IllegalArgumentException e){
            System.err.println("Таска не существует");
        }
    }
    public void deleteTask() throws NotFoundException {
        try {
            System.out.print("Введите id таски: ");
            UUID id = UUID.fromString(scanner.nextLine());
            listService.deleteTask(id);
            System.out.println("Таска успешно удалена!");
        }catch(IllegalArgumentException e){
            System.err.println("Таска не существует");
        }
    }
    public void filterTasks(){
        try {
            System.out.println("Введите статус для фильтра: " +
                    "\n1 - TODO" +
                    "\n2 - IN_PROGRESS" +
                    "\n3 - DONE");
            String input = scanner.nextLine();
            input = switch (input) {
                case "1" -> "TODO";
                case "2" -> "IN_PROGRESS";
                case "3" -> "DONE";
                default -> input;
            };
            TaskStatus taskStatus = TaskStatus.valueOf(input);
            System.out.println(listService.filterByStatus(taskStatus));
        }catch (NoSuchElementException | IllegalArgumentException e){
            System.err.println("Неизвестный статус");
        }
    }
    public void sortTasks(){
        System.out.println("Введите критерий для сортировки: " +
                "\n1 - статус" +
                "\n2 - дата");
        switch(scanner.nextLine()){
            case "1":
                System.out.println(listService.sortByStatus());
                break;
            case "2":
                System.out.println(listService.sortByDueDate());
                break;
            default:
                System.err.println("Неизвестный критерий");
                break;
        }
    }
    public void exit(){
        System.out.println("До свидания!");
    }
}
