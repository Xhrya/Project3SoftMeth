package com.example.project3softmeth;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TuitionManagerController {
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
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onAddButtonClick(Event e) {
        System.out.println(residentButtons.getSelectedToggle().toString());
        System.out.println(majorButtons.getSelectedToggle().toString());
        System.out.println(firstNameRoster.getText());
        System.out.println(lastNameRoster.getText());
        System.out.println(birthDate.getValue());
    }
}



