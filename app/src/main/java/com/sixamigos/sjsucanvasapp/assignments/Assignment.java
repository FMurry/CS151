package com.sixamigos.sjsucanvasapp.assignments;

/**
 * Created by fredericmurry on 11/15/15.
 *
 *
 */
public class Assignment {

    private String name;// name of assignment
    private double points;// points received
    private double totalPoints;//total points assignment is worth
    private String description;
    private String dueDate;

    // CONTSTRUCTORS_______________________________________

    public Assignment() {
    }

    /**
     * Constructor including name and total points
     *
     * @param newName        Name of assignment
     * @param newTotalPoints Total points of assignment
     */
    public Assignment(String newName, double newTotalPoints) {
        name = newName;
        totalPoints = newTotalPoints;
    }

    /**
     * Constructor including name, description and total points
     *
     * @param newName        Name of assignment
     * @param newDescription Description of assignment
     * @param newTotalPoints Total points of assignment
     */
    public Assignment(String newName, String newDescription, double newTotalPoints) {
        name = newName;
        description = newDescription;
        totalPoints = newTotalPoints;
    }

    //Accessors____________________________________________

    /**
     * Access name of assignment
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Access total points of assignment
     *
     * @return total points
     */
    public double getTotalPoints() {
        return totalPoints;
    }

    /**
     * Access points of assignment
     *
     * @return points
     */
    public double getPoints() {
        return points;
    }

    /**
     * Access Description of assignment
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Access Due date of the assignment
     * @return
     */
    public String getDueDate() {
        return dueDate;
    }


    //Mutators_______________________________________________

    /**
     * Set name of assignment
     *
     * @param newName new name of assignment
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Set description of assignment
     *
     * @param newDescription new description of assignment
     */
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * Set points of assignment
     *
     * @param newPoints new amount of points
     */
    public void setPoints(double newPoints) {
        points = newPoints;
    }

    /**
     * Set total points of assignment
     *
     * @param newTotal new total amount of points
     */
    public void setTotalPoints(double newTotal) {
        totalPoints = newTotal;
    }

    /**
     * Set due date of assignment
     *
     * @param newDueDate
     */
    public void setDueDate(String newDueDate) {
        dueDate = newDueDate;
    }

    //Methods________________________________________________

    /**
     * Returns number grade. Ex. 90/100 = 90
     *
     * @return number grade
     */
    public double generateGrade() {
       if(totalPoints<=0){
         totalPoints = 1;
       }
        return (points/totalPoints)*100;
    }

    /**
     * Generates String grade if not yet graded points should be negative so - is displayed
     * @return Grade to be displayed
     */
    public String displayGrade() {
        if (totalPoints <= 0) {
            totalPoints = 1;
        }
        if (points < 0) {
            return "-";
        } else
            return String.valueOf((points / totalPoints) * 100);
    }
}
