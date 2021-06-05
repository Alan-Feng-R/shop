package controller;

import dao.impl.AssetDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.UserDaoImpl;
import domain.AssetInfo;
import domain.AssetSelling;
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
import java.util.ResourceBundle;

public class buy implements Initializable {
    @FXML
    private TextField quantity;

    @FXML
    private Button buy;

    @FXML
    private Label assetName;

    @FXML
    private Button back;

    @FXML
    private Label currentPrice;

    @FXML
    private Label credit;

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
        credit.setText(String.valueOf(company.getCredit()));
        currentPrice.setText(viewModel.getName1());
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

    public void buy(ActionEvent actionEvent) throws IOException {
        String text = quantity.getText();
        if (text==null){
            alert("ERROR", "The input quantity is empty!", null, Alert.AlertType.ERROR);
            return;
        }
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            alert("ERROR", "The input quantity is wrong! please enter again!", null, Alert.AlertType.ERROR);
            return;
        }
        if (Integer.parseInt(text)>Integer.parseInt(viewModel.getName2())){
            alert("ERROR", "The input quantity is wrong! please enter again!", null, Alert.AlertType.ERROR);
            return;
        }
        if (Integer.parseInt(text)*Double.parseDouble(viewModel.getName1())>Integer.parseInt(credit.getText())){
            alert("ERROR", "Insufficient balance! please enter again!", null, Alert.AlertType.ERROR);
            return;
        }
        User user = new UserDaoImpl().findByUserName(viewModel.getName());
        Company company = new CompanyDaoImpl().findByName(user.getCompanyName());
        double d=Double.parseDouble(credit.getText())-(Double.parseDouble(text)*Double.parseDouble(viewModel.getName1()));
        new CompanyDaoImpl().updateCredit(company.getCompanyName(),String.valueOf(d));
        AssetInfo asset = new AssetDaoImpl().findAssetByNameAndCompany(assetName.getText(), company.getCompanyName());
        int quantity=Integer.parseInt(asset.getQuantity())+Integer.parseInt(text);
        new AssetDaoImpl().updateQuantity(company.getCompanyName(), viewModel1.getName(),String.valueOf(quantity));
        AssetSelling assetSelling = viewModel.getAssetSelling();
        int quantity1=Integer.parseInt(viewModel.getName2())-Integer.parseInt(text);
        new AssetDaoImpl().updateSellingQuantity(assetSelling.getCompanyName(),assetSelling.getAssetName(),String.valueOf(quantity1));
        new AssetDaoImpl().insertNewAssetSelling(assetSelling.getCompanyName(),assetSelling.getAssetName(),viewModel.getName(),"done",viewModel.getName1(),text);
        alert("SUCCESS", "The purchase is successful!", null, Alert.AlertType.INFORMATION);
        Stage primaryStage = (Stage) back.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/detail.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }


}
