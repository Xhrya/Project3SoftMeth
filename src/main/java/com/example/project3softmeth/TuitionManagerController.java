package com.example.project3softmeth;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;

public class TuitionManagerController {

    //everything for RosteR:
    @FXML
    private Label welcomeText;
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
    DatePicker birthDate;

    @FXML
    DatePicker birthDateEnroll;

    //everything for Enroll/Drop




    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onAddButtonClick(Event e) {
        System.out.println(residentButtons.getSelectedToggle().toString());
        String residency = residentButtons.getSelectedToggle().toString();
        residency = residency.substring(residency.indexOf("'") +1, residency.length() -1);

        System.out.println(residency);
        System.out.println(majorButtons.getSelectedToggle().toString());
        System.out.println(firstNameRoster.getText());
        System.out.println(lastNameRoster.getText());
        System.out.println(birthDate.getValue());


        if(residency.equals("Resident"))
        {
            System.out.println("resident 1");
        }
        else if(residentButtons.getSelectedToggle().toString().equals("NonResident"))
        {
            System.out.println("non 1");
        }
       else if(residentButtons.getSelectedToggle().toString().equals("Tristate"))
        {
            System.out.println("tri 1");
        }
        else if(residentButtons.getSelectedToggle().toString().equals("International"))
        {
            System.out.println("inter 1");
        }


    }

    protected void OnDropButtonClick(Event e)
    {//attach to scene builder

    }


}
