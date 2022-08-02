package com.stepanwxw.crudv2.view.userView;
import com.stepanwxw.crudv2.model.User;

public class UpdateUser extends WorkUser {
    public void update() {
        System.out.println("Enter id user please:");
        try {
            Long readId = Long.valueOf(scanLine());
            if (userRepository.getByID(readId) == null) {
                System.out.println("This object is missing.");
            } else {
                System.out.println("Enter NEW User first name please:");
                String firstName = scanLine();
                System.out.println("Enter NEW User last name please:");
                String lastName = scanLine();
                postsAllRead();
                postsAdd();
                regionAllRead();
                regionAdd();
                roleAdd();
                userRepository.remove(readId);
                userRepository.update(new User(readId, firstName, lastName, postList, region, role));
                System.out.println("Congratulation: user update is complete.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input number please");
        }

    }
}

