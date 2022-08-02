package com.stepanwxw.crudv2.view;
import com.stepanwxw.crudv2.directory.MenuDirectory;
import com.stepanwxw.crudv2.repository.UserRepositoryImpl;
import com.stepanwxw.crudv2.view.userView.CreateUser;
import com.stepanwxw.crudv2.view.userView.UpdateUser;

import java.util.Scanner;

public class UserMenu {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    CreateUser createUser = new CreateUser();
    UpdateUser updateUser = new UpdateUser();
    private String scanLine(){
        return new Scanner(System.in).nextLine();
    }
    public void menu() {
        System.out.println(MenuDirectory.MAIN_MENU);
        switch (scanLine()) {
            case ("1"): createUser.create(); break;
            case ("2"): readAll(); break;
            case ("3"): readId(); break;
            case ("4"): deleteId(); break;
            case ("5"): updateUser.update(); break;
        }
    }
    public void readAll() {
        String[] words = userRepository.getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }
    private void readId() {
        System.out.println("Enter id user please:");
        try {
            Long readId = Long.valueOf(scanLine());
            if (userRepository.getByID(readId) == null) {
                System.out.println("This object is missing.");
            } else {
                System.out.println(userRepository.getByID(readId));
            }
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
    private void deleteId() {
        System.out.println("Enter id to delete post please:");
        try {
            Long readId = Long.valueOf(scanLine());
            if (userRepository.getByID(readId) == null) {
                System.out.println("This object is missing.");
            } else {
                userRepository.remove(readId);
                System.out.println("User id: " + readId + " delete is complete.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
}

