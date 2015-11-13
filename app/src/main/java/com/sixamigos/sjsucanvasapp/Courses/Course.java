package com.sixamigos.sjsucanvasapp.Courses;

/**
 * Created by fredericmurry on 11/12/15. l
 *
 */
public class Course {

    private int id;
    private String courseName; //Ex: 151
    private String fullName; //Ex: Object Oriented Programming
    private String roomNumber; //Ex: DH 135


    public Course(){

    }

    public Course(String courseName,String roomNumber){
        this.courseName = courseName;
        this.roomNumber = roomNumber;
    }

}
