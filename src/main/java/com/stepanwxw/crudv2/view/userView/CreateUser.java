package com.stepanwxw.crudv2.view.userView;

import com.stepanwxw.crudv2.model.User;

public class CreateUser extends WorkUser{

    public void create() {
        System.out.println("Enter User first name please:");
        String firstName = scanLine();
        System.out.println("Enter User last name please:");
        String lastName = scanLine();
        postsAllRead();
        postsAdd();
        regionAllRead();
        regionAdd();
        roleAdd();
        userRepository.create(new User(firstName, lastName, postList, region, role));
        System.out.println("Congratulation: user create is complete.");
    }
}