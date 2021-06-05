package domain;

import javafx.beans.property.SimpleStringProperty;

public class AssetSelling {
    private final SimpleStringProperty AssetName;
    private final SimpleStringProperty RemainNum;
    private final SimpleStringProperty username;
    private final SimpleStringProperty uploadTime;
    private final SimpleStringProperty state;
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty sellingPrice;


    public AssetSelling(String assetName, String remainNum, String username, String uploadTime, String state, String companyName, String sellingPrice) {
        this.AssetName = new SimpleStringProperty(assetName);
        this.RemainNum = new SimpleStringProperty(remainNum);
        this.uploadTime =new SimpleStringProperty(uploadTime);
        this.state = new SimpleStringProperty(state);
        this.companyName = new SimpleStringProperty(companyName);
        this.username = new SimpleStringProperty(username);
        this.sellingPrice=new SimpleStringProperty(sellingPrice);
    }

    public AssetSelling(SimpleStringProperty assetName, SimpleStringProperty remainNum, SimpleStringProperty username, SimpleStringProperty uploadTime, SimpleStringProperty state, SimpleStringProperty companyName, SimpleStringProperty sellingPrice) {
        AssetName = assetName;
        RemainNum = remainNum;
        this.username = username;
        this.uploadTime = uploadTime;
        this.state = state;
        this.companyName = companyName;
        this.sellingPrice = sellingPrice;
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getSellingPrice() {
        return sellingPrice.get();
    }

    public SimpleStringProperty sellingPriceProperty() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice.set(sellingPrice);
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getAssetName() {
        return AssetName.get();
    }

    public SimpleStringProperty assetNameProperty() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        this.AssetName.set(assetName);
    }

    public String getRemainNum() {
        return RemainNum.get();
    }

    public SimpleStringProperty remainNumProperty() {
        return RemainNum;
    }

    public void setRemainNum(String remainNum) {
        this.RemainNum.set(remainNum);
    }

    public String getUploadTime() {
        return uploadTime.get();
    }

    public SimpleStringProperty uploadTimeProperty() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime.set(uploadTime);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    @Override
    public String toString() {
        return "AssetInfo{" +
                "AssetName=" + AssetName +
                ", RemainNum=" + RemainNum +
                ", uploadTime=" + uploadTime +
                ", state=" + state +
                '}';
    }
}
