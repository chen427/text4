package com.example.demoff.entity;

import javax.persistence.*;

@Entity
@Table(name = "userbean")
public class Userbean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private long usrId;
    @Column(name = "usr_flag")
    private String usrFlag;
    @Column(name = "usr_name")
    private String usrName;
    @Column(name = "usr_password")
    private String usrPassword;
    @Column(name = "usr_role_id")
    private String usrRoleId;


    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }


    public String getUsrFlag() {
        return usrFlag;
    }

    public void setUsrFlag(String usrFlag) {
        this.usrFlag = usrFlag;
    }


    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }


    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }


    public String getUsrRoleId() {
        return usrRoleId;
    }

    public void setUsrRoleId(String usrRoleId) {
        this.usrRoleId = usrRoleId;
    }

}
