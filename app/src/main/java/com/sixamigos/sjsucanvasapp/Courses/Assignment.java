package com.sixamigos.sjsucanvasapp.Courses;

/**
 * Created by fredericmurry on 11/15/15.
 */
public class Assignment {

    private String name;// name of assignment
    private double points;// points received
    private double totalPoints;//total points assignment is worth
    private String description;

    // CONTSTRUCTORS_______________________________________

    public Assignment(){}

    /**
     * Constructor including name and total points
     * @param newName Name of assignment
     * @param newTotalPoints Total points of assignment
     */
    public Assignment(String newName, double newTotalPoints){
        name = newName;
        totalPoints = newTotalPoints;
    }

    /**
     * Constructor including name, description and total points
     * @param newName Name of assignment
     * @param newDescription Description of assignment
     * @param newTotalPoints Total points of assignment
     */
    public Assignment(String newName, String newDescription, double newTotalPoints ){
        name = newName;
        description = newDescription;
        totalPoints = newTotalPoints;
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

    //Methods________________________________________________

    /**
     * Returns number grade. Ex. 90/100 = 90
     * @return number grade
     */
    public int generateGrade(){
        return (int)((points/totalPoints)*100);
    }

}
