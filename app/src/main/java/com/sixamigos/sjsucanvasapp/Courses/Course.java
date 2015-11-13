package com.sixamigos.sjsucanvasapp.Courses;

/**
 * Created by fredericmurry on 11/12/15. l
 *
 */
public class Course {

    private int id;
    private String courseName; //Ex: 151
    private String roomNumber; //Ex: DH 135
    private String fullName; //Ex: Object Oriented Programming

    //private List<Assignments> assignments;


    // CONTSTRUCTORS_______________________________________

    public Course(){

    }

    public Course(String courseName,String roomNumber){
        this.courseName = courseName;
        this.roomNumber = roomNumber;
    }

    public Course(String courseName,String roomNumber, String fullName){
        this.courseName = courseName;
        this.roomNumber = roomNumber;
        this.fullName = fullName;
    }

    //Accessors____________________________________________
    public int getId(){
        return id;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getRoomNumber(){
        return roomNumber;
    }


    //Mutators_______________________________________________
    public void setCourseName(String newCourseName){
        courseName = newCourseName;
    }

    public void setRoomNumber(String newRoomNumber){
        roomNumber = newRoomNumber;
    }

    public void setFullName(String newFullName){
        fullName = newFullName;
    }

}
