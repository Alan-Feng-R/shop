package test;

import dao.AdministratorDao;
import domain.Administrator;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestAdministrator {
    @Test
    public void TestFindAdministratorWithMock(){
        Administrator administrator=new Administrator();
        AdministratorDao administratorDao = EasyMock.createMock(AdministratorDao.class);
        EasyMock.expect(administratorDao.findOne("001","123")).andReturn(administrator);
        EasyMock.replay(administratorDao);
        Administrator administrator1 = administratorDao.findOne("001", "123");
        assertNotNull(administrator1);
        assertEquals(administrator,administrator1);
        EasyMock.verify(administratorDao);
    }
    @Test
    public void TestFindAllAdministratorWithMock(){
        AdministratorDao administratorDao = EasyMock.createMock(AdministratorDao.class);
        List<Administrator> list=new ArrayList<>();
        EasyMock.expect(administratorDao.findAll()).andReturn(list);
        EasyMock.replay(administratorDao);
        List<Administrator> list1 = administratorDao.findAll();
        assertNotNull(list1);
        assertEquals(list,list1);
        EasyMock.verify(administratorDao);
    }
}
