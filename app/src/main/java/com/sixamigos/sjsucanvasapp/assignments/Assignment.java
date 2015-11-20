package com.sixamigos.sjsucanvasapp.assignments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fredericmurry on 11/15/15.
 */
public class Assignment {

    private String name;// name of assignment
    private double points;// points received
    private double totalPoints;//total points assignment is worth
    private String description;
    private SimpleDateFormat dateFormat;
    private Date dateDue;// If Date is needed

    public static final String format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    // CONTSTRUCTORS_______________________________________

    public Assignment(){}

    /**
     * Assignment Constructor (Name, Total Points)
     * @param newName Name of assignment
     * @param newTotalPoints Total points of assignment
     */
    public Assignment(String newName, double newTotalPoints){
        name = newName;
        totalPoints = newTotalPoints;
        points = -1.00;
    }

    /**
     * Assignment Constructor (Name,Description,Total Points, Date(String))
     * @param newName Name of assignment
     * @param newDescription Description of assignment
     * @param newTotalPoints Total points of assignment
     */
    public Assignment(String newName, String newDescription, double newTotalPoints, String newDate) throws ParseException {
        name = newName;
        description = newDescription;
        totalPoints = newTotalPoints;
        points = -1.00;
    }

    /**
     * Assignment Constructor (Name,Description,Total Points, Date, Points Earned)
     * @param newName Name of assignment
     * @param newDescription Description of assignment
     * @param newTotalPoints Total points of assignment
     */
    public Assignment(String newName, String newDescription, double newTotalPoints, String newDate, double newPoints){
        name = newName;
        description = newDescription;
        totalPoints = newTotalPoints;
        points = newPoints;

    }

    //Accessors____________________________________________

    /**
     * Access name of assignment
     * @return name
     */
    public String getName(){return name;}

    /**
     * Access total points of assignment
     * @return total points
     */
    public double getTotalPoints(){return totalPoints;}

    /**
     * Access points of assignment
     * @return points
     */
    public double getPoints(){return points;}

    /**
     * Access Description of assignment
     * @return description
     */
    public String getDescription(){return description;}


    //Mutators_______________________________________________

    /**
     * Set name of assignment
     * @param newName new name of assignment
     */
    public void setName(String newName){name = newName;}

    /**
     * Set description of assignment
     * @param newDescription new description of assignment
     */
    public void setDescription(String newDescription){description = newDescription;}

    /**
     * Set points of assignment
     * @param newPoints new amount of points
     */
    public void setPoints(double newPoints){points = newPoints;}

    /**
     * Set total points of assignment
     * @param newTotal new total amount of points
     */
    public void setTotalPoints(double newTotal){totalPoints = newTotal;}

    public void setDateDue(Date newDate){

    }

    //Methods________________________________________________

    /**
     * Returns number grade with %. Ex. 90/100 = 90% if points is negative returns "-"
     * @return number grade
     */
    public String generateGrade(){
        if(points>=0){
            return "-";
        }
        else{
            if(totalPoints == 0){
                totalPoints = 1;
            }
            return String.valueOf((int)((points/totalPoints)*100))+"%";
        }

    }

    public Date genereateDate(String strDate){
        Date date = new Date();
        //Convert String following format into Date
        return date;
    }
}
