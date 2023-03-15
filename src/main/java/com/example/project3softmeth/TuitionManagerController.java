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

public class TuitionManagerController {
    //everything for RosteR:
//    @FXML
//    private Label welcomeText;
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
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



    //everything for Enroll/Drop
    @FXML
    protected void onAddButtonClick(Event e) {
       //converting the toggle buttons of resident and major into strings
        String residency = residentButtons.getSelectedToggle().toString();
        residency = residency.substring(residency.indexOf("'") +1, residency.length() -1);

        String major = majorButtons.getSelectedToggle().toString();
        major = major.substring(major.indexOf("'") +1, major.length() -1);


        //delete this later!
        System.out.println(residentButtons.getSelectedToggle().toString());
        System.out.println("STATUS:"+residency);
        System.out.println("Major: " + majorButtons.getSelectedToggle().toString());
        System.out.println("MAJOR:" + major);
        System.out.println("FirstName:" + firstNameRoster.getText());
        System.out.println("Lastname :" + lastNameRoster.getText());
        System.out.println("Credits completed:"+ creditsCompletedRoster.getText());
        System.out.println("Birthdate :" + birthDate.getValue());
//        String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
//        System.out.println(dob);
        //delete this later^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


        if(residency.equals("Resident")) {
            //NEED AN EXCEPTION TO MAKE SURE ALL VALUES ARE ENTERED AND NONE OF THE FIELDS ARE MISSING
            String firstName = firstNameRoster.getText();
            String lastName = lastNameRoster.getText();
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                System.out.println("Credits completed invalid: not an integer!");
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
                            System.out.println(thisStudent.toString() + " added to the roster.");
                        } else {
                            System.out.println(thisStudent.toString() + " is already in the roster.");
                        }
                    } else {
                        System.out.println("Credits completed invalid: cannot be negative!");
                    }
                } else {
                    System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                }
            } else {
                System.out.println("DOB invalid: " + dob.toString() + " is younger than 16 years old.");
            }
        }else if(residency.equals("NonResident")) {
            System.out.println("Nonresident entered");
            //NEED AN EXCEPTION TO MAKE SURE ALL VALUES ARE ENTERED AND NONE OF THE FIELDS ARE MISSING
            String firstName = firstNameRoster.getText();
            String lastName = lastNameRoster.getText();
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                System.out.println("Credits completed invalid: not an integer!");
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
                                System.out.println(thisStudent.toString() + " added to the roster.");
                            } else {
                                System.out.println(thisStudent.toString() + " is already in the roster.");
                            }
                        } else {
                            System.out.println("Credits completed invalid: cannot be negative!");
                        }
                } else {
                    System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                }
            } else {
                System.out.println("DOB invalid: " + dob.toString() + " is younger than 16 years old.");
            }

        } else if(residency.equals("Tristate")) {
            System.out.println("triSTATE entered");
            String firstName = firstNameRoster.getText();
            String lastName = lastNameRoster.getText();
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
            String state = (regionButtons.getSelectedToggle().toString());
            state = state.substring(state.indexOf("'") +1, state.length() -1);
            System.out.println("STATE:" + state);

            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                System.out.println("Credits completed invalid: not an integer!");
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

                                System.out.println(thisStudent.toString() + " added to the roster.");
                            } else {
                                System.out.println(thisStudent.toString() + " is already in the roster.");
                            }
                        } else {
                            System.out.println("Credits completed invalid: cannot be negative!");
                        }
                } else {
                    System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                }
            } else {
                System.out.println("DOB invalid: " + dob.toString() + " is younger than 16 years old.");
            }
        } else if(residency.equals("International")) {
            System.out.println("international entered");
            String firstName = firstNameRoster.getText();
            String lastName = lastNameRoster.getText();
            String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);
            boolean abroad = isAbroad.isSelected();

            int credits = 0;
            try{
                credits = Integer.parseInt((creditsCompletedRoster.getText()));
            } catch( NumberFormatException creditE) {
                System.out.println("Credits completed invalid: not an integer!");
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
                                System.out.println(thisStudent.toString() + " added to the roster.");
                            } else {
                                System.out.println(thisStudent.toString() + " is already in the roster.");
                            }
                        } else {
                            System.out.println("Credits completed invalid: cannot be negative!");
                        }
                } else {
                    System.out.println("DOB invalid: " + dob.toString() + " not a valid calendar date!");
                }
            } else {
                System.out.println("DOB invalid: " + dob.toString() + " is younger than 16 years old.");
            }


        }

    }


    @FXML
    protected void onDropButtonClick(Event e) {
        String firstName = firstNameRoster.getText();
        String lastName = lastNameRoster.getText();
        String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);

        //making the Date, Profile, and Student
        Date RMDate = new Date(dob);
        Profile RMProfile = new Profile(lastName, firstName, RMDate);
        Student RMStudent = newRoster.ProfileToStudent(RMProfile);
        boolean isRemoved = newRoster.remove(RMStudent);
        if (isRemoved) {
            System.out.println(RMProfile.toString() + " removed from the roster.");
        } else {
            System.out.println(RMProfile.toString() + " is not in the roster.");
        }
    }

    @FXML
    protected void onChangeMajorClick(Event e) {
        String major = majorButtons.getSelectedToggle().toString();
        major = major.substring(major.indexOf("'") +1, major.length() -1);

        String firstName = firstNameRoster.getText();
        String lastName = lastNameRoster.getText();
        String dob = birthDate.getValue().toString().substring(5 , 7) + "/" + birthDate.getValue().toString().substring(8 , 10) + "/" + birthDate.getValue().toString().substring(0 , 4);

        //making the Date & Profile
        Date CDate = new Date(dob);
        Profile CProfile = new Profile(lastName, firstName, CDate);
        Major MajorEnum = Major.valueOf(major);

        //Changing the major of the student
        boolean isChanged = newRoster.ChangeMajor(CProfile, MajorEnum);
        if (isChanged) {
            System.out.println(CProfile.toString() + " major changed to " + major.toString());
        } else {
            System.out.println("Student not found");
        }
    }

    @FXML
    protected void onLoadFromFile(Event e) {//attach to scene builder

    }
    //    @FXML
