package dao;

import domain.AssetInfo;

import java.util.List;

public interface AssetInfoDao {

    List<AssetInfo> findAssetByCompany(String company);

    AssetInfo findAssetByNameAndCompany(String assetName, String company);

    AssetInfo findAssetByCompanyAndAssetName(String companyName, String name);

    void updateQuantity(String company, String assetName, String quantity);

    List<AssetInfo> findAllAssetName();

    void updateSellingQuantity(String companyName, String assetName, String quantity);

    void insertNewAsset(String assetName, String companyName);

    List<String> findAssetType();

    void insertNewAssetType(String text);
}
