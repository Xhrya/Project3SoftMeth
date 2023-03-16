/**
 * @author Shreya Pandey
 * @author Medhasri Veldurthi
 */
package com.example.project3softmeth;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TuitionManagerController {
    //everything for RosteR:
//    @FXML
//    private Label welcomeText;
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!"+ "\n");
//    }



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

        //making the Date, Profile, and Student
        Date RMDate = new Date(dob);
        Profile RMProfile = new Profile(lastName, firstName, RMDate);
        Student RMStudent = newRoster.ProfileToStudent(RMProfile);
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
    protected void onLoadFromFile(Event e) {//attach to scene builder
        uploadFile = new FileChooser();
        File f = uploadFile.showOpenDialog(null);
        if (f != null) {
            vbMenu.appendText(f.getPath());
            try {
                Scanner fileScanner = new Scanner(f);
                while (fileScanner.hasNextLine()) {
                    vbMenu.appendText(fileScanner.toString());
                }
            } catch (FileNotFoundException ex) {
                System.out.println("no file found");
            }
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
            vbMenu.appendText(enrollProfile.toString() + "enrolled " + credits + " credits"+ "\n");
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
        vbMenu.appendText("testing Roster print" + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList.length ==0)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    vbMenu.appendText(StudentList[i].toString() + "\n");
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

        if(StudentList.length ==0)
        {
            vbMenu.appendText("Roster is empty" + "\n" );
        }
        else{
            for(int i=0; i<StudentList.length; i++)
            {
                if(StudentList[i] != null)
                {
                    vbMenu.appendText(StudentList[i].toString() + "\n");
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
        vbMenu.setText("testing Roster print"+ "\n");
    }

    @FXML
    /**
     * Prints the students in RBS
     */
    protected void onPrintBySchoolRBS()
    {
        vbMenu.appendText("testing Roster print by school RBS " + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList.length ==0)
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
        vbMenu.appendText("Testing Roster print by school SAS " + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList.length ==0)
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
        vbMenu.appendText("Testing Roster print by school SOE " + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList.length ==0)
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
        vbMenu.appendText("testing Roster print by school SC&I " + "\n");
        Student[] StudentList = newRoster.getRoster();

        if(StudentList.length ==0)
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
                        firstNamePrint.appendText(StudentList[i].getProfile().getFname());
                        lastNamePrint.appendText(StudentList[i].getProfile().getLname());
                        String c = Integer.toString(StudentList[i].getProfile().getAge()); //change to get dob
                        dobPrint.appendText(c);
                        schoolPrint.appendText(StudentList[i].getSchool());
                        majorPrint.appendText(StudentList[i].getMajor().toString());


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
    protected void onPrintEnrolled() //medha
    {
        vbMenu.setText("testing Roster print"+ "\n");
//        for(int i =0; i<enrollmentList.size; i++)
//        {
//            vbMenu.setText(enrollmentList[i].toString());
//        }

    }

    @FXML
    /**
     * Prints the students by tuition due
     */
    protected void OnPrintTuitionDue() //medha
    {
        vbMenu.setText("Print Tuition Due"+ "\n");


    }

    @FXML
    /**
     * Prints the students by semester
     */
    protected void onPrintSemesterEnd() //medha
    {
        vbMenu.setText("testing Roster print"+ "\n");
    }




    }

