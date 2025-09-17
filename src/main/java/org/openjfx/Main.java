package org.openjfx;

import org.openjfx.exceptions.NotFoundException;
import org.openjfx.exceptions.NotValidData;
import org.openjfx.listcontroller.ListController;
import org.openjfx.listrepository.ListRepository;
import org.openjfx.listservice.ListService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws NotValidData, NotFoundException {
        ListRepository listRepository = new ListRepository();
        ListService listService = new ListService(listRepository);
        ListController listController = new ListController(listService);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Добро пожаловать в приложение TODO Лист!\nДоступные команды:\n" +
                "addTask\n" +
                "showTasks\n" +
                "editTask\n" +
                "updateStatus\n" +
                "deleteTask\n" +
                "filterTasks\n" +
                "sortTasks\n" +
                "exit");

        while(!exit){
            switch (scanner.nextLine()){
                case "addTask":
                    listController.addTask();
                    break;
                case "showTasks":
                    listController.showTasks();
                    break;
                case "editTask":
                    listController.editTask();
                    break;
                case "updateStatus":
                    listController.updateStatus();
                    break;
                case "deleteTask":
                    listController.deleteTask();
                    break;
                case "filterTask":
                    listController.filterTasks();
                    break;
                case "sortTasks":
                    listController.sortTasks();
                    break;
                case "exit":
                    listController.exit();
                    exit = true;
                    break;
            }
        }
    }
}