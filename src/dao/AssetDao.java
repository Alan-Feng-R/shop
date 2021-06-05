package dao;

import domain.AssetInfo;
import domain.AssetSelling;
import domain.MarketAsset;

import java.util.Date;
import java.util.List;

public interface AssetDao {
    List<AssetSelling> findAll();

    List<AssetInfo> findAssetByCompany(String company);

     AssetInfo findAssetByNameAndCompany(String assetName, String company);

    void updateQuantity(String company, String assetName, String quantity);

    List<AssetInfo> findAllAssetName();

    List<Integer> findAllByAssetName(String assetName,String companyName);

    List<AssetSelling> findByCompany(String companyName);

    void insertNewAsset(String text, String value);

    List<AssetSelling> findSellingAssetName(String assetName);

    MarketAsset findAssetByName(String assetName);

    void insertNewAssetPrice(String assetName, double d);

    MarketAsset GetAssetPrice(String assetName, Date date);

    void updateSellingQuantity(String companyName, String assetName, String valueOf);

    void insertNewAssetSelling(String companyName, String assetName, String username,String state ,String sellPrice, String remain);

    List<AssetSelling> findAllSellingAssetName(String assetName);

    AssetInfo findAssetByCompanyAndAssetName(String companyName, String name);

    void deleteAssetSelling(AssetSelling assetSelling);

    List<MarketAsset> findAllMarketPriceByAssetName(String name);

    void insertNewMarketasset(String text);
}
