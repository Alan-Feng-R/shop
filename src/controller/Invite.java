package controller;

import dao.impl.UserDaoImpl;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class Invite {
    @FXML
    public TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private PasswordField cpassword;
    /**
     * 弹框
     */
    public void alert(String title, String content, String header, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void add(ActionEvent actionEvent) {
        List<User> list = new UserDaoImpl().findAll();
        if (!password.getText().equals(cpassword.getText())) {
            alert("ERROR", "The password input is inconsistent, please re-enter!", null, Alert.AlertType.ERROR);
        } else {
            for (User user : list) {
                if (username.getText().equals(user.getUsername())) {
                    alert("ERROR", "Username already exists, please re-enter!", null, Alert.AlertType.ERROR);
                    return;
                }
            }
            User user = new User(username.getText(), password.getText(),1,null);
            new UserDaoImpl().saveAdmin(user);
            alert("SUCCESS", "added Successfully!", null, Alert.AlertType.INFORMATION);
        }
    }
}
