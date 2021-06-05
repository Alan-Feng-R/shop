package controller;

import dao.impl.AssetDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.UserDaoImpl;
import domain.AssetInfo;
import domain.Company;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewModel.TargetDataViewModel;
import viewModel.TargetDataViewModel1;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Sell implements Initializable {
    @FXML
    private Button sellBtn;

    @FXML
    private TextField quantity;

    @FXML
    private Label assetName;

    @FXML
    private Button back;

    @FXML
    private TextField sellPrice;

    private TargetDataViewModel viewModel = TargetDataViewModel.getInstance();
    private TargetDataViewModel1 viewModel1 = TargetDataViewModel1.getInstance();

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assetName.setText(viewModel1.getName());
        User user = new UserDaoImpl().findByUserName(viewModel.getName());
        Company company = new CompanyDaoImpl().findByName(user.getCompanyName());
    }

    public void sell(ActionEvent actionEvent) throws IOException {
        String quantityText = quantity.getText();
        String sellPriceText = sellPrice.getText();
        User user = new UserDaoImpl().findByUserName(viewModel.getName());
        Company company = new CompanyDaoImpl().findByName(user.getCompanyName());
        if (quantityText==null|sellPriceText==null){
            alert("ERROR", "The input  is empty!", null, Alert.AlertType.ERROR);
            return;
        }
        try {
            int quantity=Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            alert("ERROR", "The input quantity is wrong! please enter again!", null, Alert.AlertType.ERROR);
            return;
        }
        AssetInfo assetInfo=new AssetDaoImpl().findAssetByCompanyAndAssetName(company.getCompanyName(),viewModel1.getName());
        List<Integer> list1 = new AssetDaoImpl().findAllByAssetName(assetInfo.getAssetName(),user.getCompanyName());
        int num=0;
        for (Integer integer : list1) {
            num+=integer;
        }
        if (Integer.parseInt(quantityText)>num){
            alert("ERROR", "The input quantity is wrong!", null, Alert.AlertType.ERROR);
            return;
        }
        new AssetDaoImpl().insertNewAssetSelling(company.getCompanyName(), viewModel1.getName(), user.getUsername(),"selling",sellPriceText,quantityText);
        alert("SUCCESS", "The sale is successful!", null, Alert.AlertType.INFORMATION);
        Stage primaryStage = (Stage) back.getScene().getWindow();
        primaryStage.close();
        /*Parent root = FXMLLoader.load(getClass().getResource("../view/detail.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口*/
    }


    public void back(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/detail.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}
