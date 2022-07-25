package com.stepanwxw.crudv2.repository.implementation;

import com.stepanwxw.crudv2.model.User;
import java.util.List;

public interface UserRepository extends GenericRepository<User,Long> {
    @Override
    User create(User user);
    @Override
    List<User> getAll();
    @Override
    User getByID(Long id);
    @Override
    User update(User user);
    @Override
    void remove(Long id);
}
