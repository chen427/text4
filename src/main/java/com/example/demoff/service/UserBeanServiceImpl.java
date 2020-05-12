package com.example.demoff.service;
import com.example.demoff.entity.Userbean;
import com.example.demoff.repository.UserBeanRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserBeanServiceImpl implements UserBeanService {
    @Resource
    private UserBeanRepository userBeanRepository;

    @Override
    public Userbean getUser(String usrName) {
        return userBeanRepository.findUserbeanByUsrName(usrName);
    }
}
