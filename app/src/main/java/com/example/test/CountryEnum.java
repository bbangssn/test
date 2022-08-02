package com.example.test;

public enum CountryEnum {
    /*  TODO
    *   국가정보 추가(국가 고유 식별자 선택...!)
    */

    korea("ko");

    private String country;

    CountryEnum(String country){
        this.country = country;
    }
}
