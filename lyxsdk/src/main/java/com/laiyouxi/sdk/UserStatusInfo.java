package com.laiyouxi.sdk;

public class UserStatusInfo {

    private int age;
    private boolean status;
    private int requireAge;

    public UserStatusInfo() {}

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRequireAge() {
        return this.requireAge;
    }

    public void setRequireAge(int requireAge) {
        this.requireAge = requireAge;
    }

    @Override
    public String toString() {
        return "UserStatusModel{" +
                "age=" + age +
                ", status=" + status +
                ", requireAge=" + requireAge +
                '}';
    }
}
