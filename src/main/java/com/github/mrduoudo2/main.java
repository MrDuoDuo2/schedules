package com.github.mrduoudo2;

import java.util.*;

public class main {
    public static void main(String[] args) {
        //模拟添加课程
        ArrayList<Lesson> lessos = new ArrayList<>();
        lessos.add(new Lesson(1,"语文",5));
        lessos.add(new Lesson(2,"英文",5));

        //模拟添加老师
        ArrayList<Teacher> teacherArrayList = new ArrayList<>();
        teacherArrayList.add(new Teacher(1,"张三",35,1898989,"男",10,1));
        teacherArrayList.add(new Teacher(2,"李伟",24,11111111,"男",10,2));
        teacherArrayList.add(new Teacher(3,"张四",35,1898989,"男",10,1));
        teacherArrayList.add(new Teacher(4,"赵五",20,12321321,"女",20,2));


        ArrayList<Teacher> c1Teachers = new ArrayList<>();
        c1Teachers.add(teacherArrayList.get(0));
        c1Teachers.add(teacherArrayList.get(3));

        ArrayList<Teacher> c2Teachers = new ArrayList<>();
        c2Teachers.add(teacherArrayList.get(1));
        c2Teachers.add(teacherArrayList.get(2));


        //模拟添加班级
        ArrayList<Clazz> clazzHashMap = new ArrayList<>();
        clazzHashMap.add(new Clazz(1,"1831",2018,2022,c1Teachers));
        clazzHashMap.add(new Clazz(2,"1832",2018,2022,c1Teachers));
        clazzHashMap.add(new Clazz(3,"2121",2020,20201,c2Teachers));
        clazzHashMap.add(new Clazz(3,"2100",2020,20201,c2Teachers));

        //模拟添加教室
        ArrayList<Room> roomArrayList = new ArrayList<>();
        roomArrayList.add(new Room(1,"801"));
        roomArrayList.add(new Room(2,"802"));
        roomArrayList.add(new Room(2,"803"));

        int time = 0;
        while (true) {
            for (int i = 1; i < 6; i++) {
                for (Teacher teacher:teacherArrayList) {
                    teacher.tired = false;
                }

                for (int j = 1; j <10; j++) {
                    Collections.shuffle(teacherArrayList);
                    Collections.shuffle(clazzHashMap);

                    for (Teacher teacher : teacherArrayList) {
                        teacher.is_class = false;
                    }

                    for (Clazz clazz: clazzHashMap) {
                        clazz.is_class = false;
                    }

                    System.out.printf("day:%d,time:%d\n",i,j);

                    if (j == 5){
                        System.out.println("午休时间");
                    }else {
                        for (Room room : roomArrayList) {

                        Clazz clzz = null;

                        //排班级
                        for (Clazz clazz : clazzHashMap) {
                            if (!clazz.is_class) {
                                clzz = clazz;
                                clazz.is_class = true;
                                break;
                            }
                        }

                        if (clzz == null) {
                            break;
                        }

                        Teacher teacher = null;

                        int i1 = (int) (Math.random() * 2);

                        clzz.over = true;
                        for (Teacher t1 : clzz.teachers) {
                            if (!t1.over.contains(clzz)) {
                                clzz.over = false;
                                break;
                            }
                        }

                        if (i1 == 0 ) {
                            teacher = new Teacher(0, "", 0, 0, "",0,0);
                        }else {


                            for (int k = 0; k < clzz.teachers.size(); k++) {
                                /*
                                * 判断：
                                * 老师是否上课
                                * 这个班的这门是否排完
                                */

                                if (clzz.teachers.get(k).is_class || clzz.teachers.get(k).over.contains(clzz)) {
                                    teacher = new Teacher(0, "", 0, 0, "",0,0);
                                } else if (clzz.teachers.get(k).tired) {
                                    //判断老师的最后一节课的时间和当前时间差了是否一节课
                                    if (clzz.teachers.get(k).schedules.size() >= 2 && time - clzz.teachers.get(k).schedules.get(clzz.teachers.get(k).schedules.size() - 1) > 1) {
                                        clzz.teachers.get(k).tired = false;
                                    }

                                    teacher = new Teacher(0, "", 0, 0, "",0,0);
                                } else {
                                    teacher = clzz.teachers.get(k);
                                    teacher.is_class = true;
                                    clzz.put_lesson(teacher);
                                    teacher.schedules.add(time);

                                    //判断是否排完课程
                                    if (clzz.teacherCount.get(teacher) >= teacher.count) {
                                        clzz.teachers.get(k).over.add(clzz);
                                    }

                                    break;
                                }
                            }
                        }

                        Lesson lesson = null;


                        if (teacher != null) {
                            for (Lesson lesso : lessos)
                                if (lesso.id == teacher.lesson_id) {
                                    lesson = lesso;
                                    break;
                                } else {
                                    lesson = new Lesson(0, "", 0);
                                    teacher = new Teacher(0, "", 0, 0, "",0,0);
                                }

                            assert lesson != null;
                            System.out.printf("教室：%s，班级：%s，老师：%s,课程:%s%d\n", room.location, clzz.name, teacher.name, lesson.name, clzz.teacherCount.get(teacher));

                            if (teacher.schedules.size() >= 2 && (teacher.schedules.get(teacher.schedules.size() - 1) - teacher.schedules.get(teacher.schedules.size() - 2) <= 1)) {
                                teacher.tired = true;
                                System.out.printf("%s该休息了\n", teacher.name);
                            }

                        }
                    }
                    }
                    time++;
                }
                System.out.println("\n");
            }

            //判断是否所有班级都排完了课程
            boolean flag1 =true;
            for (Clazz clazz:clazzHashMap) {
                if (!clazz.over) {
                    flag1 = false;
                    break;
                }
            }

            if (flag1){
                break;
            }
        }
    }
}
