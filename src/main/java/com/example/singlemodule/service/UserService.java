package com.example.singlemodule.service;

import com.example.singlemodule.domain.UserInfo;
import com.example.singlemodule.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    // field injection
    @Autowired
    private UserInfoRepository userInfoRepository;


    public List<UserInfo> allBySort() {
        Sort sort = Sort.by(Sort.Direction.DESC,"userInfoId");
        return this.userInfoRepository.findAll(sort);
    }


}