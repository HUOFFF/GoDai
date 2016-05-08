package com.team7.godai.domain;

import java.io.Serializable;

/**
 * Created by mm on 2016/4/21.
 */
public class Assignment implements Serializable {
    private int assignment_id;
    private String destination;
    private String dormitory;
    private String money;
    private String status;
    private String user;
    private String re_user;
    private String re_OR_not;

    public Assignment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Assignment(String destination, String dormitory, String money){
        super();
        this.destination=destination;
        this.dormitory=dormitory;
        this.money=money;
        this .status = status;
        this.user = user;
    }

    public int getAssignment_id() {return assignment_id;}
    public void setAssignment_id(int id) {this.assignment_id = id;}
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDormitory() {return dormitory;}
    public void setDormitory(String dormitory) { this.dormitory = dormitory;}
    public String getMoney() {return money;}
    public void setMoney(String money) {this.money = money;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}
    public String getRe_User() {return re_user;}
    public void setRe_User(String re_user) {this.re_user = re_user;}
    public String getre_OR_not() {return re_OR_not;}
    public void setre_OR_not(String re_OR_not) {this.re_OR_not = re_OR_not;}


}
