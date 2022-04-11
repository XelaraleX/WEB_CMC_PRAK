package ru.msu.cmc.webprak.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.msu.cmc.webprak.model.dao.UsersDAO;
import ru.msu.cmc.webprak.model.entity.Users;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersDAOTest {
    UsersDAO dao;
    Users usersTest1;
    Users usersTest2;
    String runId;

    @BeforeEach
    public void setUp() {
        this.dao = DAOFactory.getInstance().getUsersDAO();
        this.runId = UUID.randomUUID().toString();

        this.usersTest1 = new Users();
        this.usersTest1.setUserStatus("TestStatus1" + this.runId);
        this.usersTest1.setFullName("TestFullName1" + this.runId);
        this.usersTest1.setAddress("TestAddress1" + this.runId);
        this.usersTest1.setEmail("test1" + this.runId + "@mail.com");
        this.usersTest1.setPhoneNumber("+1" + this.runId);
        this.usersTest1.setUserLogin("TestLogin1" + this.runId);
        this.usersTest1.setUserPassword("TestPassword1" + this.runId);

        this.usersTest2 = new Users();
        this.usersTest2.setUserStatus("TestStatus2" + this.runId);
        this.usersTest2.setFullName("TestFullName2" + this.runId);
        this.usersTest2.setAddress("TestAddress2" + this.runId);
        this.usersTest2.setEmail("test2" + this.runId + "@mail.com");
        this.usersTest2.setPhoneNumber("+2" + this.runId);
        this.usersTest2.setUserLogin("TestLogin2" + this.runId);
        this.usersTest2.setUserPassword("TestPassword2" + this.runId);

        this.dao.add(usersTest1);
        this.dao.add(usersTest2);
    }

    @AfterEach
    public void cleanUp() {
        this.dao.delete(usersTest1);
        this.dao.delete(usersTest2);

        this.dao = null;
        this.runId = null;
        this.usersTest1 = null;
        this.usersTest2 = null;
    }

    @Test
    public void testGetByUserStatus() {
        Collection<Users> all = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .userStatus(this.runId)
                        .build()
        );
        Set<Users> expected = new HashSet<>();
        expected.add(this.usersTest1);
        expected.add(this.usersTest2);

        Set<Users> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Users> onlyTest1 = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .userStatus("TestStatus1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.usersTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByFullName() {
        Collection<Users> all = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .fullName(this.runId)
                        .build()
        );
        Set<Users> expected = new HashSet<>();
        expected.add(this.usersTest1);
        expected.add(this.usersTest2);

        Set<Users> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Users> onlyTest1 = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .fullName("TestFullName1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.usersTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByAddress() {
        Collection<Users> all = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .address(this.runId)
                        .build()
        );
        Set<Users> expected = new HashSet<>();
        expected.add(this.usersTest1);
        expected.add(this.usersTest2);

        Set<Users> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Users> onlyTest1 = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .address("TestAddress1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.usersTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByEmail() {
        Collection<Users> all = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .email(this.runId)
                        .build()
        );
        Set<Users> expected = new HashSet<>();
        expected.add(this.usersTest1);
        expected.add(this.usersTest2);

        Set<Users> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Users> onlyTest1 = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .email("test1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.usersTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByPhoneNumber() {
        Collection<Users> all = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .phoneNumber(this.runId)
                        .build()
        );
        Set<Users> expected = new HashSet<>();
        expected.add(this.usersTest1);
        expected.add(this.usersTest2);

        Set<Users> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Users> onlyTest1 = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .phoneNumber("+1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.usersTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByUserLogin() {
        Collection<Users> all = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .userLogin(this.runId)
                        .build()
        );
        Set<Users> expected = new HashSet<>();
        expected.add(this.usersTest1);
        expected.add(this.usersTest2);

        Set<Users> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Users> onlyTest1 = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .userLogin("TestLogin1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.usersTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByUserPassword() {
        Collection<Users> all = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .userPassword(this.runId)
                        .build()
        );
        Set<Users> expected = new HashSet<>();
        expected.add(this.usersTest1);
        expected.add(this.usersTest2);

        Set<Users> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Users> onlyTest1 = this.dao.getUsersByFilter(
                UsersDAO.getFilterBuilder()
                        .userPassword("TestPassword1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.usersTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }
}
