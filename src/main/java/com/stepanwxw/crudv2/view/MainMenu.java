package com.stepanwxw.crudv2.view;

import java.util.Scanner;

public class MainMenu {
    public void startMenu() {
        System.out.println("Welcome from CRUD (Create, Read, Update, Delete)");
        mainMenu();
    }
    public void mainMenu() {
        while (true) {
            System.out.println("Choose an object for work");
            System.out.println("Please write \"1\" - Region, \"2\" - Post, \"3\" - User:");
            switch (new Scanner(System.in).nextLine()) {
                case ("1"): new RegionMenu().menu(); break;
                case ("2"): new PostMenu().menu(); break;
                case ("3"): new UserMenu().userMenu(); break;
                default:
                    System.out.println("Please write number (for example: 2)");
            }
        }
    }
}