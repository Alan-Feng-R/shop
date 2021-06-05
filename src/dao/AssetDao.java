package dao;

import domain.AssetSelling;
import domain.MarketAsset;

import java.util.Date;
import java.util.List;

public interface AssetDao {
    List<AssetSelling> findAll();

    List<Integer> findAllByAssetName(String assetName, String companyName);

    List<AssetSelling> findByCompany(String companyName);

    List<AssetSelling> findSellingAssetName(String assetName);

    MarketAsset findAssetByName(String assetName);

    void insertNewAssetPrice(String assetName, double d);

    MarketAsset GetAssetPrice(String assetName, Date date);


    void insertNewAssetSelling(String companyName, String assetName, String username, String state, String sellPrice, String remain);

    List<AssetSelling> findAllSellingAssetName(String assetName);

    void deleteAssetSelling(AssetSelling assetSelling);

    List<MarketAsset> findAllMarketPriceByAssetName(String name);

    void insertNewMarketasset(String text);

    List<AssetSelling> findAllSellingAssetNameAndCompanyName(String assetName, String companyName);

    List<AssetSelling> findAllBuyingAssetNameAndCompanyName(String assetName,String companyName);

    void updateAssetSellingQuantity(AssetSelling assetSelling);

}
