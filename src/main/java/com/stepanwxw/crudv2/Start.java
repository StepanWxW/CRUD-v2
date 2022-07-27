package com.stepanwxw.crudv2;

import com.stepanwxw.crudv2.model.Post;
import com.stepanwxw.crudv2.repository.PostRepositoryImpl;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        PostRepositoryImpl repository = new PostRepositoryImpl();
        repository.create(new Post("Волосы"));
        repository.create(new Post("Прическа"));
        repository.create(new Post("Глаза"));
        System.out.println(repository.getAll());
        repository.remove(2L);
        System.out.println(repository.getAll());
        Thread.sleep(1000);
        repository.create(new Post("Уши"));
        repository.update(new Post(3L, "ТЕперь не глаза"));
        System.out.println(repository.getAll());
        System.out.println(repository.getByID(25L));
        System.out.println(repository.getByID(1L));
        repository.remove(15L);
        System.out.println(repository.getByID(2L));
    }
}
