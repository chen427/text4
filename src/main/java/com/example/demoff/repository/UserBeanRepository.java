package com.example.demoff.repository;

import com.example.demoff.entity.Userbean;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserBeanRepository extends JpaRepository<Userbean,Long> {
     public Userbean findUserbeanByUsrName(String usrName);
}
