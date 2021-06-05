package controller;

import dao.impl.AssetDaoImpl;
import dao.impl.CompanyDaoImpl;
import domain.AssetInfo;
import domain.Company;
import domain.MarketAsset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class addNewAsset implements Initializable {
    @FXML
    public TextField assetName;
    @FXML
    public TextField cassetName;
    @FXML
    public ChoiceBox companyList;

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
    public void initialize(URL location, ResourceBundle resources) {
        List<Company> companylist = new CompanyDaoImpl().findAll();
        ObservableList<String> data = FXCollections.observableArrayList();
        for (Company company : companylist) {
            data.add(company.getCompanyName());
        }
        for (String datum : data) {
            companyList.getItems().add(datum);
        }
    }

    public void add(ActionEvent actionEvent) {
        if (companyList.getValue()==null){
            alert("ERROR", "The company cannot be empty! Please select a company!",null, Alert.AlertType.ERROR);
            return;
        }
        List<AssetInfo> list = new AssetDaoImpl().findAllAssetName();
        if (!assetName.getText().equals(cassetName.getText())){
            alert("ERROR", "The two names are different, please re-enter!", null, Alert.AlertType.ERROR);
            return;
        }
        for (AssetInfo assetInfo : list) {
            if (assetInfo.getAssetName().equals(assetName.getText())){
                alert("ERROR", "The product already exists, please re-enter!", null, Alert.AlertType.ERROR);
                return;
            }
        }
        new AssetDaoImpl().insertNewAsset(assetName.getText(),(String)companyList.getValue());
        MarketAsset marketAsset = new AssetDaoImpl().findAssetByName(assetName.getText());
        if (marketAsset==null){
            new AssetDaoImpl().insertNewMarketasset(assetName.getText());
        }
        alert("SUCCESS", "added success!", null, Alert.AlertType.INFORMATION);
    }


}
