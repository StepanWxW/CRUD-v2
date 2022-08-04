package com.stepanwxw.crudv2.view;

import com.stepanwxw.crudv2.directory.MenuDirectory;
import com.stepanwxw.crudv2.model.Post;
import com.stepanwxw.crudv2.repository.PostRepositoryImpl;

import java.util.Scanner;

public class PostMenu {
        PostRepositoryImpl postRepository = new PostRepositoryImpl();
    public void startPostMenu() {
        System.out.println(MenuDirectory.MAIN_MENU);
        switch (scanLine()) {
            case ("1"):
                create();
                break;
            case ("2"):
                readAll();
                break;
            case ("3"):
                readId();
                break;
            case ("4"):
                deleteId();
                break;
            case ("5"):
                updateId();
                break;
        }
    }
    private void create() {
        System.out.println("Enter Post please:");
        postRepository.create(new Post(scanLine()));
        System.out.println("Congratulation: create is complete.");
    }
    private void readAll() {
        String[] words = postRepository.getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }
    private void readId() {
        System.out.println("Enter id post please:");
        try {
            Long readId = Long.valueOf(scanLine());
            System.out.println(postRepository.getByID(readId));
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
    private void deleteId() {
        System.out.println("Enter id to delete post please:");
        try {
            Long readId = Long.valueOf(scanLine());
            postRepository.remove(readId);
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
    private void updateId() {
        System.out.println("Enter id to update post please:");
        try {
            Long readId = Long.valueOf(scanLine());
            if(postRepository.getByID(readId) != null) {
                System.out.println("Enter please new post for id: " +readId);
                postRepository.update(new Post(readId, scanLine()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }

    private String scanLine(){
        return new Scanner(System.in).nextLine();
    }
}

