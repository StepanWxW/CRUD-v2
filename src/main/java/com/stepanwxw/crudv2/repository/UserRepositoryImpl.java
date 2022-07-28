package com.stepanwxw.crudv2.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stepanwxw.crudv2.model.User;
import com.stepanwxw.crudv2.repository.implementation.UserRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class UserRepositoryImpl implements UserRepository {
    final String fileUser = "src" + separator + "main" + separator + "resources" + separator + "users.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    List<User> users = new ArrayList<>();
    User user;
    private Long generateId() {
        long id = 1L;
        Optional<User> optGenerateId = users.stream().max(Comparator.comparing(User::getId));
        optGenerateId.ifPresent(value -> user = value);
        if (user != null) {
            id = user.getId()+ 1L;
        }
        return id;
    }
    private User returnId(Long id) {
        Optional<User> optReturnId = users.stream().filter((s) -> Objects.equals(s.getId(), id)).findFirst();
        if(optReturnId.isPresent()) {
            optReturnId.ifPresent(value -> user = value);
        }else {
            user = null;
        }
        return user;
    }
    private void openFile() {
        Type itemsListType = new TypeToken<List<User>>() {}.getType();
        try (FileReader fileReader = new FileReader(fileUser)){
            List<User> testNull;
            testNull = gson.fromJson(fileReader, itemsListType);
            if (testNull != null) {
                users = testNull;
            }
        }catch (IOException e) {
            System.out.println("Problem with fileReader" + e);
        }
    }
    private void writeFile() {
        try (FileWriter fileWriter = new FileWriter(fileUser)){
            gson.toJson(users, fileWriter);
        } catch (IOException e) {
            System.out.println("Problem with fileWriter" + e);
        }
    }
    @Override
    public User create(User user) {
        openFile();
        users.add(new User(generateId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPosts(),
                user.getRegion(),
                user.getRole()));
        writeFile();
        return user;
    }

    @Override
    public List<User> getAll() {
        openFile();
        return users;
    }

    @Override
    public User getByID(Long id) {
        openFile();
        return returnId(id);
    }

    @Override
    public User update(User user) {
        openFile();
        User userNew = returnId(user.getId());
        if (userNew == null){
            user = null;
        }
        else {
            users.remove(userNew);
            users.add(user);
            users = users.stream().sorted(((o2, o1) -> -o1.getId().compareTo(o2.getId()))).collect(Collectors.toList());
        }
        writeFile();
        return user;
    }

    @Override
    public void remove(Long id) {
        openFile();
        users.remove(returnId(id));
        writeFile();
    }
}
