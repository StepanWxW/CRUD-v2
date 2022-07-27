package com.stepanwxw.crudv2.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stepanwxw.crudv2.model.Post;
import com.stepanwxw.crudv2.model.Region;
import com.stepanwxw.crudv2.repository.implementation.PostRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class PostRepositoryImpl implements PostRepository {
    private Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }
    final String filePosts = "src" + separator + "main" + separator + "resources" + separator + "posts.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    List<Post> posts = new ArrayList<>();
    Post post;
    private Long generateId() {
        long id = 1L;
        Optional<Post> optGenerateId = posts.stream().max(Comparator.comparing(Post::getId));
        optGenerateId.ifPresent(value -> post = value);
        if (post != null) {
            id = post.getId()+ 1L;
        }
        return id;
    }
    private Post returnId(Long id) {
        Optional<Post> optReturnId = posts.stream().filter((s) -> Objects.equals(s.getId(), id)).findFirst();
        optReturnId.ifPresent(value -> post = value);
        return post;
    }
    private void openFile() {
        Type itemsListType = new TypeToken<List<Post>>() {}.getType();
        try (FileReader fileReader = new FileReader(filePosts)){
            List<Post> testNull;
            testNull = gson.fromJson(fileReader, itemsListType);
            if (testNull != null) {
                posts = testNull;
            }
        }catch (IOException e) {
            System.out.println("Problem with fileReader" + e);
        }
    }
    private void writeFile() {
        try (FileWriter fileWriter = new FileWriter(filePosts)){
            gson.toJson(posts, fileWriter);
        } catch (IOException e) {
            System.out.println("Problem with fileWriter" + e);
        }
    }
    @Override
    public Post create(Post post) {
        openFile();
        posts.add(new Post(generateId(),post.getContent(),getCurrentTime(),getCurrentTime()));
        writeFile();
        return post;
    }

    @Override
    public List<Post> getAll() {
        openFile();
        return posts;
    }

    @Override
    public Post getByID(Long id) {
        openFile();
        return returnId(id);
    }

    @Override
    public Post update(Post post) {
        openFile();
        Post postNew = returnId(post.getId());
        if (postNew == null){
            post = null;
        }
        else {
            posts.add(new Post(post.getId(),post.getContent(),postNew.getCreate(),getCurrentTime()));
            posts.remove(postNew);
            posts = posts.stream().sorted(((o2, o1) -> -o1.getId().compareTo(o2.getId()))).collect(Collectors.toList());
        }
        writeFile();
        return post;
    }

    @Override
    public void remove(Long id) {
        openFile();
        posts.remove(returnId(id));
        writeFile();
    }
}
