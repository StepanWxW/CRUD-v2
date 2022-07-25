package com.stepanwxw.crudv2.repository.implementation;

import com.stepanwxw.crudv2.model.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post,Long> {
    @Override
    Post create(Post post);

    @Override
    List<Post> getAll();

    @Override
    Post getByID(Long id);

    @Override
    Post update(Post post);

    @Override
    void remove(Long id);
}
