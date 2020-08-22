package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Roles extends CrudRepository<User, Long> {
    @Query("select ur.role from Permission ur, User u where u.username=?1 and ur.id=u.id")
    String ofUserWith(String username);

}
