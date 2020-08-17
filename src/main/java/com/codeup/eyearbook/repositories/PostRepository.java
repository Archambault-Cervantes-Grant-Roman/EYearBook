package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Signatures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Signatures, Long> {
}