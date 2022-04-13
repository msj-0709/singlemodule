package com.example.singlemodule.repository;

import com.example.singlemodule.constant.UserStatusType;
import com.example.singlemodule.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {



    @Query(value = "select NEXTVAL(seq_user_info_pk);", nativeQuery = true)
    long getNextUserInfoId();

    List<UserInfo> findAll();

    Optional<UserInfo> findByUserInfoId(String id);

    long countByUserInfoIdAndStatus(String id, UserStatusType status);

    Optional<UserInfo> findByUsername(String username);

    long countByUsername(String username);
}