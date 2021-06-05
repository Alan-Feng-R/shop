package controller;

import domain.MarketAsset;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewModel.SourceDataviewModel1;

import java.io.IOException;

public class Item {
    @FXML
    private Label assetNameLabel;

    @FXML
    private Label sellPriceLabel;

    private SourceDataviewModel1 viewModel1 = SourceDataviewModel1.getInstance();


    public void setData(MarketAsset marketAsset){
        assetNameLabel.setText(marketAsset.getAssetName());
        sellPriceLabel.setText(String.valueOf(marketAsset.getCurrentPrice()));
    }

    public void confirm(MouseEvent mouseEvent) throws IOException {
        viewModel1.setName(assetNameLabel.getText());
        viewModel1.setTargetData();
        Stage primaryStage = (Stage) assetNameLabel.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/detail.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}
