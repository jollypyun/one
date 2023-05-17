package com.example.one.repository;

import com.example.one.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Member, String> {
    Optional<Member> findByNickname(String nickname);
}
