package org.openjfx;

import org.openjfx.exceptions.NotFoundException;
import org.openjfx.exceptions.InvalidDataTime;
import org.openjfx.listcontroller.ListController;
import org.openjfx.listrepository.ListRepository;
import org.openjfx.listservice.ListService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidDataTime, NotFoundException {
        ListRepository listRepository = new ListRepository();
        ListService listService = new ListService(listRepository);
        ListController listController = new ListController(listService);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Добро пожаловать в приложение TODO Лист!\nДоступные команды:\n" +
                "add\n" +
                "show\n" +
                "edit\n" +
                "update\n" +
                "delete\n" +
                "filter\n" +
                "sort\n" +
                "exit");

        while(!exit){
            switch (scanner.nextLine()){
                case "add":
                    listController.addTask();
                    break;
                case "show":
                    listController.showTasks();
                    break;
                case "edit":
                    listController.editTask();
                    break;
                case "update":
                    listController.updateStatus();
                    break;
                case "delete":
                    listController.deleteTask();
                    break;
                case "filter":
                    listController.filterTasks();
                    break;
                case "sort":
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