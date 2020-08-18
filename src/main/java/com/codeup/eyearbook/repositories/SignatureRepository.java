package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Signatures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<Signatures, Long> {
}
