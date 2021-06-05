package dao.impl;

import dao.AssetInfoDao;
import domain.AssetInfo;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetInfoDaoImpl implements AssetInfoDao {

    @Override
    public List<AssetInfo> findAssetByCompany(String company) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM asset_info where organisation_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetInfo> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetInfo assetInfo = new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("organisation_name"), dateStr);
                list.add(assetInfo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public AssetInfo findAssetByNameAndCompany(String assetName, String company) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM asset_info where asset_name=? and organisation_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            ps.setString(2, company);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetInfo> list = new ArrayList<>();
            if (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                return new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("organisation_name"), dateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public void updateQuantity(String company, String assetName, String quantity) {
        String sql = "update asset_info set quantity=? where asset_name=? and organisation_name=?";
        JdbcUtil.executeUpdate(sql, quantity, assetName, company);
    }

    @Override
    public List<AssetInfo> findAllAssetName() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "SELECT * FROM asset_info ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<AssetInfo> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetInfo assetInfo = new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"),
                        rs.getString("organisation_name"), dateStr);
                list.add(assetInfo);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }
        return null;
    }

    @Override
    public void updateSellingQuantity(String companyName, String assetName, String quantity) {
        String sql = "update asset_info set quantity=? where asset_name=? and organisation_name=?";
        JdbcUtil.executeUpdate(sql, quantity, assetName, companyName);

    }

    @Override
    public void insertNewAsset(String assetName, String companyName) {
        String sql = "insert into asset_info(asset_name,organisation_name) values (?,?)";
        JdbcUtil.executeUpdate(sql, assetName, companyName);
    }

    @Override
    public List<String> findAssetType() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "SELECT DISTINCT type FROM asset_type ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString("type"));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }
        return null;
    }

    @Override
    public void insertNewAssetType(String type) {
        String sql = "insert into asset_type(type) values (?)";
        JdbcUtil.executeUpdate(sql,type);
    }

    @Override
    public AssetInfo findAssetByCompanyAndAssetName(String companyName, String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM asset_info where asset_name=? and organisation_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                return new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("organisation_name"), dateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}
