package com.github.mrduoudo2;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher {
    public boolean tired = false;
    public boolean is_class = false;

    int id;
    String name;
    int arg;
    int phone;
    String sex;
    int count;
    public int lesson_id;

    ArrayList<Clazz> over = new ArrayList<>();
    ArrayList<Integer> schedules = new ArrayList<>();

    public Teacher(int id, String name, int arg, int phone, String sex,int count,int lesson_id) {
        this.id = id;
        this.name = name;
        this.arg = arg;
        this.phone = phone;
        this.sex = sex;
        this.count = count;
        this.lesson_id =lesson_id;
    }
}
