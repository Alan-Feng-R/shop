package dao.impl;

import dao.AssetDao;
import domain.AssetSelling;
import domain.MarketAsset;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AssetDaoImpl implements AssetDao {

    @Override
    public List<AssetSelling> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "select * from current_trade ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("username"), dateStr,
                        rs.getString("state"), rs.getString("organisation_name"), rs.getString("price"));
                list.add(assetSelling);
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
    public List<Integer> findAllByAssetName(String assetName, String companyName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT quantity FROM current_trade where asset_name=? and organisation_name=? and state='selling'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            ps.setString(2, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("quantity"));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public List<AssetSelling> findByCompany(String companyName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM current_trade where organisation_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("organisation_name"), rs.getString("price"));
                list.add(assetSelling);
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
    public List<AssetSelling> findSellingAssetName(String assetName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM current_trade where asset_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("organisation_name"), rs.getString("price"));
                list.add(assetSelling);
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
    public List<AssetSelling> findAllSellingAssetName(String assetName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM current_trade where asset_name=? and state='selling'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("organisation_name"), rs.getString("price"));
                list.add(assetSelling);
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
    public List<AssetSelling> findAllSellingAssetNameAndCompanyName(String assetName,String companyName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM current_trade where asset_name=? and state='selling' and organisation_name!=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            ps.setString(2, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("organisation_name"), rs.getString("price"));
                list.add(assetSelling);
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
    public List<AssetSelling> findAllBuyingAssetNameAndCompanyName(String assetName,String companyName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM current_trade where asset_name=? and state='buying' and organisation_name!=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            ps.setString(2, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("organisation_name"), rs.getString("price"));
                list.add(assetSelling);
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
    public void updateAssetSellingQuantity(AssetSelling assetSelling) {
        String sql = "update current_trade set quantity=? where asset_name=? and organisation_name=? and username=? and state=? and price=?";
        JdbcUtil.executeUpdate(sql, assetSelling.getRemainNum(), assetSelling.getAssetName(), assetSelling.getCompanyName(), assetSelling.getUsername(), assetSelling.getState(), assetSelling.getSellingPrice());
    }


    @Override
    public void deleteAssetSelling(AssetSelling assetSelling) {
        String sql = "delete from current_trade where asset_name=? and organisation_name=? and username=? and state=? and price=? and quantity=?";
        JdbcUtil.executeUpdate(sql, assetSelling.getAssetName(), assetSelling.getCompanyName(), assetSelling.getUsername(), assetSelling.getState(), assetSelling.getSellingPrice(), assetSelling.getRemainNum());
    }

    @Override
    public List<MarketAsset> findAllMarketPriceByAssetName(String assetName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM market_price where asset_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            // 4.执行语句
            rs = ps.executeQuery();
            List<MarketAsset> list = new ArrayList<>();
            while (rs.next()) {
                MarketAsset marketAsset = new MarketAsset(rs.getString("asset_name"), rs.getDouble("current_price"), rs.getString("updatetime"));
                list.add(marketAsset);
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
    public void insertNewMarketasset(String assetName) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String format = sdf.format(date);
        String sql1 = "insert into market_price(asset_name,current_price,updatetime) values (?,?,?)";
        JdbcUtil.executeUpdate(sql1, assetName, 9 + Math.random() * 15 % (15 - 9 + 1) , format);
    }

    @Override
    public MarketAsset findAssetByName(String assetName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM market_price where asset_name=? and updatetime=?";
            ps = conn.prepareStatement(sql);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String format = sdf.format(date);
            ps.setString(1, assetName);
            ps.setString(2, format);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new MarketAsset(rs.getString("asset_name"), rs.getDouble("current_price"), rs.getString("updatetime"));
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
    public MarketAsset GetAssetPrice(String assetName, Date date) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM market_price where asset_name=? and updatetime=?";
            ps = conn.prepareStatement(sql);
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(date);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1);//设置为前一天
            date = calendar.getTime(); //得到前一天的时间
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String format = sdf.format(date);
            ps.setString(1, assetName);
            ps.setString(2, format);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new MarketAsset(rs.getString("asset_name"), rs.getDouble("current_price"), rs.getString("updatetime"));
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
    public void insertNewAssetSelling(String companyName, String assetName, String username, String state, String sellPrice, String remain) {
        String sql = "insert into current_trade(asset_name,organisation_name,username,state,price,date,quantity) values (?,?,?,?,?,?,?)";
        JdbcUtil.executeUpdate(sql, assetName, companyName, username, state, sellPrice, new Date(), remain);
    }

    @Override
    public void insertNewAssetPrice(String assetName, double d) {
        Date date = new Date();
        MarketAsset marketAsset = GetAssetPrice(assetName, date);
        while (marketAsset == null) {
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(date);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -1);//设置为前一天
            date = calendar.getTime(); //得到前一天的时间
            marketAsset = GetAssetPrice(assetName, date);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String format = sdf.format(date);
        String sql1 = "insert into market_price(asset_name,current_price,updatetime) values (?,?,?)";
        JdbcUtil.executeUpdate(sql1, assetName, marketAsset.getCurrentPrice() + d, format);

    }
}


