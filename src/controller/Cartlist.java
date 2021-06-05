package controller;

import dao.impl.AssetDaoImpl;
import dao.impl.UserDaoImpl;
import domain.AssetInfo;
import domain.MarketAsset;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import viewModel.TargetDataViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Cartlist implements Initializable {
    @FXML
    public ScrollPane content;
    @FXML
    public Pane pane;
    @FXML
    private GridPane grid;


    private TargetDataViewModel viewModel = TargetDataViewModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = new UserDaoImpl().findByUserName(viewModel.getName());
        List<AssetInfo> asset = new AssetDaoImpl().findAssetByCompany(user.getCompanyName());
        int column = 0;
        int row = 0;
        try {
            for (AssetInfo assetInfo : asset) {
                MarketAsset marketAsset = new AssetDaoImpl().findAssetByName(assetInfo.getAssetName());
                while (marketAsset == null) {
                    marketAsset = new AssetDaoImpl().findAssetByName(assetInfo.getAssetName());
                    double d = -1.0 + Math.random() * (1.0 - (-1.0) + 1);
                    new AssetDaoImpl().insertNewAssetPrice(assetInfo.getAssetName(), d);
                }
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Item item = fxmlLoader.getController();
                item.setData(marketAsset);

                grid.add(anchorPane, column, row++);
                grid.setMinWidth(-1);
                grid.setPrefWidth(-1);
                grid.setMaxWidth(Double.NEGATIVE_INFINITY);

                grid.setMinHeight(-1);
                grid.setPrefHeight(-1);
                grid.setMaxHeight(Double.NEGATIVE_INFINITY);
                GridPane.setMargin(anchorPane, new Insets(20));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

