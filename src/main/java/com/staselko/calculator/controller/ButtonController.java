package com.staselko.calculator.controller;

import com.staselko.calculator.utils.Sign;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ButtonController {
    private static StringBuilder inputText = new StringBuilder(" ");
    private double result;
    private String action = "=";

    @FXML
    private TextField input;

    @FXML
    public void pressButton(ActionEvent event) {
        enter((Button) event.getSource());
    }

    @FXML
    public void setAPoint() {
        if (inputText.length() == 1){
            inputText.append("0.");
        }else if (inputText.indexOf(".") == -1){
            inputText.append(".");
        }
        changeInput(inputText);
    }

    @FXML
    public void deleteAll() {
        inputText.setLength(1);
        inputText.setCharAt(0, ' ');
        result = 0d;
        action = "=";
        changeInput(inputText);
    }

    @FXML
    void delete() {
        if (input.getText().equals(String.valueOf(result))) {
            if (result > 0d) {
                inputText.append(result);
            } else if (result < 0d) {
                inputText.setCharAt(0, '-');
                inputText.append(result * (-1));
            }
        }
        if (inputText.length() > 1) {
            inputText.setLength(inputText.length() - 1);
        } else if (inputText.length() == 1) {
            inputText.setCharAt(0, ' ');
        }
        changeInput(inputText);
    }

    @FXML
    void change_sign() {
        String str = input.getText();
        if (str.length() > 1) {
            if (inputText.length() == 1) {
                if (str.charAt(0) == '-') {
                    inputText.setCharAt(0, '-');
                    inputText.append(str.substring(1));
                } else {
                    inputText.append(str);
                }
            }
            if (inputText.charAt(0) == ' ') {
                inputText.setCharAt(0, '-');
            } else {
                inputText.setCharAt(0, ' ');
            }
            changeInput(inputText);
        }
    }

    @FXML
    void action(ActionEvent event) {
        Button but = (Button) event.getSource();
        if (input.getText().length() > 1) {
            try {
                result = Sign.getResult(result,
                        Double.parseDouble(input.getText()),
                        Sign.convert(action));
                action = but.getText();
                input.setText(String.valueOf(result));
            } catch (IllegalArgumentException | ArithmeticException e) {
                input.setText("error");
                result = 0d;
                action = "=";
            }
            clearInputText();
        }
    }

    @FXML
    void percent() {
        double num = 0d;
        if (input.getText().length() > 1) {
            Sign sign = Sign.convert(action);
            if (sign == Sign.PLUS || sign == Sign.MINUS) {
                num = result * Double.parseDouble(input.getText()) / 100;
            } else if (sign == Sign.MULTIPLY || sign == Sign.DIVIDE) {
                num = Double.parseDouble(input.getText()) / 100;
            }
            try {
                result = Sign.getResult(result, num, sign);
                action = "%";
            } catch (ArithmeticException e) {
                input.setText("error");
                action = "=";
                result = 0d;
                clearInputText();
            }
        }
    }

    private void enter(Button btn) {
        if (inputText.length() < 22) {
            inputText = inputText.append(btn.getText());
            changeInput(inputText);
        }
    }

    private void changeInput(StringBuilder str) {
        if (inputText.length() > 1) {
            input.setText(String.valueOf(str));
        } else {
            inputText.setCharAt(0, ' ');
            input.setText("");
        }
    }
    private void clearInputText(){
        inputText.setLength(1);
        if (inputText.charAt(0) == '-') {
            inputText.setCharAt(0, ' ');
        }
    }
}
