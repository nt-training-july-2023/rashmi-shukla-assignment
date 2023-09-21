package com.project.assesmentportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.assesmentportal.entities.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByUserEmail(String userEmail);
}
