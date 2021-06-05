package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

public class TestLogin {



    /**
     * 测试用户登录
     * */
    @Test
    public void TestUserLogin(){

        UserDaoImpl userDao=new UserDaoImpl();
        //正确的用户密码
        User user = userDao.findUser("001", "123");
        if (user!=null){
            System.out.println(user+"test success!");
        }else{
            System.out.println("failed!");
        }
        //错误的用户密码
        User user1 = userDao.findUser("111", "123");
        if (user1!=null){
            System.out.println(user+"test success!");
        }else{
            System.out.println("failed!");
        }
//        User expectedUser = new User(1,"001","123","company A");
    }

    @Test
    public void TestUserLoginWithMock(){
        User user=new User();
        UserDao userDao=EasyMock.createMock(UserDao.class);
        EasyMock.expect(userDao.findUser("001","123")).andReturn(user);
        EasyMock.replay(userDao);
        User user1 = userDao.findUser("001", "123");
        assertNotNull(user);
        assertEquals(user,user1);
        EasyMock.verify(userDao);
    }
}
