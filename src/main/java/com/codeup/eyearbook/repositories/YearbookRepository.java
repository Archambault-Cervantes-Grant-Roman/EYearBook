package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Yearbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearbookRepository extends JpaRepository<Yearbook, Long> {
}
