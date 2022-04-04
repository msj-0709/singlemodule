package com.example.singlemodule.repository;

import com.example.singlemodule.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, String> {
    Optional<Board> findByUserInfoId(String userInfoId);
}