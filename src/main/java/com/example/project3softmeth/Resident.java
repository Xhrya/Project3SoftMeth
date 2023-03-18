/**
 * @author Shreya Pandey
 * @author Medhasri Veldurthi
 */
package com.example.project3softmeth;

public class Resident extends Student{
    private int scholarship;

    /**
     * Makes a resident
     *
     * @param p Student's profile with a student's full name and DOB
     * @param m Student's major
     * @param credits Student's number of credits
     * */
    public Resident(Profile p, Major m, int credits) {

        super(p, m, credits);
    }


    /**
     * Calculates a student's tuition based on their credits
     *
     * @param creditsEnrolled is the number of credits a student currently has
     * */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;

        if(creditsEnrolled>=3 && creditsEnrolled <=12)
        {
            tuition= creditsEnrolled *404;
            tuition = (3268 * 0.80) + tuition;
        }
        if(creditsEnrolled>12 && creditsEnrolled <=24)
        {
            tuition= 12536;
            tuition = tuition + 3268;

        } //6654

        return tuition;

    }


    /**
     * Returns true if the student is a resident
     * */
    @Override
    public boolean isResident() {
        return true;
    }

    /**
     * If the student is a resident, this assigns a scholarship
     * @param scholarship is the scholarship amount
     * */
    public void setScholarship(int scholarship){
        this.scholarship = scholarship;
    }

    public int getScholarship()
    {
        return scholarship;
    }
}