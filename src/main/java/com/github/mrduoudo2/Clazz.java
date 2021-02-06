package com.github.mrduoudo2;

import java.util.ArrayList;
import java.util.HashMap;

public class Clazz {
    public boolean over =false;
    public boolean is_class = false;
    HashMap<Teacher,Integer> teacherCount = new HashMap<>();

    int id;
    String name;
    int begin;
    int end;

    ArrayList<Teacher> teachers;

    public Clazz(int id, String name, int begin, int end,ArrayList<Teacher> teachers) {
        this.id = id;
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.teachers = teachers;
    }

    public void put_lesson(Teacher teacher) {
        int count = 1;
        if (teacherCount.get(teacher) == null){
            teacherCount.put(teacher,count);
        }else {
            count = teacherCount.get(teacher);
            count++;
            teacherCount.replace(teacher,count);
        }
    }
}
