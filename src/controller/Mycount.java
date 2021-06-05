package controller;

import dao.impl.UserDaoImpl;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewModel.TargetDataViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Mycount implements Initializable {

    @FXML
    public Label companyText;


    private TargetDataViewModel viewModel = TargetDataViewModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = new UserDaoImpl().findByUserName(viewModel.getName());
        companyText.setText(user.getCompanyName());
    }


    public void back(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) companyText.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/detail.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }

    public void RemainAsset(MouseEvent mouseEvent) throws IOException {
        Stage primaryStage = (Stage) companyText.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/RemainingAssets.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }

    public void cancel(MouseEvent mouseEvent) throws IOException {
        Stage primaryStage = (Stage) companyText.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/cancelAssets.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}
