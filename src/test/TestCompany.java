package test;

import dao.CompanyDao;
import domain.Company;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCompany {

    @Test
    public void TestFindAllCompanyWithMock(){
        CompanyDao companyDao=EasyMock.createMock(CompanyDao.class);
        List<Company> companyList=new ArrayList<>();
        EasyMock.expect(companyDao.findAll()).andReturn(companyList);
        EasyMock.replay(companyDao);
        List<Company> list = companyDao.findAll();
        assertEquals(list,companyList);
        EasyMock.verify(companyDao);
    }

    @Test
    public void TestFindCompanyByNameWithMock(){
        CompanyDao companyDao=EasyMock.createMock(CompanyDao.class);
        Company company=new Company();
        EasyMock.expect(companyDao.findByName("company A")).andReturn(company);
        EasyMock.replay(companyDao);
        Company company1 = companyDao.findByName("company A");
        assertEquals(company,company1);
        EasyMock.verify(companyDao);
    }

    @Test
    public void TestSaveCompanyWithMock(){
        CompanyDao companyDao=EasyMock.createMock(CompanyDao.class);
        Company company=new Company();
        companyDao.save(company);
        EasyMock.expectLastCall();
        EasyMock.replay(companyDao);
        companyDao.save(company);
        EasyMock.verify(companyDao);
    }

    @Test
    public void TestUpdateCompanyCreditWithMock(){
        CompanyDao companyDao=EasyMock.createMock(CompanyDao.class);
        String company="company A";
        String credit="800";
        companyDao.updateCredit(company,credit);
        EasyMock.expectLastCall();
        EasyMock.replay(companyDao);
        companyDao.updateCredit(company,credit);
        EasyMock.verify(companyDao);
    }

    @Test
    public void TestInsertCompanyWithMock(){
        CompanyDao companyDao=EasyMock.createMock(CompanyDao.class);
        String company="company A";
        companyDao.insertCompany(company);
        EasyMock.expectLastCall();
        EasyMock.replay(companyDao);
        companyDao.insertCompany(company);
        EasyMock.verify(companyDao);
    }
}
