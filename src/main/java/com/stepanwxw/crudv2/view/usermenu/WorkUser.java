package com.stepanwxw.crudv2.view.usermenu;

import com.stepanwxw.crudv2.model.*;
import com.stepanwxw.crudv2.repository.*;
import java.util.*;

public class WorkUser {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    PostRepositoryImpl postRepository = new PostRepositoryImpl();
    List<Post> postList = new ArrayList<>();
    RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();
    Region region;
    Role role;
    String scanLine() {
        return new Scanner(System.in).nextLine();
    }
    protected void postsAllRead() {
        String[] words = postRepository.getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }

    protected void postsAdd() {
        System.out.println("Enter id post and press \"enter\" for next post. \n" +
                "If you want to finish typing, then write \"exit\"");
        while (true) {
            String idPost = scanLine();
            if (idPost.equals("exit") && !postList.isEmpty()) {
                break;
            }
            if (idPost.equals("exit")){
                System.out.println("Enter please at least one id");
            }
            else {
                try {
                    Long readId = Long.valueOf(idPost);
                    if (postRepository.getByID(readId) == null) {
                        System.out.println("This object is missing.");
                    } else {
                        postList.add(postRepository.getByID(readId));
                        System.out.println("Post with id: " + idPost +" add is complete." +
                                "\nPlease enter \"exit\" or add id post.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input number please");

                }
            }
        }
    }
    protected void regionAllRead() {
        String[] words = regionRepository.getAll().toString().split("},");
        for (String word : words) {
            System.out.println(word);
        }
    }
    protected void regionAdd() {
        while (true) {
            System.out.println("Enter id region please:");
            try {
                Long readId = Long.valueOf(scanLine());
                if (regionRepository.getByID(readId) == null) {
                    System.out.println("This object is missing.");
                } else {
                    region = regionRepository.getByID(readId);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number please");
            }
        }
    }
    protected void roleAdd() {
        System.out.println("Enter please Role: 1 - ADMIN, 2 - USER, 3 - MODERATOR");
        boolean flagRole = true;
        while (flagRole) {
            switch (scanLine()) {
                case ("1"): case ("ADMIN"): role = Role.ADMIN; flagRole = false; break;
                case ("2"): case ("USER"): role = Role.USER; flagRole = false; break;
                case ("3"): case ("MODERATOR"): role = Role.MODERATOR; flagRole = false; break;
                default:
                    System.out.println("Enter please: \"USER\", \"ADMIN\", \"MODERATOR\" " +
                            "or \"1\", \"2\", \"3\".");
            }
        }
    }
}
