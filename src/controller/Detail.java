package controller;

import dao.impl.AssetDaoImpl;
import domain.AssetSelling;
import domain.MarketAsset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import viewModel.SourceDataViewModel;
import viewModel.TargetDataViewModel;
import viewModel.TargetDataViewModel1;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class Detail implements Initializable {
    @FXML
    public Button MyCompany;
    @FXML
    public TableView tableviewBuy;
    @FXML
    public TableView tableviewSell;
    @FXML
    public TableColumn sellpriceCol;
    @FXML
    public TableColumn sellquantityCol;
    @FXML
    public Button backBtn;
    @FXML
    public Label assetName;
    @FXML
    public Button buy;
    @FXML
    public TableColumn buypriceCol;
    @FXML
    public TableColumn buyquantity;
    @FXML
    public Button sellBtn;
    @FXML
    private LineChart<String,String > linechart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;

    private TargetDataViewModel viewModel = TargetDataViewModel.getInstance();
    private TargetDataViewModel1 viewModel1 = TargetDataViewModel1.getInstance();
    private SourceDataViewModel viewModel2 = SourceDataViewModel.getInstance();

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
        sellpriceCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellquantityCol.setCellValueFactory(new PropertyValueFactory<>("RemainNum"));
        assetName.setText(viewModel1.getName());

        List<AssetSelling> list = new AssetDaoImpl().findAllSellingAssetName(viewModel1.getName());
        ObservableList<AssetSelling> data = FXCollections.observableArrayList();
        for (AssetSelling assetSelling : list) {
            data.add(assetSelling);
        }
        tableviewSell.setItems(data);

        ObservableList<AssetSelling> data1 = FXCollections.observableArrayList();
        List<AssetSelling> list1 = new AssetDaoImpl().findAllSellingAssetName(viewModel1.getName());
        buypriceCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        buyquantity.setCellValueFactory(new PropertyValueFactory<>("RemainNum"));
        for (AssetSelling assetSelling : list1) {
            double price = Double.parseDouble(assetSelling.getSellingPrice())+0.50;
            assetSelling.setSellingPrice(String.valueOf(price));
            data1.add(assetSelling);
        }
        tableviewBuy.setItems(data1);

        x.setLabel("date");
        y.setLabel("price");
        linechart.setTitle("CurrentPrice");
        XYChart.Series series=new XYChart.Series();
        List<MarketAsset> marketList=new AssetDaoImpl().findAllMarketPriceByAssetName(viewModel1.getName());
        marketList.sort(new Comparator<MarketAsset>() {
            @Override
            public int compare(MarketAsset o1, MarketAsset o2) {
                String[] split1 = o1.getUpdateTime().split("/");
                String[] split2 = o2.getUpdateTime().split("/");
                if (!split1[2].equals(split2[2])){
                    return Integer.parseInt(split1[2]) - Integer.parseInt(split2[2]);
                }else if (!split1[1].equals(split2[1])){
                    return Integer.parseInt(split1[1]) - Integer.parseInt(split2[1]);
                }else {
                    return Integer.parseInt(split1[0]) - Integer.parseInt(split2[0]);
                }
            }
        });

        for (MarketAsset marketAsset : marketList) {
            series.getData().add(new XYChart.Data(marketAsset.getUpdateTime(), marketAsset.getCurrentPrice()));
        }
        linechart.getData().addAll(series);

    }


    public void myCount(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) MyCompany.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/mycount.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }


    public void back(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) MyCompany.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/cartlist.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }

    public void buy(ActionEvent actionEvent) throws IOException {
        AssetSelling assetSelling = (AssetSelling) tableviewBuy.getSelectionModel().getSelectedItem();
        viewModel2.setAssetSelling(assetSelling);
        viewModel2.setTargetData3();
        if (assetSelling==null){
            alert("ERROR", "Please select the purchase price!", null, Alert.AlertType.ERROR);
            return;
        }
        viewModel2.setName1(assetSelling.getSellingPrice());
        viewModel2.setName2(assetSelling.getRemainNum());
        viewModel2.setTargetData1();
        viewModel2.setTargetData2();
        Parent root = FXMLLoader.load(getClass().getResource("../view/buy.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }

    public void sell(ActionEvent actionEvent) throws IOException {
        AssetSelling assetSelling = (AssetSelling) tableviewSell.getSelectionModel().getSelectedItem();
        viewModel2.setAssetSelling(assetSelling);
        viewModel2.setTargetData3();
        if (assetSelling==null){
            alert("ERROR", "Please select the purchase price!", null, Alert.AlertType.ERROR);
            return;
        }
        viewModel2.setName1(assetSelling.getSellingPrice());
        viewModel2.setName2(assetSelling.getRemainNum());
        viewModel2.setTargetData1();
        viewModel2.setTargetData2();
        Parent root = FXMLLoader.load(getClass().getResource("../view/sell.fxml"));
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}
