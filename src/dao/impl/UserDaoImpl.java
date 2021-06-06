package dao.impl;

import dao.UserDao;
import domain.User;
import util.JdbcUtil;
import util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUser(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM user_info where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, PasswordUtil.md5(password + PasswordUtil.SALT));
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"), rs.getInt("account_type"), rs.getString("organisation_name"));
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
    public User findByUserName(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "SELECT * FROM user_info where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"), rs.getInt("account_type"), rs.getString("organisation_name"));
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
    public void updateUser(String username, String password) {
        String MD5pwd = PasswordUtil.md5(password + PasswordUtil.SALT);
        String sql = "update user_info set password=? where username=?";
        JdbcUtil.executeUpdate(sql, MD5pwd, username);
    }

    @Override
    public List<User> findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "select * from user_info ";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getInt("account_type"), rs.getString("organisation_name"));
                list.add(user);
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
    public void save(User user) {
        String MD5pwd = PasswordUtil.md5(user.getPassword() + PasswordUtil.SALT);
        String sql = "insert into user_info(username,password,account_type,organisation_name) values (?,?,?,?)";
        JdbcUtil.executeUpdate(sql, user.getUsername(), MD5pwd, User.USER_TYPE, user.getCompanyName());
    }

    @Override
    public void saveAdmin(User user) {
        String MD5pwd = PasswordUtil.md5(user.getPassword() + PasswordUtil.SALT);
        String sql = "insert into user_info(username,password,account_type) values (?,?,?)";
        JdbcUtil.executeUpdate(sql, user.getUsername(), MD5pwd, User.ADMIN_TYPE);
    }

    @Override
    public List<User> findAllAdmin() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            st = conn.createStatement();
            String sql = "select * from user_info where type=1";
            // 4.执行语句
            rs = st.executeQuery(sql);
            // 创建一个集合
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User administrator = new User(rs.getString("adname"), rs.getString("password"), rs.getInt("aid"), null
                );
                list.add(administrator);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }
        return null;
    }

    public User findOne(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "select * from user_info where username=? and password=? and type=1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, PasswordUtil.md5(password + PasswordUtil.SALT));
            // 4.执行语句
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("adname"), rs.getString("password"), rs.getInt("type"), null);
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
