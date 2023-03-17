/**
 * @author Shreya Pandey
 * @author Medhasri Veldurthi
 */
package com.example.project3softmeth;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.stream.Stream;


import java.util.Scanner;

public class TuitionManagerController {
    Roster newRoster = new Roster();
    Enrollment enrollmentList = new Enrollment();
    @FXML
    TextArea vbMenu;
    @FXML
    ToggleGroup residentButtons;
    @FXML
    ToggleGroup regionButtons;
    @FXML
    ToggleGroup majorButtons;
    @FXML
    TextField firstNameRoster;
    @FXML
    TextField lastNameRoster;
    @FXML
    TextField creditsCompletedRoster;
    @FXML
    DatePicker birthDate;
    @FXML
    RadioButton isAbroad;
    @FXML
    TextField firstNameEnroll;
    @FXML
    TextField lastNameEnroll;
    @FXML
    TextField creditsEnroll;
    @FXML
    DatePicker birthDateEnroll;
    @FXML
    TextField firstNameScholarship;
    @FXML
    TextField lastNameScholarship;
    @FXML
    DatePicker birthDateScholarship;
    @FXML
    TextField amountScholarship;
    @FXML
    TextArea firstNamePrint;
    @FXML
    TextArea lastNamePrint;
    @FXML
    TextArea creditsPrints;
    @FXML
    TextArea schoolPrint;
    @FXML
    TextArea majorPrint;
    @FXML
    TextArea dobPrint;
    @FXML
    TextArea standingPrint;
    @FXML
    TextArea tuitionDuePrint;
    @FXML
    FileChooser uploadFile;


    //everything for Enroll/Drop
    @FXML
    /**
     * Adds a student to the roster by checking first if they are resident, nonresident, tristate or international
     * @param e is the event where the user clicks "add"
     */
    protected void onAddButtonClick(Event e) {
       //converting the toggle buttons of resident and major into strings
        if(residentButtons.getSelectedToggle() == null){
            vbMenu.appendText("Please specify your residency." + "\n"+ "\n");
            return;
        }
        String residency = residentButtons.getSelectedToggle().toString();
        residency = residency.substring(residency.indexOf("'") +1, residency.length() -1);

        if(majorButtons.getSelectedToggle() == null){
            vbMenu.appendText("Please specify your major." + "\n"+ "\n");
            return;
        }
        String major = majorButtons.getSelectedToggle().toString();
        major = major.substring(major.indexOf("'") +1, major.length() -1);


        //delete this later!
        //vbMenu.appendText(residentButtons.getSelectedToggle().toString());
//        vbMenu.appendText("STATUS:"+residency + "\n");
//        //vbMenu.appendText("Major: " + majorButtons.getSelectedToggle().toString());
//        vbMenu.appendText("MAJOR:" + major+ "\n");
//        vbMenu.appendText("FirstName:" + firstNameRoster.getText()+ "\n");
//        vbMenu.appendText("Lastname :" + lastNameRoster.getText()+ "\n");
//        vbMenu.appendText("Credits completed:"+ creditsCompletedRoster.getText() + "\n");
//        vbMenu.appendText("Birthdate :" + birthDate.getValue() + "\n");
//        String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
//        vbMenu.appendText(dob);
        //delete this later^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


        if(residency.equals("Resident")) {
            //NEED AN EXCEPTION TO MAKE SURE ALL VALUES ARE ENTERED AND NONE OF THE FIELDS ARE MISSING
            String firstName = firstNameRoster.getText();
            if(firstName.length()<1){
                vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
                return;
            }
            String lastName = lastNameRoster.getText();
            if(lastName.length()<1){
                vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
                return;
            }

            if(birthDate.getValue() == null){
                vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
                return;
            }
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);

            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                vbMenu.appendText("Credits completed invalid: not an integer!" + "\n"+ "\n");
                return;
            }
            //current date + checking if valid
            Date d = new Date(dob);
            boolean isValid = d.isValid();

            //making a student profile and age is valid
            Profile thisStudent = new Profile(lastName, firstName, d);
            int age = thisStudent.getAge();

            //checking for valid DOB + age, adding an ENUM if this exists
            if (age > 16) {
                if (isValid) {
                    Major studentMajorEnum = Major.valueOf(major);
                    //making sure credits are positive
                    if (credits >= 0) {
                        //checking if student is in the roster, then adding student meeting all requirements
                        Resident newResident = new Resident(thisStudent, studentMajorEnum, credits);
                        if (!newRoster.contains(newResident)) {
                            newRoster.add(newResident);
                            vbMenu.appendText(thisStudent.toString() + " added to the roster."+ "\n"+ "\n");
                        } else {
                            vbMenu.appendText(thisStudent.toString() + " is already in the roster."+ "\n"+ "\n");
                        }
                    } else {
                        vbMenu.appendText("Credits completed invalid: cannot be negative!"+ "\n"+ "\n");
                    }
                } else {
                    vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old."+ "\n");
            }
        }else if(residency.equals("NonResident")) {
//            vbMenu.appendText("Nonresident entered"+ "\n");
            //NEED AN EXCEPTION TO MAKE SURE ALL VALUES ARE ENTERED AND NONE OF THE FIELDS ARE MISSING
            String firstName = firstNameRoster.getText();
            if(firstName.length()<1){
                vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
                return;
            }
            String lastName = lastNameRoster.getText();
            if(lastName.length()<1){
                vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
                return;
            }
            if(birthDate.getValue() == null){
                vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
                return;
            }
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                vbMenu.appendText("Credits completed invalid: not an integer!"+ "\n");
                return;
            }
            //current date + checking if valid
            Date d = new Date(dob);
            boolean isValid = d.isValid();

