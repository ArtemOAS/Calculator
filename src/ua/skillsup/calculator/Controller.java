package ua.skillsup.calculator;

import com.sun.corba.se.spi.extension.ZeroPortPolicy;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ua.skillsup.calculator.exception.Exceptions;

public class Controller {
    private double firstValue;
    private double secondValue;
    private String operatorPrev;

    private StringProperty displayValue = new SimpleStringProperty("0");

    public String getDisplayValue() {
        return displayValue.get();
    }

    public StringProperty displayValueProperty() {
        return displayValue;
    }

    @FXML
    private void onNumberedButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();
        String number = button.getText();
        String existValue = getDisplayValue();
        if ("0".equals(existValue)) {
            displayValue.set(number);
        } else {
            displayValue.set(existValue + number);
        }

    }

    @FXML
    private void reset() {
        displayValue.setValue("0");
    }

    @FXML
    private void setPoint() {
        String existValue = getDisplayValue();
        if (!existValue.contains(".")) {
            displayValue.setValue(existValue + ".");
        }
    }

    @FXML
    private void calculate(ActionEvent event) {
        Button button = (Button) event.getSource();
        String operator = button.getText();
        if (operatorPrev == null) {
            operatorPrev = operator;
            firstValue = Double.parseDouble(displayValue.getValue());
            secondValue = Double.parseDouble(displayValue.getValue());
        } else {
            switch (operatorPrev) {

                case "+":
                    displayValue.setValue(String.valueOf(firstValue+=secondValue));
                    break;

                case "-":
                    displayValue.setValue(String.valueOf(firstValue-=secondValue));
                    break;

                case "*":
                    displayValue.setValue(String.valueOf(firstValue*=secondValue));
                    break;

                case "/":
                    try {
                        displayValue.setValue(String.valueOf(firstValue/=secondValue));
                    } catch (Exception e) {
                        if (String.valueOf(firstValue).equals("0")) {
                            Exceptions.cantDivideByZeroException();
                        } else {
                            throw new IllegalArgumentException("Unknown exception " + e.getMessage());
                        }
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Incorrect symbol");
            }
        }
    }
}
