package com.stepanwxw.crudv2.view;

import java.util.Scanner;

public class MainMenu {
    public void startMenu() {
        System.out.println("Welcome from CRUD v 2.0 (Create, Read, Update, Delete)");
        while (true) {
            System.out.println("Choose object for work \n" +
                    "Please write \"1\" - Region, \"2\" - Post, \"3\" - User:");
            switch (new Scanner(System.in).nextLine()) {
                case ("1"):
                    new RegionMenu().startRegionMenu();
                    break;
                case ("2"):
                    new PostMenu().startPostMenu();
                    break;
                case ("3"):
                    new UserMenu().startUserMenu();
                    break;
                default:
                    System.out.println("Please write number (for example: 2)");
            }
        }
    }
}