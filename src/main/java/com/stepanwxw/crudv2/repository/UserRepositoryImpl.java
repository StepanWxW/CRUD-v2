package com.stepanwxw.crudv2.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stepanwxw.crudv2.directory.JsonDirectory;

import com.stepanwxw.crudv2.exception.NoEntityException;
import com.stepanwxw.crudv2.model.User;
import com.stepanwxw.crudv2.repository.implementation.UserRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public User create(User user) {
        List<User> usersCreate = getAll();
        User regionCreate = usersCreate.stream().max(Comparator.comparing(User::getId))
                .orElse(new User(0L));
        long id = regionCreate.getId() + 1L;
        usersCreate.add(new User(id, user.getFirstName(), user.getLastName(),
                user.getPosts(),user.getRegion(),user.getRole()));
        writeFile(usersCreate);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Type itemsListType = new TypeToken<List<User>>() {}.getType();
        try (FileReader fileReader = new FileReader(JsonDirectory.USER)) {
            List<User> testNull = gson.fromJson(fileReader, itemsListType);
            if (testNull != null) {
                users = testNull;
            }
        } catch (IOException e) {
            System.out.println("Problem with fileReader" + e);
        }
        return users;
    }

    @Override
    public User getByID(Long id) {
        try {
            return getAll().stream()
                    .filter(r -> id.equals(r.getId()))
                    .findFirst().orElseThrow(() ->new NoEntityException(id));
        } catch (NoEntityException e) {
            System.out.println("This object is missing");
        }
        return null;
    }

    @Override
    public User update(User user) {
        List<User> usersListUpdate = getAll();
        usersListUpdate = usersListUpdate.stream()
                .filter((p) -> !Objects.equals(p.getId(), user.getId()))
                .collect(Collectors.toList());
        usersListUpdate.add(user);
        usersListUpdate = usersListUpdate.stream().
                sorted(((o2, o1) -> -o1.getId().compareTo(o2.getId()))).
                collect(Collectors.toList());
        writeFile(usersListUpdate);
        return user;
    }

    @Override
    public void remove(Long id) {
        List<User> userListRemove = getAll().stream()
                .filter((p) -> !Objects.equals(p.getId(), id))
                .collect(Collectors.toList());
        writeFile(userListRemove);
    }

    private void writeFile(List<User> userList) {
        try (FileWriter fileWriter = new FileWriter(JsonDirectory.USER)) {
            gson.toJson(userList, fileWriter);
        } catch (IOException e) {
            System.out.println("Problem with fileWriter" + e);
        }
    }
}
