package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}