package com.betting.backend.User;

import jakarta.persistence.*;


@Entity(name="User")
@Table(name="USERS")
public class User {
    @Column(name="USERNAME",length=50,nullable=false,unique=false)
    private String username;
    @Column(name="PASSWORD",length=50,nullable=false,unique=false)
    private String password;
    @Column(name="BIO",length=50,nullable=false,unique=false)
    private String Bio;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ID;
    public User(){
        username="None";
        password="Not Created";
        Bio="Not Created";
    }
    public User(String username,String password,String Bio){
        this.username=username;
        this.password=password;
        this.Bio=Bio;
    }
    public String getUsername(){
        return username;
    }
    public String getBio(){
        return Bio;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setBio(String Bio){
        this.Bio=Bio;
    }
    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password=password;
    }
    public Long getId(){
        return ID;
    }
    public void setId(Long ID){
        this.ID=ID;
    }








}
