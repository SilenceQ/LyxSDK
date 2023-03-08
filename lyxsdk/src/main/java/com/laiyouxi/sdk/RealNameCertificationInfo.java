package com.laiyouxi.sdk;

public class RealNameCertificationInfo {

    private String name = "";
    private String card = "";
    private String age = "";

    public RealNameCertificationInfo() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "RealNameCertification{" +
                "name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
