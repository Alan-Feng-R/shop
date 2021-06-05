package controller;

import dao.impl.AssetDaoImpl;
import dao.impl.AssetInfoDaoImpl;
import domain.MarketAsset;
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
    }

    public void add(ActionEvent actionEvent) {
        List<String> list = new AssetInfoDaoImpl().findAssetType();
        if (!assetName.getText().equals(cassetName.getText())){
            alert("ERROR", "The two names are different, please re-enter!", null, Alert.AlertType.ERROR);
            return;
        }
        for (String s : list) {
            if (s.equals(assetName.getText())){
                alert("ERROR", "The product already exists, please re-enter!", null, Alert.AlertType.ERROR);
                return;
            }
        }
        new AssetInfoDaoImpl().insertNewAssetType(assetName.getText());
        MarketAsset marketAsset = new AssetDaoImpl().findAssetByName(assetName.getText());
        if (marketAsset==null){
            new AssetDaoImpl().insertNewMarketasset(assetName.getText());
        }
        alert("SUCCESS", "added success!", null, Alert.AlertType.INFORMATION);
    }


}