//    VBox vbMenu;

//    @FXML
//    protected void onLoadFromFileClick(Event e)
//    {
//        Window stage = vbMenu.getScene().getWindow();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File("C:\\temp"));
//        fileChooser.setTitle("Load Dialog");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"), new FileChooser.ExtensionFilter("pdf", "*.pdf"));
//
//        try{
//            File file = fileChooser.showSaveDialog(stage);
//            fileChooser.setInitialDirectory(file.getParentFile());
//
//        }
//        catch(Exception ex)
//        {
//
//        }
//    }

    @FXML
    protected void onEnrollClick(Event e)
    {
        String firstName = firstNameEnroll.getText();
        String lastName = lastNameEnroll.getText();
        String d = birthDateEnroll.getValue().toString().substring(5 , 7) + "/" + birthDateEnroll.getValue().toString().substring(8 , 10) + "/" + birthDateEnroll.getValue().toString().substring(0 , 4);

        Date dob = new Date(d);
        int credits = 0;
        try{
            credits = Integer.parseInt(creditsEnroll.getText());
        }
        catch(NumberFormatException g)
        {
            System.out.println("Credits completed invalid: not an integer!");
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
            System.out.println(enrollProfile.toString() + "enrolled " + credits + " credits");
        }
    }


    @FXML
    protected void onDropClick(Event e)
    {
        String firstName = firstNameEnroll.getText();
        String lastName = lastNameEnroll.getText();
        String d = birthDateEnroll.getValue().toString().substring(5 , 7) + "/" + birthDateEnroll.getValue().toString().substring(8 , 10) + "/" + birthDateEnroll.getValue().toString().substring(0 , 4);
        Date dob = new Date(d);

        int credits = 0;
        try{
            credits = Integer.parseInt(creditsEnroll.getText());
        }
        catch(NumberFormatException n)
        {
            System.out.println("Credits completed invalid: not an integer!");
            return;
        }

        boolean isValid = dob.isValid();
        Profile enrollProfile = new Profile(lastName, firstName, dob);

        //making enrollStudent and adding/updating credits:

        EnrollStudent newEnroll = new EnrollStudent(enrollProfile, credits);
        if(enrollmentList.contains(newEnroll))
        {
            enrollmentList.remove(newEnroll);
            System.out.println(enrollProfile.toString() + " dropped.");
        }
        else
        {
            System.out.println(enrollProfile.toString() + "is not enrolled ");
        }

    }
    @FXML
    protected void onUpdateScholarshipAmountClick(Event e)
    {
        String firstName = firstNameScholarship.getText();
        String lastName = lastNameScholarship.getText();
        String d = birthDateScholarship.getValue().toString().substring(5 , 7) + "/" + birthDateScholarship.getValue().toString().substring(8 , 10) + "/" + birthDateScholarship.getValue().toString().substring(0 , 4);
        Date dob = new Date(d);
        int amount = 0;
        try{
            amount = Integer.parseInt(amountScholarship.getText());
        }
        catch(NumberFormatException f)
        {
            System.out.println("Amount awarded invalid: not an integer!");
            return;
        }

        Profile findRes = new Profile(lastName, firstName, dob); //create a new Profile for the student
        Resident r = newRoster.findResident(findRes); //only residents have scholarship?

        if(r!= null)
        {
            r.setScholarship(amount);
            System.out.println("The scholarship amount has been set to $" + r.getScholarship());
        }
        else {
            System.out.println("Resident not found");
        }
    }
    @FXML
    protected void onPrintByProfileRoster()
    {
        vbMenu.setText("testing Roster print");
    }




    }

