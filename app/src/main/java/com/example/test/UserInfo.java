package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    //TODO 유저 정보 더 뭐 필요하지?!!?
    private String name;
    private String email;
    private String country;


    private static final ArrayList<String> countryList;
    private static final Map<String, String> countryCodeMap;
    private static final UserInfo userInfo;//현재 사용자

    static {
        userInfo = new UserInfo();
        countryList = new ArrayList<>();
        countryCodeMap = new HashMap<>();
    }

    public enum CountryEnum {
        /*  TODO
        *   국가정보 추가(국가 고유 식별자 선택...!)
        */
        china("China", "CN") , japan("Japan", "JP"), korea("Republic Of Korea", "KR"), usa("USA", "US");
        private final String country;
        private final String countryCode;
        CountryEnum(String country, String countryCode) {
            this.country = country;
            this.countryCode = countryCode;

            setCountryList(country);
            setCountryCodeMap(country, countryCode);
        }
        public static String[] getCountryList() {return countryList.toArray(new String[0]);}
        private static void setCountryList(String in) {countryList.add(in);}
        private static void setCountryCodeMap(String key, String value) {countryCodeMap.put(key, value);}
        public String getCountry() {return country;}
        public String getCountryCode() {return countryCode;}
        public static String convertCountryToCountryCode(String in) {return countryCodeMap.get(in);}
    }


    public UserInfo(){
        this.name = null;
        this.email = null;
        this.country = null;
    }
    public UserInfo(String name, String email, String country){
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public static UserInfo getInstance(){return userInfo;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
