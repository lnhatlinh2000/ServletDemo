package com.example.main;

import com.example.util.HibernateUtils;

public class Application {

    public static void main(String[] args) {
        HibernateUtils.getSessionFactory();
    }
}
