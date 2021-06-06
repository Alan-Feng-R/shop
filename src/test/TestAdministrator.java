package test;

import dao.UserDao;
import domain.User;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestAdministrator {
    @Test
    public void TestFindAdministratorWithMock(){
        User administrator=new User();
        UserDao userDao = EasyMock.createMock(UserDao.class);
        EasyMock.expect(userDao.findOne("001","123")).andReturn(administrator);
        EasyMock.replay(userDao);
        User administrator1 = userDao.findOne("001", "123");
        assertNotNull(administrator1);
        assertEquals(administrator,administrator1);
        EasyMock.verify(userDao);
    }
    @Test
    public void TestFindAllAdministratorWithMock(){
        UserDao userDao = EasyMock.createMock(UserDao.class);
        List<User> list=new ArrayList<>();
        EasyMock.expect(userDao.findAll()).andReturn(list);
        EasyMock.replay(userDao);
        List<User> list1 = userDao.findAllAdmin();
        assertNotNull(list1);
        assertEquals(list,list1);
        EasyMock.verify(userDao);
    }
}
