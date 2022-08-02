package com.stepanwxw.crudv2.view;
import com.stepanwxw.crudv2.directory.MenuDirectory;
import com.stepanwxw.crudv2.model.Post;
import com.stepanwxw.crudv2.model.Region;
import com.stepanwxw.crudv2.model.Role;
import com.stepanwxw.crudv2.model.User;
import com.stepanwxw.crudv2.repository.PostRepositoryImpl;
import com.stepanwxw.crudv2.repository.RegionRepositoryImpl;
import com.stepanwxw.crudv2.repository.UserRepositoryImpl;
import com.stepanwxw.crudv2.repository.implementation.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMenu {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    public void startUserMenu() {
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
        System.out.println("Enter User first name please:");
        String firstName = scanLine();
        System.out.println("Enter User last name please:");
        String lastName = scanLine();
        postsAllRead();
        System.out.println("Enter id post and press \"enter\" for next post. \n" +
                "If you want to finish typing, then write \"exit\"");
        List<Post> postList = postsAdd();
        regionAllRead();
        System.out.println("Enter id region please:");
        Region region = regionAdd();
        System.out.println("Enter please Role: 1 - ADMIN, 2 - USER, 3 - MODERATOR");
        Role role = roleAdd();
        userRepository.create(new User(firstName, lastName, postList, region, role));
        System.out.println("Congratulation: user create is complete.");
    }
    protected void postsAllRead() {
        String[] words = new PostRepositoryImpl().getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }

    protected List<Post> postsAdd() {
        PostRepositoryImpl postRepository = new PostRepositoryImpl();
        List<Post> postList = new ArrayList<>();
        while (true) {
            String s = scanLine();
            if (s.equals("exit") && !postList.isEmpty()) {
                break;
            }
            if (s.equals("exit")){
                System.out.println("Enter please at least one id");
            }
            else {
                try {
                    Long readId = Long.valueOf(s);
                    if (postRepository.getByID(readId) != null) {
                        postList.add(postRepository.getByID(readId));
                        System.out.println("Post with id: " + s +" add is complete." +
                                "\nPlease enter \"exit\" or add id post.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input number please");

                }
            }
        }
        return postList;
    }
    protected void regionAllRead() {
        RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();
        String[] words = regionRepository.getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }
    protected Region regionAdd() {
        RegionRepository regionRepository = new RegionRepositoryImpl();
        Region region;
        while (true) {
            String s = scanLine();
            try {
                Long readId = Long.valueOf(s);
                if (regionRepository.getByID(readId) != null) {
                    region = regionRepository.getByID(readId);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number please");
            }
        }
        return region;
    }
    protected Role roleAdd() {
        Role role = null;
        boolean flagRole = true;
        while (flagRole) {
            String s = scanLine();
            switch (s) {
                case ("1"): case ("ADMIN"): role = Role.ADMIN; flagRole = false; break;
                case ("2"): case ("USER"): role = Role.USER; flagRole = false; break;
                case ("3"): case ("MODERATOR"): role = Role.MODERATOR; flagRole = false; break;
                default:
                    System.out.println("Enter please: \"USER\", \"ADMIN\", \"MODERATOR\" " +
                            "or \"1\", \"2\", \"3\".");
            }
        }
        return role;
    }
    private void readAll() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        String[] words = userRepository.getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }
    private void readId() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        System.out.println("Enter id user please:");
        try {
            Long readId = Long.valueOf(scanLine());
            System.out.println(userRepository.getByID(readId));
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
    private void deleteId() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        System.out.println("Enter id to delete User please:");
        try {
            Long readId = Long.valueOf(scanLine());
            userRepository.remove(readId);
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
    private void updateId() {
        System.out.println("Enter id user please:");
        try {
            Long readId = Long.valueOf(scanLine());
            if (userRepository.getByID(readId) != null) {
                System.out.println("Enter NEW User first name please:");
                String firstName = scanLine();
                System.out.println("Enter NEW User last name please:");
                String lastName = scanLine();
                postsAllRead();
                System.out.println("Enter id post and press \"enter\" for next post. \n" +
                        "If you want to finish typing, then write \"exit\"");
                List<Post> postList = postsAdd();
                regionAllRead();
                System.out.println("Enter id region please:");
                Region region = regionAdd();
                System.out.println("Enter please Role: 1 - ADMIN, 2 - USER, 3 - MODERATOR");
                Role role = roleAdd();
                userRepository.remove(readId);
                userRepository.update(new User(readId, firstName, lastName, postList, region, role));
                System.out.println("Congratulation: user update is complete.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }
    }
    private String scanLine(){
        return new Scanner(System.in).nextLine();
    }
}