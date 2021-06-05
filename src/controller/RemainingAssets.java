package controller;

import dao.impl.AssetDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.UserDaoImpl;
import domain.AssetInfo;
import domain.Company;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import viewModel.TargetDataViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RemainingAssets implements Initializable {
    @FXML
    public Label credit;
    @FXML
    public Label companyName;
    @FXML
    public TableColumn assetCol;
    @FXML
    public TableColumn RemainCol;
    @FXML
    public TableColumn updateCol;
    @FXML
    public TableView assetTableView;


    private TargetDataViewModel viewModel = TargetDataViewModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = new UserDaoImpl().findByUserName(viewModel.getName());
        Company company = new CompanyDaoImpl().findByName(user.getCompanyName());
        credit.setText(String.valueOf(company.getCredit()));
        companyName.setText(user.getCompanyName());

          List<AssetInfo> asset = new AssetDaoImpl().findAssetByCompany(user.getCompanyName());
        assetCol.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        RemainCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        updateCol.setCellValueFactory(new PropertyValueFactory<>("updateTime"));
        ObservableList<AssetInfo> data = FXCollections.observableArrayList();
        for (AssetInfo assetInfo : asset) {
            List<Integer> list1 = new AssetDaoImpl().findAllByAssetName(assetInfo.getAssetName(),user.getCompanyName());
            String quantity=assetInfo.getQuantity();
            int num=0;
            for (Integer integer : list1) {
                num+=integer;
            }
            assetInfo.setQuantity((Integer.parseInt(quantity)-num)+"/"+quantity);
            data.add(assetInfo);
        }
        assetTableView.setItems(data);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) credit.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/mycount.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}

