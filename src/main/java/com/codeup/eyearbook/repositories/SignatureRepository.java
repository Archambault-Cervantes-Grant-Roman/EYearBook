package com.codeup.eyearbook.repositories;

import com.codeup.eyearbook.models.Signatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SignatureRepository extends JpaRepository <Signatures, Long> {

}
