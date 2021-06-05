package test;

import dao.UserDao;
import org.easymock.EasyMock;
import org.junit.Test;


public class TestForget {

    @Test
    public void TestUserUpdateWithMock(){
        UserDao userDao= EasyMock.createMock(UserDao.class);
        userDao.updateUser("001","111");
        EasyMock.expectLastCall();
        EasyMock.replay(userDao);
        userDao.updateUser("001","111");
        EasyMock.verify(userDao);
    }
}
