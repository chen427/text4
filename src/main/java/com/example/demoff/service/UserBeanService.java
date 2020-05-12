package com.example.demoff.service;

import com.example.demoff.entity.Userbean;
import org.springframework.stereotype.Service;
public interface UserBeanService {
    public Userbean getUser(String usrName);
}
