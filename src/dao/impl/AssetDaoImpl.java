package dao.impl;

import dao.AssetDao;
import domain.AssetInfo;
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
            String sql = "select * from asset_selling ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("uploadtime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("remain"), rs.getString("username"), dateStr,
                        rs.getString("state"), rs.getString("company_name"), rs.getString("sell_price"));
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
    public List<AssetInfo> findAssetByCompany(String company) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT asset_name,quantity,company_name,updatetime FROM asset where company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetInfo> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("updatetime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetInfo assetInfo = new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("company_name"), dateStr);
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

    public AssetInfo findAssetByNameAndCompany(String assetName, String company) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT asset_name,quantity,company_name,updatetime FROM asset where asset_name=? and company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            ps.setString(2, company);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetInfo> list = new ArrayList<>();
            if (rs.next()) {
                Date date = rs.getDate("updatetime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                return new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("company_name"), dateStr);
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
        String sql = "update asset set quantity=? where asset_name=? and company_name=?";
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
            String sql = "select asset_name,quantity,company_name,updatetime from asset ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<AssetInfo> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("updatetime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetInfo assetInfo = new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"),
                        rs.getString("company_name"), dateStr);
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
    public List<Integer> findAllByAssetName(String assetName, String companyName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT remain FROM asset_selling where asset_name=? and company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            ps.setString(2, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("remain"));
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
            String sql = "SELECT * FROM asset_selling where company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("uploadtime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("remain"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("company_name"), rs.getString("sell_price"));
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
    public void insertNewAsset(String assetName, String companyName) {
        String sql = "insert into asset(asset_name,company_name,updatetime) values (?,?,?)";
        Date date = new Date();
        JdbcUtil.executeUpdate(sql, assetName, companyName, date);
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
            String sql = "SELECT * FROM asset_selling where asset_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("uploadtime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("remain"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("company_name"), rs.getString("sell_price"));
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
            String sql = "SELECT * FROM asset_selling where asset_name=? and state='selling'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, assetName);
            // 4.执行语句
            rs = ps.executeQuery();
            ArrayList<AssetSelling> list = new ArrayList<>();
            while (rs.next()) {
                Date date = rs.getDate("uploadtime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                AssetSelling assetSelling = new AssetSelling(rs.getString("asset_name"), rs.getString("remain"), rs.getString("username"), dateStr, rs.getString("state"), rs.getString("company_name"), rs.getString("sell_price"));
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
    public AssetInfo findAssetByCompanyAndAssetName(String companyName, String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM asset where asset_name=? and company_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, companyName);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                Date date = rs.getDate("updatetime");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = sdf.format(date);
                return new AssetInfo(rs.getString("asset_name"), rs.getString("quantity"), rs.getString("company_name"), dateStr);
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
    public void deleteAssetSelling(AssetSelling assetSelling) {
        String sql = "delete from asset_selling where asset_name=? and company_name=? and username=? and state=? and sell_price=? and remain=?";
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
            String sql = "SELECT * FROM market_price where asset_name=? order by updatetime";
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
    public void updateSellingQuantity(String companyName, String assetName, String quantity) {
        String sql = "update asset set quantity=? where asset_name=? and company_name=?";
        JdbcUtil.executeUpdate(sql, quantity, assetName, companyName);

    }

    @Override
    public void insertNewAssetSelling(String companyName, String assetName, String username, String state, String sellPrice, String remain) {
        String sql = "insert into asset_selling(asset_name,company_name,username,state,sell_price,uploadtime,remain) values (?,?,?,?,?,?,?)";
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


