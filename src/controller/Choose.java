package controller;

import dao.impl.AssetDaoImpl;
import domain.AssetSelling;
import domain.MarketAsset;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import viewModel.TargetDataViewModel;
import viewModel.TargetDataViewModel1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Choose implements Initializable {
    @FXML
    private Button backBtn;

    @FXML
    private Label assetName;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label currentPrice;

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
        MarketAsset marketAsset = new AssetDaoImpl().findAssetByName(viewModel1.getName());
        currentPrice.setText(String.valueOf(marketAsset.getCurrentPrice()));
    }

    public void confirm(ActionEvent actionEvent) throws IOException {
        AssetSelling assetSelling = viewModel.getAssetSelling();
        new AssetDaoImpl().deleteAssetSelling(assetSelling);
        alert("SUCCESS", "delete successfully!", null, Alert.AlertType.INFORMATION);
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        primaryStage.close();
        /*Parent root = FXMLLoader.load(getClass().getResource("../view/cancelAssets.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口*/
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        primaryStage.close();
       /* Parent root = FXMLLoader.load(getClass().getResource("../view/cancelAssets.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口*/
    }


}
