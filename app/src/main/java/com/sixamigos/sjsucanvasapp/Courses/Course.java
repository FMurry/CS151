package com.sixamigos.sjsucanvasapp.Courses;

/**
 * Created by Frederic Murry on 11/12/15.
 * Course Object
 *
 */
public class Course {

    private int id;
    private String courseName; //Ex: 151
    private String roomNumber; //Ex: DH 135
    private String fullName; //Ex: Object Oriented Programming
    private double grade;


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

    /**
     * Access the ID #
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * Access the course name
     * @return
     */
    public String getCourseName(){
        return courseName;
    }

    /**
     * Access the room number
     * @return
     */
    public String getRoomNumber(){
        return roomNumber;
    }

    /**
     * Access the grade
     * @return
     */
    public double getGrade(){ return grade;}


    //Mutators_______________________________________________

    /**
     * Set the course name (different from full name) Ex CS 151
     * @param newCourseName
     */
    public void setCourseName(String newCourseName){ courseName = newCourseName;
    }

    /**
     * Sets the room number of course
     * @param newRoomNumber
     */
    public void setRoomNumber(String newRoomNumber){
        roomNumber = newRoomNumber;
    }

    /**
     * Sets the full name of Course
     * @param newFullName
     */
    public void setFullName(String newFullName){
        fullName = newFullName;
    }

    /**
     * Sets the grade of course
     * @param newGrade
     */
    public void setGrade(double newGrade) { grade = newGrade;}


    //Methods_________________________
    /**
     * Returns letter grade of Course
     * @return
     */
    public String getLetterGrade(){
        throw  new UnsupportedOperationException("Letter Grade not yet supported");
    }




}