            //making a student profile and age is valid
            Profile thisStudent = new Profile(lastName, firstName, d);
            int age = thisStudent.getAge();

            //checking for valid DOB + age, adding an ENUM if this exists
            if (age > 16) {
                if (isValid) {
                        Major studentMajorEnum = Major.valueOf(major);
                        //making sure credits are positive
                        if (credits >= 0) {
                            //checking if student is in the roster, then adding student meeting all requirements
                            Resident newNonResident = new Resident(thisStudent, studentMajorEnum, credits);
                            if (!newRoster.contains(newNonResident)) {
                                newRoster.add(newNonResident);
                                vbMenu.appendText(thisStudent.toString() + " added to the roster."+ "\n");
                            } else {
                                vbMenu.appendText(thisStudent.toString() + " is already in the roster."+ "\n");
                            }
                        } else {
                            vbMenu.appendText("Credits completed invalid: cannot be negative!"+ "\n");
                        }
                } else {
                    vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old."+ "\n");
            }

        } else if(residency.equals("Tristate")) {
//            vbMenu.appendText("triSTATE entered"+ "\n");
            String firstName = firstNameRoster.getText();
            if(firstName.length()<1){
                vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
                return;
            }
            String lastName = lastNameRoster.getText();
            if(lastName.length()<1){
                vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
                return;
            }
            if(birthDate.getValue() == null){
                vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
                return;
            }
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);

