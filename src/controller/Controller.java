package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private StackPane content;
    @FXML
    private Button exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("../view/create.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(fxml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void create(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/create.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void invite(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/invite.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void assets(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/assets.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void edit(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/edit.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void add(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/addnewasset.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void addUser(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/addUser.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) exit.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}