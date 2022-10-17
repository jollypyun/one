package com.example.one.repository;

import com.example.one.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneRepository extends JpaRepository<UserInfo, String> {

}
