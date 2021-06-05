package domain;

public class MarketAsset {
    private String assetName;
    private Double currentPrice;
    private String updateTime;


    public MarketAsset() {
    }

    public MarketAsset(String assetName, double currentPrice, String updateTime) {
        this.assetName = assetName;
        this.currentPrice = currentPrice;
        this.updateTime = updateTime;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MarketAsset{" +
                "assetName='" + assetName + '\'' +
                ", currentPrice=" + currentPrice +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
