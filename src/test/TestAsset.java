package test;


import dao.AssetDao;
import domain.AssetInfo;
import domain.AssetSelling;
import domain.MarketAsset;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestAsset {

    @Test
    public void TestFindAllSellingAssetWithMock(){
        AssetDao assetDao=EasyMock.createMock(AssetDao.class);
        List<AssetSelling> list=new ArrayList<>();
        EasyMock.expect(assetDao.findAll()).andReturn(list);
        EasyMock.replay(assetDao);
        List<AssetSelling> list1 = assetDao.findAll();
        assertNotNull(list1);
        assertEquals(list,list1);
        EasyMock.verify(assetDao);
    }

    @Test
    public void TestFindAllSellingAssetByNameWithMock(){
        AssetDao assetDao=EasyMock.createMock(AssetDao.class);
        List<AssetSelling> list=new ArrayList<>();
        String assetName="test1";
        EasyMock.expect(assetDao.findSellingAssetName(assetName)).andReturn(list);
        EasyMock.replay(assetDao);
        List<AssetSelling> list1 = assetDao.findSellingAssetName(assetName);
        assertNotNull(list1);
        assertEquals(list,list1);
        EasyMock.verify(assetDao);
    }

    @Test
    public void TestFindAllSellingAssetByCompanyNameWithMock(){
        AssetDao assetDao=EasyMock.createMock(AssetDao.class);
        List<AssetSelling> list=new ArrayList<>();
        String companyName="company A";
        EasyMock.expect(assetDao.findByCompany(companyName)).andReturn(list);
        EasyMock.replay(assetDao);
        List<AssetSelling> list1 = assetDao.findByCompany(companyName);
        assertNotNull(list1);
        assertEquals(list,list1);
        EasyMock.verify(assetDao);
    }

    @Test
    public void TestFindAssetByCompanyNameWithMock(){
        AssetDao assetDao=EasyMock.createMock(AssetDao.class);
        List<AssetInfo> list=new ArrayList<>();
        EasyMock.expect(assetDao.findAllAssetName()).andReturn(list);
        EasyMock.replay(assetDao);
        List<AssetInfo> list1 = assetDao.findAllAssetName();
        assertNotNull(list1);
        assertEquals(list,list1);
        EasyMock.verify(assetDao);
    }

    @Test
    public void TestFindAssetByCompanyAndAssetNameWithMock(){
        AssetDao assetDao=EasyMock.createMock(AssetDao.class);
        AssetInfo assetInfo=new AssetInfo();
        String assetName="test1";
        String companyName="company A";
        EasyMock.expect(assetDao.findAssetByCompanyAndAssetName(assetName,companyName)).andReturn(assetInfo);
        EasyMock.replay(assetDao);
        AssetInfo assetInfo1 = assetDao.findAssetByCompanyAndAssetName(assetName, companyName);
        assertNotNull(assetInfo1);
        assertEquals(assetInfo,assetInfo1);
        EasyMock.verify(assetDao);
    }

    @Test
    public void TestGetAssetPriceNameWithMock(){
        AssetDao assetDao=EasyMock.createMock(AssetDao.class);
        String assetName="test1";
        MarketAsset marketAsset=new MarketAsset();
        Date date=new Date();
        EasyMock.expect(assetDao.GetAssetPrice(assetName,date)).andReturn(marketAsset);
        EasyMock.replay(assetDao);
        MarketAsset marketAsset1 = assetDao.GetAssetPrice(assetName, date);
        assertNotNull(marketAsset1);
        assertEquals(marketAsset,marketAsset1);
        EasyMock.verify(assetDao);
    }

    @Test
    public void TestFindAllAssetPriceWithMock(){
        AssetDao assetDao=EasyMock.createMock(AssetDao.class);
        String assetName="test1";
        List<MarketAsset> list=new ArrayList<>();
        EasyMock.expect(assetDao.findAllMarketPriceByAssetName(assetName)).andReturn(list);
        EasyMock.replay(assetDao);
        List<MarketAsset> list1 = assetDao.findAllMarketPriceByAssetName(assetName);
        assertNotNull(list1);
        assertEquals(list,list1);
        EasyMock.verify(assetDao);
    }
}
