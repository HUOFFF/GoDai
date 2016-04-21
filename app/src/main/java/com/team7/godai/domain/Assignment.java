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

    public Assignment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Assignment(String destination, String dormitory, String money){
        super();
        this.destination=destination;
        this.dormitory=dormitory;
        this.money=money;
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

    @Override
    public String toString() {
        return "Assignment [assignment_id=" + assignment_id + ", destination=" + destination + ", dormitory="
                + dormitory +  ",money="+ money+"]";
    }
}
