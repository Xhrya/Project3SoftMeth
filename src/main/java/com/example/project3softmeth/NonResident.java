/**
 * @author Shreya Pandey
 * @author Medhasri Veldurthi
 */
package com.example.project3softmeth;

public class NonResident extends Student{

    /**
     * return Default constructor of the NonResident class
     * @param p the profile
     * @param m Major
     * @param cc credits completed
     */
    public NonResident(Profile p, Major m, int cc)
    {
        super(p, m, cc);
    }

    /**
     * Calculate the tuitionDue for the nonresident student
     * @param creditsEnrolled is the number of credits the student currently has
     * @return the tuition, as a double
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;
        if(creditsEnrolled>=3 && creditsEnrolled <=12)
        {
            tuition= creditsEnrolled *966;
            tuition = (3268 * 0.80) + tuition;
        }
        if(creditsEnrolled>12 && creditsEnrolled <=24)
        {
            tuition= 29737;
            tuition = tuition + 3268;
        }

        return tuition;

    }

    /**
     * return if the student is a resident or not
     * @return true if the student is a resident
     */
    @Override
    public boolean isResident() {
        return false;
    }


}