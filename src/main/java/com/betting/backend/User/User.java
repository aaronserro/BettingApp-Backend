package com.betting.backend.User;

import jakarta.persistence.*;


@Entity(name="User")
@Table(name="USERS")
public class User {

    @Column(name="USERNAME",length=255,nullable=false,unique=false)
    private String username;
    @Column(name="PASSWORD",length=255,nullable=false,unique=false)
    private String password;
    @Column(name="BIO",length=255,nullable=false,unique=false)
    private String Bio;
    @Column(name="HEARD_FROM")
    private String heardFrom;

    @Column(name="NICHE")
    private String niche;

    @Column(name="UNIVERSITY")
    private String university;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long ID;

    public User(){
        username="None";
        password="Not Created";
        Bio="Not Created";
    }
    public User(String username, String password, String bio, String heardFrom, String niche, String university){
        this.username=username;
        this.password=password;
        this.Bio=bio;
        this.heardFrom=heardFrom;
        this.niche=niche;
        this.university=university;
    }
    public String getUniversity() {
    return this.university;
}
    public String getHeardFrom() {
    return this.heardFrom;
}
    public String getUsername(){
        return username;
    }
    public String getNiche() {
    return this.niche;
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