            if(regionButtons.getSelectedToggle() == null){
                vbMenu.appendText("Please specify your state." + "\n"+ "\n");
                return;
            }
            String state = (regionButtons.getSelectedToggle().toString());
            state = state.substring(state.indexOf("'") +1, state.length() -1);
            vbMenu.appendText("STATE:" + state);

            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                vbMenu.appendText("Credits completed invalid: not an integer!"+ "\n");
                return;
            }
            //current date + checking if valid
            Date d = new Date(dob);
            boolean isValid = d.isValid();

            //making a student profile and age is valid
            Profile thisStudent = new Profile(lastName, firstName, d);
            int age = thisStudent.getAge();

            //checking for valid DOB + age, adding an ENUM if this exists
            if (age > 16) {
                if (isValid) {
                        Major studentMajorEnum = Major.valueOf(major);
                        //making sure credits are positive
                        if (credits >= 0) {
                            //checking if student is in the roster, then adding student meeting all requirements
                            TriState newTriState = new TriState(thisStudent, studentMajorEnum, credits, state);
                            if (!(newRoster.contains(newTriState))) {
                                newRoster.add(newTriState);
                                //can create an instance of the bigger object and assign a smaller object to it

                                vbMenu.appendText(thisStudent.toString() + " added to the roster."+ "\n");
                            } else {
                                vbMenu.appendText(thisStudent.toString() + " is already in the roster."+ "\n");
                            }
                        } else {
                            vbMenu.appendText("Credits completed invalid: cannot be negative!"+ "\n");
                        }
                } else {
                    vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old."+ "\n");
            }
        } else if(residency.equals("International")) {
//            vbMenu.appendText("international entered"+ "\n");
            String firstName = firstNameRoster.getText();
            if(firstName.length()<1){
                vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
                return;
            }
            String lastName = lastNameRoster.getText();
            if(lastName.length()<1){
                vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
                return;
            }
            if(birthDate.getValue() == null){
                vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
                return;
            }
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
            boolean abroad = isAbroad.isSelected();

            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                vbMenu.appendText("Credits completed invalid: not an integer!"+ "\n");
                return;
            }
            //current date + checking if valid
            Date d = new Date(dob);
            boolean isValid = d.isValid();

            //making a student profile and age is valid
            Profile thisStudent = new Profile(lastName, firstName, d);
            int age = thisStudent.getAge();

            if (age > 16) {
                if (isValid) {
                        Major studentMajorEnum = Major.valueOf(major);
                        //making sure credits are positive
                        if (credits >= 0) {
                            //checking if student is in the roster, then adding student meeting all requirements
                            International i = new International(thisStudent, studentMajorEnum, credits, abroad);
                            if (!newRoster.contains(i)) {
                                newRoster.add(i);
                                vbMenu.appendText(thisStudent.toString() + " added to the roster."+ "\n");
                            } else {
                                vbMenu.appendText(thisStudent.toString() + " is already in the roster."+ "\n");
                            }
                        } else {
                            vbMenu.appendText("Credits completed invalid: cannot be negative!"+ "\n");
                        }
                } else {
                    vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old."+ "\n");
            }


        }

    }


    @FXML
    /**
     * Drops a student to the roster
     * @param e is the event where the user clicks "Drop"
     */
    protected void onDropButtonClick(Event e) {
        String firstName = firstNameRoster.getText();
        if(firstName.length()<1){
            vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
            return;
        }
        String lastName = lastNameRoster.getText();
        if(lastName.length()<1){
            vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
            return;
        }
        if(birthDate.getValue() == null){
            vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
            return;
        }
        String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);

        int credits = 0;
        try{
            credits = Integer.parseInt((creditsCompletedRoster.getText()));
        } catch( NumberFormatException creditE) {
            vbMenu.appendText("Credits completed invalid: not an integer!"+ "\n");
            return;
        }

        //making the Date, Profile, and Student
        Date RMDate = new Date(dob);
        Profile RMProfile = new Profile(lastName, firstName, RMDate);
        Student RMStudent = newRoster.ProfileToStudent(RMProfile);
        EnrollStudent testingContainsEnrollment = new EnrollStudent(RMProfile, credits);
        if(enrollmentList.contains(testingContainsEnrollment))
        {
            vbMenu.appendText("Student is currently in the enrollment list, therefore cannot be removed");
            return;
        }
        boolean isRemoved = newRoster.remove(RMStudent);
        if (isRemoved) {
            vbMenu.appendText(RMProfile.toString() + " removed from the roster."+ "\n");
        } else {
            vbMenu.appendText(RMProfile.toString() + " is not in the roster."+ "\n");
        }
    }

    @FXML
    /**
     * Changes a student's major on the roster
     * @param e is the event where the user clicks "Change Major"
     */
    protected void onChangeMajorClick(Event e) {
        if(majorButtons.getSelectedToggle() == null){
            vbMenu.appendText("Please specify your major." + "\n"+ "\n");
            return;
        }
        String major = majorButtons.getSelectedToggle().toString();
        major = major.substring(major.indexOf("'") +1, major.length() -1);

        String firstName = firstNameRoster.getText();
        if(firstName.length()<1){
            vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
            return;
        }

        String lastName = lastNameRoster.getText();
        if(lastName.length()<1){
            vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
            return;
        }
        if(birthDate.getValue() == null){
            vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
            return;
        }
        String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);

        //making the Date & Profile
        Date CDate = new Date(dob);
        Profile CProfile = new Profile(lastName, firstName, CDate);
        Major MajorEnum = Major.valueOf(major);

        //Changing the major of the student
        boolean isChanged = newRoster.ChangeMajor(CProfile, MajorEnum);
        if (isChanged) {
            vbMenu.appendText(CProfile.toString() + " major changed to " + major.toString());
        } else {
            vbMenu.appendText("Student not found"+ "\n");
        }
    }

    @FXML
    /**
     * Adds, students given a file
     * @param e is the event where the user clicks "Change Major"
     */
    protected void onLoadFromFile(Event e) throws FileNotFoundException {//attach to scene builder
        uploadFile = new FileChooser();
        File f = uploadFile.showOpenDialog(null);
        if (f != null) {
            FileReader fileReader = new FileReader(f);
            try (BufferedReader br = new BufferedReader(fileReader)) {
                String line;
                while ((line = br.readLine()) != null) {

                    if(line.charAt(0) == 'N')
                    {
                        line = line.substring(1, line.length()-1);
                        fileAddNonResident(line);
                    }
                    else if(line.charAt(0) == 'R')
                    {
                        line = line.substring(1, line.length()-1);
                        fileAddResident(line);
                    }
                    else if(line.charAt(0) == 'I')
                    {
                        line = line.substring(1, line.length()-1);

                        fileAddTristate(line);

                    }
                    else if(line.charAt(0) == 'T')
                    {
                        line = line.substring(1, line.length()-1);

                        fileAddInternational(line);

                    }

                }
                vbMenu.appendText("File succesfully uploaded \n");

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void fileAddResident(String line)
    {
        StringTokenizer st = new StringTokenizer(line, ",");
        if(st.countTokens() <=4)
        {
            vbMenu.appendText("Missing data in line command." + "\n"+ "\n");
            return;
        }

        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dob = st.nextToken();
        String major = st.nextToken().toUpperCase();
        int credits = 0;
        try{
            credits = Integer.parseInt((st.nextToken()));
        }
        catch( NumberFormatException e)
        {
            vbMenu.appendText("Credits completed invalid" + "\n"+ "\n");
            return;
        }

        //current date + checking if valid
        Date d = new Date(dob);
        boolean isValid = d.isValid();

        //making a student profile and age is valid
        Profile thisStudent = new Profile(lastName, firstName, d);
        int age = thisStudent.getAge();

        //checking if major exists
        boolean containsMajor = false;
        String[] majorList = {"CS", "EE", "ITI", "BAIT", "MATH"};
        for (int i = 0; i < majorList.length; i++) {
            if (majorList[i].equals(major)) {
                containsMajor = true;
            }
        }

        //checking for valid DOB + age, adding an ENUM if this exists
        if (age > 16) {
            if (isValid) {
                if (containsMajor) {
                    Major studentMajorEnum = Major.valueOf(major);
                    //making sure credits are positive
                    if (credits >= 0) {
                        //checking if student is in the roster, then adding student meeting all requirements
                        Resident newResident = new Resident(thisStudent, studentMajorEnum, credits);
                        if (!newRoster.contains(newResident)) {
                            newRoster.add(newResident);
                            vbMenu.appendText(thisStudent.toString() + " added to the roster." + "\n"+ "\n");
                        } else {
                            vbMenu.appendText(thisStudent.toString() + " is already in the roster." + "\n"+ "\n");
                        }
                    } else {
                        vbMenu.appendText("Credits completed invalid: cannot be negative!" + "\n"+ "\n");
                    }
                } else {
                    vbMenu.appendText("Major code invalid:" + major.toString() + "\n"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!" + "\n"+ "\n");
            }
        } else {
            vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old." + "\n"+ "\n");
        }
    }

    private void fileAddNonResident(String line)
    {
        StringTokenizer st = new StringTokenizer(line, ",");
        if(st.countTokens() <=4)
        {
            vbMenu.appendText("Missing data in line command." + "\n"+ "\n");
            return;
        }


        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dob = st.nextToken();
        String major = st.nextToken().toUpperCase();
        int credits = Integer.parseInt((st.nextToken()));

        //current date + checking if valid
        Date d = new Date(dob);
        boolean isValid = d.isValid();

        //making a student profile and age is valid
        Profile thisStudent = new Profile(lastName, firstName, d);
        int age = thisStudent.getAge();

        String[] majorList = {"CS", "EE", "ITI", "BAIT", "MATH"};
        //checking if major exists
        boolean containsMajor = false;
        for (int i = 0; i < majorList.length; i++) {
            if (majorList[i].equals(major)) {
                containsMajor = true;
            }
        }

        //checking for valid DOB + age, adding an ENUM if this exists
        if (age > 16) {
            if (isValid) {
                if (containsMajor) {
                    Major studentMajorEnum = Major.valueOf(major);
                    //making sure credits are positive
                    if (credits >= 0) {
                        //checking if student is in the roster, then adding student meeting all requirements
                        Resident newNonResident = new Resident(thisStudent, studentMajorEnum, credits);
                        if (!newRoster.contains(newNonResident)) {
                            newRoster.add(newNonResident);
                            vbMenu.appendText(thisStudent.toString() + " added to the roster." + "\n"+ "\n");
                        } else {
                            vbMenu.appendText(thisStudent.toString() + " is already in the roster." + "\n"+ "\n");
                        }
                    } else {
                        vbMenu.appendText("Credits completed invalid: cannot be negative!" + "\n"+ "\n");
                    }
                } else {
                    vbMenu.appendText("Major code invalid:" + major.toString() + "\n"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!" + "\n"+ "\n");
            }
        } else {
            vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old." + "\n"+ "\n");
        }

    }

    private void fileAddTristate(String line)
    {
        StringTokenizer st = new StringTokenizer(line, ",");
        String[] majorList = {"CS", "EE", "ITI", "BAIT", "MATH"};

        if(st.countTokens() <=4)
        {
            vbMenu.appendText("Missing data in line command." + "\n"+ "\n");
            return;
        }

        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dob = st.nextToken();
        String major = st.nextToken().toUpperCase();
        int credits = Integer.parseInt((st.nextToken()));

        String state = "";
        try{
            state = st.nextToken().toUpperCase();
        }
        catch( NoSuchElementException e)
        {
            vbMenu.appendText("Missing state code." + "\n"+ "\n");
            return;
        }


        if(! (state.equals("CT") || state.equals("NY")))
        {
            vbMenu.appendText(state + ": Invalid state code"+ "\n"+ "\n");
            return;
        }

        //current date + checking if valid
        Date d = new Date(dob);
        boolean isValid = d.isValid();

        //making a student profile and age is valid
        Profile thisStudent = new Profile(lastName, firstName, d);
        int age = thisStudent.getAge();

        //checking if major exists
        boolean containsMajor = false;
        for (int i = 0; i < majorList.length; i++) {
            if (majorList[i].equals(major)) {
                containsMajor = true;
            }
        }

        //checking for valid DOB + age, adding an ENUM if this exists
        if (age > 16) {
            if (isValid) {
                if (containsMajor) {
                    Major studentMajorEnum = Major.valueOf(major);
                    //making sure credits are positive
                    if (credits >= 0) {

                        //checking if student is in the roster, then adding student meeting all requirements
                        TriState newTriState = new TriState(thisStudent, studentMajorEnum, credits, state);
                        if (!(newRoster.contains(newTriState))) {
                            newRoster.add(newTriState);
                            //can create an instance of the bigger object and assign a smaller object to it
                            vbMenu.appendText(thisStudent.toString() + " added to the roster." + "\n"+ "\n");
                        } else {
                            vbMenu.appendText(thisStudent.toString() + " is already in the roster." + "\n"+ "\n");
                        }
                    } else {
                        vbMenu.appendText("Credits completed invalid: cannot be negative!" + "\n"+ "\n");
                    }
                } else {
                    vbMenu.appendText("Major code invalid:" + major.toString() + "\n"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!" + "\n"+ "\n");
            }
        } else {
            vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old." + "\n"+ "\n");
        }
    }

    private void fileAddInternational(String line)
    {
        StringTokenizer st = new StringTokenizer(line, ",");
        String[] majorList = {"CS", "EE", "ITI", "BAIT", "MATH"};

        if(st.countTokens() <=5)
        {
           vbMenu.appendText("Missing data in line command." + "\n"+ "\n");
            return;
        }

        String firstName = st.nextToken();
        String lastName = st.nextToken();
        String dob = st.nextToken();
        String major = st.nextToken().toUpperCase();
        int credits = Integer.parseInt((st.nextToken()));
        boolean abroad = Boolean.parseBoolean((st.nextToken()));

        //current date + checking if valid
        Date d = new Date(dob);
        boolean isValid = d.isValid();

        //making a student profile and age is valid
        Profile thisStudent = new Profile(lastName, firstName, d);
        int age = thisStudent.getAge();

        //checking if major exists
        boolean containsMajor = false;
        for (int i = 0; i < majorList.length; i++) {
            if (majorList[i].equals(major)) {
                containsMajor = true;
            }
        }

        //checking for valid DOB + age, adding an ENUM if this exists
        if (age > 16) {
            if (isValid) {
                if (containsMajor) {
                    Major studentMajorEnum = Major.valueOf(major);
                    //making sure credits are positive
                    if (credits >= 0) {
                        //checking if student is in the roster, then adding student meeting all requirements
                        International i = new International(thisStudent, studentMajorEnum, credits, abroad);
                        if (!newRoster.contains(i)) {
                            newRoster.add(i);
                            vbMenu.appendText(thisStudent.toString() + " added to the roster.");
                        } else {
                            vbMenu.appendText(thisStudent.toString() + " is already in the roster." + "\n"+ "\n");
                        }
                    } else {
                        vbMenu.appendText("Credits completed invalid: cannot be negative!" + "\n"+ "\n");
                    }
                } else {
                    vbMenu.appendText("Major code invalid:" + major.toString() + "\n"+ "\n");
                }
            } else {
                vbMenu.appendText("DOB invalid: " + dob.toString() + " not a valid calendar date!" + "\n"+ "\n");
            }
        } else {
            vbMenu.appendText("DOB invalid: " + dob.toString() + " is younger than 16 years old." + "\n"+ "\n");
        }

    }



    @FXML
    /**
     * Enrolls a student
     * @param e is the event where the user clicks "Drop"
     */
    protected void onEnrollClick(Event e)
    {
        String firstName = firstNameEnroll.getText();
        if(firstName.length()<1){
            vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
            return;
        }

        String lastName = lastNameEnroll.getText();
        if(lastName.length()<1){
            vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
            return;
        }
        if(birthDateEnroll.getValue() == null){
            vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
            return;
        }
        String d = birthDateEnroll.getValue().toString().substring(5 , 7) + "/" + birthDateEnroll.getValue().toString().substring(8 , 10) + "/" + birthDateEnroll.getValue().toString().substring(0 , 4);

        Date dob = new Date(d);
        int credits = 0;
        try{
            credits = Integer.parseInt(creditsEnroll.getText());
        }
        catch(NumberFormatException g)
        {
            vbMenu.appendText("Credits completed invalid: not an integer!"+ "\n");
            return;
        }

        boolean isValid = dob.isValid();
        Profile enrollProfile = new Profile(lastName, firstName, dob);

        //making enrollStudent and adding/updating credits:

        EnrollStudent newEnroll = new EnrollStudent(enrollProfile, credits);
        if(enrollmentList.contains(newEnroll))
        {
            enrollmentList.updateCredits(newEnroll, credits);
        }
        else
        {
            enrollmentList.add(newEnroll);
            vbMenu.appendText(enrollProfile.toString() + " enrolled " + credits + " credits"+ "\n");
        }
    }


    @FXML
    /**
     * Removes a student from enrollment
     * @param e is the event where the user clicks "Drop"
     */
    protected void onDropClick(Event e)
    {
        String firstName = firstNameEnroll.getText();
        if(firstName.length()<1){
            vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
            return;
        }

        String lastName = lastNameEnroll.getText();
        if(lastName.length()<1){
            vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
            return;
        }
        if(birthDateEnroll.getValue() == null){
            vbMenu.appendText("Please specify a date of birth" + "\n"+ "\n");
            return;
        }
        String d = birthDateEnroll.getValue().toString().substring(5 , 7) + "/" + birthDateEnroll.getValue().toString().substring(8 , 10) + "/" + birthDateEnroll.getValue().toString().substring(0 , 4);
        Date dob = new Date(d);

        int credits = 0;
        try{
            credits = Integer.parseInt(creditsEnroll.getText());
        }
        catch(NumberFormatException n)
        {
            vbMenu.appendText("Credits completed invalid: not an integer!"+ "\n");
            return;
        }

        boolean isValid = dob.isValid();
        Profile enrollProfile = new Profile(lastName, firstName, dob);

        //making enrollStudent and adding/updating credits:

        EnrollStudent newEnroll = new EnrollStudent(enrollProfile, credits);
        if(enrollmentList.contains(newEnroll))
        {
            enrollmentList.remove(newEnroll);
            vbMenu.appendText(enrollProfile.toString() + " dropped."+ "\n");
        }
        else
        {
            vbMenu.appendText(enrollProfile.toString() + "is not enrolled "+ "\n");
        }

    }
    @FXML
    /**
     * Updates a student's scholarship amount
     * @param e is the event where the user clicks "Update Scholarship"
     */
    protected void onUpdateScholarshipAmountClick(Event e)
    {
        String firstName = firstNameEnroll.getText();
        if(firstName.length()<1){
            vbMenu.appendText("Please enter a first name." + "\n"+ "\n");
            return;
        }

        String lastName = lastNameEnroll.getText();
        if(lastName.length()<1){
            vbMenu.appendText("Please enter a last name." + "\n"+ "\n");
            return;
        }
        if(birthDateScholarship.getValue() == null){
            vbMenu.appendText("Please specify a date of birth." + "\n"+ "\n");
            return;
        }
        String d = birthDateScholarship.getValue().toString().substring(5 , 7) + "/" + birthDateScholarship.getValue().toString().substring(8 , 10) + "/" + birthDateScholarship.getValue().toString().substring(0 , 4);
        Date dob = new Date(d);
        int amount = 0;
        try{
            amount = Integer.parseInt(amountScholarship.getText());
        }
        catch(NumberFormatException f)
        {
            vbMenu.appendText("Amount awarded invalid: not an integer!"+ "\n");
            return;
        }

        Profile findRes = new Profile(lastName, firstName, dob); //create a new Profile for the student
        Resident r = newRoster.findResident(findRes); //only residents have scholarship?

        if(r!= null)
        {
            r.setScholarship(amount);
            vbMenu.appendText("The scholarship amount has been set to $" + r.getScholarship());
        }
        else {
            vbMenu.appendText("Resident not found"+ "\n");
        }
    }
    @FXML
    /**
     * Prints the students on roster
     */
    protected void onPrintByProfileRoster() //shreya
    {
        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        vbMenu.appendText("testing Roster print by school" + "\n");
        Student[] StudentList = newRoster.getRoster();

        int size = newRoster.getSize();


        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = 1 + i; j < size; j++) {
                if ((StudentList[j].compareTo(StudentList[min])) == -1) {
                    min = j;
                }
            }
            Student temp = StudentList[min];
            StudentList[min] = StudentList[i];
            StudentList[i] = temp;
        }

        if(StudentList ==null)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    vbMenu.appendText(StudentList[i].toString() + "\n");
                    firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                    lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                    String c = (StudentList[i].getProfile().getDateOfBirth());
                    dobPrint.appendText(c + "\n");
                    schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                    majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                    creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                    standingPrint.appendText(StudentList[i].getYear() + "\n");

                }
                if(StudentList[i] ==null)
                {
                    break;
                }
            }

        }

    }
    @FXML
    /**
     * Prints the students by roster by their school
     */
    protected void onPrintBySchoolRoster() //shreya
    {
        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        vbMenu.appendText("testing Roster print by school" + "\n");
        Student[] StudentList = newRoster.getRoster();

        int size = newRoster.getSize();
        for (int i = 0; i <size; i++) {
            int min = i;
            for (int j = 1 + i; j < size; j++) {
                String schoolMajorJ = StudentList[j].getSchool() + StudentList[j].getMajor().toString();
                String schoolMajorMin = StudentList[min].getSchool() + StudentList[min].getMajor().toString();
                if (schoolMajorJ.compareTo(schoolMajorMin) < 0) {
                    min = j;
                }
            }
            Student temp = StudentList[min];
            StudentList[min] = StudentList[i];
            StudentList[i] = temp;
        }

        if(StudentList ==null)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    vbMenu.appendText(StudentList[i].toString() + "\n");
                    firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                    lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                    String c = (StudentList[i].getProfile().getDateOfBirth());
                    dobPrint.appendText(c + "\n");
                    schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                    majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                    creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                    standingPrint.appendText(StudentList[i].getYear() + "\n");

                }
                if(StudentList[i] ==null)
                {
                    break;
                }
            }

        }

    }

    @FXML
    /**
     * Prints the students by their standing(credits)
     */
    protected void onPrintByStandingRoster()
    {
        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");


        vbMenu.appendText("Print by Standing Roster" + "\n");
        Student[] StudentList = newRoster.getRoster();

        int size = newRoster.getSize();
        for (int i = 0; i <size; i++) {
            int min = i;
            for (int j = 1 + i; j < size; j++) {
                if ((StudentList[j].compareTo(StudentList[min])) == -1) {
                    min = j;
                }
            }
            Student temp = StudentList[min];
            StudentList[min] = StudentList[i];
            StudentList[i] = temp;
        }

        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = 1 + i; j < size; j++) {

                if (StudentList[j].getYear().compareTo((StudentList[min].getYear())) < 0) {

                    min = j;
                }
            }
            Student temp = StudentList[min];
            StudentList[min] = StudentList[i];
            StudentList[i] = temp;
        }

        if(StudentList ==null)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    vbMenu.appendText(StudentList[i].toString() + "\n");
                    firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                    lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                    String c = (StudentList[i].getProfile().getDateOfBirth());
                    dobPrint.appendText(c + "\n");
                    schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                    majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                    creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                    standingPrint.appendText(StudentList[i].getYear() + "\n");
                }
                if(StudentList[i] ==null)
                {
                    break;
                }
            }

        }

    }

    @FXML
    /**
     * Prints the students in RBS
     */
    protected void onPrintBySchoolRBS()
    {
        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        vbMenu.appendText("testing Roster print by school RBS " + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList ==null)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    if(StudentList[i].getSchool().equals("RBS"))
                    {
                        vbMenu.appendText(StudentList[i].toString() + "\n");
                        firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                        lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                        String c = (StudentList[i].getProfile().getDateOfBirth());
                        dobPrint.appendText(c + "\n");
                        schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                        majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                        creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                        standingPrint.appendText(StudentList[i].getYear() + "\n");

                    }
                }
                if(StudentList[i] ==null)
                {
                    break;
                }
            }

        }
    }

    @FXML
    /**
     * Prints the students in SAS
     */
    protected void onPrintBySchoolSAS()
    {
        vbMenu.appendText("testing Roster print by school SAS " + "\n");
        Student[] StudentList = newRoster.getRoster();

        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        if(StudentList ==null)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    if(StudentList[i].getSchool().equals("SAS"))
                    {
                        vbMenu.appendText(StudentList[i].toString() + "\n");
                        firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                        lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                        String c = (StudentList[i].getProfile().getDateOfBirth());
                        dobPrint.appendText(c + "\n");
                        schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                        majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                        creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                        standingPrint.appendText(StudentList[i].getYear() + "\n");

                    }
                }
                if(StudentList[i] ==null)
                {
                    break;
                }
            }

        }
    }

    @FXML
    /**
     * Prints the students in SOE
     */
    protected void onPrintBySchoolSOE() //shreya
    {
        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        vbMenu.appendText("testing Roster print by school SOE " + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList ==null)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    if(StudentList[i].getSchool().equals("SOE"))
                    {
                        vbMenu.appendText(StudentList[i].toString() + "\n");
                        firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                        lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                        String c = (StudentList[i].getProfile().getDateOfBirth());
                        dobPrint.appendText(c + "\n");
                        schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                        majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                        creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                        standingPrint.appendText(StudentList[i].getYear() + "\n");

                    }
                }
                if(StudentList[i] ==null)
                {
                    break;
                }
            }

        }
    }

    @FXML
    /**
     * Prints the students in SCI
     */
    protected void onPrintBySchoolSCI()
    {
        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        vbMenu.appendText("testing Roster print by school SC&I " + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList ==null)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    if(StudentList[i].getSchool().equals("SC&I"))
                    {
                        vbMenu.appendText(StudentList[i].toString() + "\n");
                        firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                        lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                        String c = (StudentList[i].getProfile().getDateOfBirth());
                        dobPrint.appendText(c + "\n");
                        schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                        majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                        creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                        standingPrint.appendText(StudentList[i].getYear() + "\n");

                    }
                }
                if(StudentList[i] ==null)
                {
                    break;
                }
            }

        }
    }

    @FXML
    /**
     * Prints the students who are Enrolled
     */
    protected void onPrintEnrolled()
    {

        vbMenu.appendText("Prints enrolled List " + "\n");
       // Student[] StudentList = newRoster.getRoster();
        //Enrollment enrollmentList = new Enrollment();

        EnrollStudent[] enrollStudentsList = enrollmentList.getEnrollStudents();

        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        if(enrollStudentsList ==null)
        {
            vbMenu.appendText("Enrollment list is empty" + "\n" );
        }
        else{
            for(int i=0; i<enrollStudentsList.length; i++)
            {
                if(enrollStudentsList[i] != null)
                {

                        vbMenu.appendText(enrollStudentsList[i].toString() + "\n");
                        firstNamePrint.appendText(enrollStudentsList[i].getProfile().getFname() + "\n");
                        lastNamePrint.appendText(enrollStudentsList[i].getProfile().getLname()+ "\n");
                        String c = (enrollStudentsList[i].getProfile().getDateOfBirth());
                        dobPrint.appendText(c + "\n");
                     //   schoolPrint.appendText(enrollStudentsList[i].getSchool()+ "\n");
                    //   majorPrint.appendText(enrollStudentsList[i].getMajor().toString()+ "\n");
                       creditsPrints.appendText(enrollStudentsList[i].getCreditsEnrolled() + "\n");
                    //    standingPrint.appendText(enrollStudentsList[i].getYear() + "\n");
                    //add a print statement for the


                }
                if(enrollStudentsList[i] ==null)
                {
                    break;
                }
            }

        }

        vbMenu.setText("testing Enroll print"+ "\n");


        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");

        vbMenu.appendText("testing Enroll print" + "\n");
        EnrollStudent[] EnrollStudentList = enrollmentList.getEnrollStudents();

        int size = enrollmentList.getSize();

        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = 1 + i; j < size; j++) {
                if ((EnrollStudentList[j].compareTo(EnrollStudentList[min])) == -1) {
                    min = j;
                }
            }
            EnrollStudent temp = EnrollStudentList[min];
            EnrollStudentList[min] = EnrollStudentList[i];
            EnrollStudentList[i] = temp;
        }

        if(EnrollStudentList ==null)
        {
            vbMenu.appendText("Enrollment is empty" + "\n" );
        }
        else{
            for(int i=0; i<EnrollStudentList.length; i++)
            {
                if(EnrollStudentList[i] != null)
                {
                    vbMenu.appendText(EnrollStudentList[i].toString() + "\n");
                    firstNamePrint.appendText(EnrollStudentList[i].getProfile().getFname() + "\n");
                    lastNamePrint.appendText(EnrollStudentList[i].getProfile().getLname()+ "\n");
                    String c = (EnrollStudentList[i].getProfile().getDateOfBirth());
                    dobPrint.appendText(c + "\n");
                    creditsPrints.appendText(EnrollStudentList[i].getCreditsEnrolled() + "\n");
                }
                if(EnrollStudentList[i] ==null)
                {
                    break;
                }
            }
        }
    }

    @FXML
    /**
     * Prints the students by tuition due
     */
    protected void OnPrintTuitionDue() {
        vbMenu.setText("Print Tuition Due" + "\n");
        firstNamePrint.setText("");
        lastNamePrint.setText("");
        dobPrint.setText("");
        schoolPrint.setText("");
        majorPrint.setText("");
        creditsPrints.setText("");
        standingPrint.setText("");
        tuitionDuePrint.setText("");

        EnrollStudent[] enrollStudentsList = enrollmentList.getEnrollStudents();
        Student[] studentsEnrolled = newRoster.getRoster();
        if (enrollStudentsList == null) {
            vbMenu.appendText("Enrollment list is empty" + "\n");
        } else {
            for (int i = 0; i < enrollStudentsList.length; i++) {
                if (enrollStudentsList[i] != null) {

                    vbMenu.appendText(enrollStudentsList[i].toString() + "\n");
                    firstNamePrint.appendText(enrollStudentsList[i].getProfile().getFname() + "\n");
                    lastNamePrint.appendText(enrollStudentsList[i].getProfile().getLname() + "\n");
                    String c = (enrollStudentsList[i].getProfile().getDateOfBirth());
                    dobPrint.appendText(c + "\n");
                    creditsPrints.appendText(enrollStudentsList[i].getCreditsEnrolled() + "\n");
                    for (int j = 0; j < newRoster.getSize(); j++) {
                        if (studentsEnrolled[j].getProfile().equals(enrollStudentsList[i].getProfile())) {
                            tuitionDuePrint.appendText("$" + studentsEnrolled[j].tuitionDue(enrollStudentsList[i].getCreditsEnrolled()) + "\n");
                        }
                    }
                    if (enrollStudentsList[i] == null) {
                        break;
                    }
                }
            }
        }
    }

    @FXML
    /**
     * Prints the students by semester
     */
    protected void onPrintSemesterEnd(){
        vbMenu.setText("Semester end print"+ "\n");
        EnrollStudent[] EnrollStudentList = enrollmentList.getEnrollStudents();
        Student[] StudentList = newRoster.getRoster();

        if (enrollmentList.getSize() == 0) {
            vbMenu.appendText("Student enrollment list is empty");
        } else {
            for(int i =0; i< enrollmentList.getSize(); i++) {
                EnrollStudent temp = EnrollStudentList[i];
                for(int j=0; j< newRoster.getSize(); j++) {
                    if(StudentList[j].getProfile().equals(temp.getProfile())) {
                        StudentList[i].addCredits(temp.getCreditsEnrolled());
                    }
                }
            }
        }
        if(StudentList==null) {
            vbMenu.appendText("Roster is empty" + "\n" );
        }else{
            for(int i=0; i<StudentList.length; i++) {
                if(StudentList[i] != null) {
                    if(StudentList[i].returnCredits() >=120) {
                        vbMenu.appendText(StudentList[i].toString() + "\n");
                        firstNamePrint.appendText(StudentList[i].getProfile().getFname() + "\n");
                        lastNamePrint.appendText(StudentList[i].getProfile().getLname()+ "\n");
                        String c = (StudentList[i].getProfile().getDateOfBirth());
                        dobPrint.appendText(c + "\n");
                        schoolPrint.appendText(StudentList[i].getSchool()+ "\n");
                        majorPrint.appendText(StudentList[i].getMajor().toString()+ "\n");
                        creditsPrints.appendText(StudentList[i].returnCredits() + "\n");
                        standingPrint.appendText(StudentList[i].getYear() + "\n");
                    }
                }
                if(StudentList[i] ==null){
                    break;
                }
            }

        }
    }

    }