package com.stepanwxw.crudv2.repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.stepanwxw.crudv2.directory.JsonDirectory;
import com.stepanwxw.crudv2.model.Post;
import com.stepanwxw.crudv2.repository.implementation.PostRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class PostRepositoryImpl implements PostRepository {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Post create(Post post) {
        List<Post> postsListCreate = getAll();
        Post postCreate = postsListCreate.stream().max(Comparator.comparing(Post::getId))
                .orElse(new Post(0L));
        long id = postCreate.getId() + 1L;
        postsListCreate.add(new Post(id, post.getContent(),getCurrentTime(), getCurrentTime()));
        writeFile(postsListCreate);
        return post;
    }
    private Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Post>>() {}.getType();
        try (FileReader fileReader = new FileReader(JsonDirectory.POST)) {
            List<Post> testNull = gson.fromJson(fileReader, itemsListType);
            if (testNull != null) {
                posts = testNull;
            }
        } catch (IOException e) {
            System.out.println("Problem with fileReader" + e);
        }
        return posts;
    }

    @Override
    public Post getByID(Long id) {
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
    public Post update(Post post) {
        List<Post> postsListUpdate = getAll();
        Post newPost = new Post(post.getId(), post.getContent(),post.getCreate(),getCurrentTime());
        postsListUpdate = postsListUpdate.stream()
                .filter((p) -> !Objects.equals(p.getId(), post.getId()))
                .collect(Collectors.toList());
        postsListUpdate.add(newPost);
        postsListUpdate = postsListUpdate.stream().
                sorted(((o2, o1) -> -o1.getId().compareTo(o2.getId()))).
                collect(Collectors.toList());
        writeFile(postsListUpdate);
        return post;
    }

    @Override
    public void remove(Long id) {
        List<Post> postsRemove = getAll().stream()
                .filter((p) -> !Objects.equals(p.getId(), id))
                .collect(Collectors.toList());
        writeFile(postsRemove);
    }

    private void writeFile(List<Post> postsList) {
        try (FileWriter fileWriter = new FileWriter(JsonDirectory.POST)) {
            gson.toJson(postsList, fileWriter);
        } catch (IOException e) {
            System.out.println("Problem with fileWriter" + e);
        }
    }
}

