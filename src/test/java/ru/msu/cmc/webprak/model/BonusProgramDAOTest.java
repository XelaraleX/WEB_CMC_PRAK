package ru.msu.cmc.webprak.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.msu.cmc.webprak.model.dao.UsersDAO;
import ru.msu.cmc.webprak.model.entity.Users;
import ru.msu.cmc.webprak.model.dao.AirlinesDAO;
import ru.msu.cmc.webprak.model.entity.Airlines;
import ru.msu.cmc.webprak.model.dao.BonusProgramDAO;
import ru.msu.cmc.webprak.model.entity.BonusProgram;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusProgramDAOTest {
    BonusProgramDAO dao;
    String runId;
    Airlines airlines1;
    Airlines airlines2;
    Users users1;
    Users users2;
    BonusProgram bonusProgram1;
    BonusProgram bonusProgram2;

    @BeforeEach
    public void setUp() {
        this.dao = DAOFactory.getInstance().getBonusProgramDAO();
        this.runId = UUID.randomUUID().toString();

        this.airlines1 = new Airlines();
        this.airlines1.setAirlineName("TestName1" + this.runId);
        this.airlines1.setAirlineEmail("test1" + this.runId + "@mail.com");
        this.airlines1.setPhoneNumber("+1" + this.runId);

        this.users1 = new Users();
        this.users1.setUserStatus("TestStatus1" + this.runId);
        this.users1.setFullName("TestFullName1" + this.runId);
        this.users1.setAddress("TestAddress1" + this.runId);
        this.users1.setEmail("test1" + this.runId + "@mail.com");
        this.users1.setPhoneNumber("+1" + this.runId);
        this.users1.setUserLogin("TestLogin1" + this.runId);
        this.users1.setUserPassword("TestPassword1" + this.runId);

        this.bonusProgram1 = new BonusProgram();
        this.bonusProgram1.setUserId(users1);
        this.bonusProgram1.setAirlineId(airlines1);
        this.bonusProgram1.setBonusCard("bc1" + this.runId);
        this.bonusProgram1.setCntKm(1);
        this.bonusProgram1.setCntUseKm(1);


        this.airlines2 = new Airlines();
        this.airlines2.setAirlineName("TestName2" + this.runId);
        this.airlines2.setAirlineEmail("test2" + this.runId + "@mail.com");
        this.airlines2.setPhoneNumber("+2" + this.runId);

        this.users2 = new Users();
        this.users2.setUserStatus("TestStatus2" + this.runId);
        this.users2.setFullName("TestFullName2" + this.runId);
        this.users2.setAddress("TestAddress2" + this.runId);
        this.users2.setEmail("test2" + this.runId + "@mail.com");
        this.users2.setPhoneNumber("+2" + this.runId);
        this.users2.setUserLogin("TestLogin2" + this.runId);
        this.users2.setUserPassword("TestPassword2" + this.runId);

        this.bonusProgram2 = new BonusProgram();
        this.bonusProgram2.setUserId(users2);
        this.bonusProgram2.setAirlineId(airlines2);
        this.bonusProgram2.setBonusCard("bc2" + this.runId);
        this.bonusProgram2.setCntKm(2);
        this.bonusProgram2.setCntUseKm(2);

        AirlinesDAO airlinesDAO = DAOFactory.getInstance().getAirlinesDAO();
        airlinesDAO.add(airlines1);
        airlinesDAO.add(airlines2);

        UsersDAO usersDAO = DAOFactory.getInstance().getUsersDAO();
        usersDAO.add(users1);
        usersDAO.add(users2);

        this.dao.add(bonusProgram1);
        this.dao.add(bonusProgram2);
    }

    @AfterEach
    public void cleanUp() {
        this.dao.delete(bonusProgram1);
        this.dao.delete(bonusProgram2);

        AirlinesDAO airlinesDAO = DAOFactory.getInstance().getAirlinesDAO();
        airlinesDAO.delete(this.airlines1);
        airlinesDAO.delete(this.airlines2);

        UsersDAO usersDAO = DAOFactory.getInstance().getUsersDAO();
        usersDAO.delete(this.users1);
        usersDAO.delete(this.users2);

        this.dao = null;
        this.runId = null;
        this.airlines1 = null;
        this.airlines2 = null;
        this.users1 = null;
        this.users2 = null;
        this.bonusProgram1 = null;
        this.bonusProgram2 = null;
    }

    @Test
    public void testGetByBonusCard() {
        Collection<BonusProgram> all = this.dao.getBonusProgramByFilter(
                BonusProgramDAO.getFilterBuilder()
                        .bonusCard(this.runId)
                        .build()
        );
        Set<BonusProgram> expected = new HashSet<>();
        expected.add(this.bonusProgram1);
        expected.add(this.bonusProgram2);

        Set<BonusProgram> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<BonusProgram> onlyTest1 = this.dao.getBonusProgramByFilter(
                BonusProgramDAO.getFilterBuilder()
                        .bonusCard("bc1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.bonusProgram1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByCntKm() {
        Collection<BonusProgram> all = this.dao.getBonusProgramByFilter(
                BonusProgramDAO.getFilterBuilder()
                        .cntKm(2)
                        .build()
        );
        Set<BonusProgram> expected = new HashSet<>();
        expected.add(this.bonusProgram1);
        expected.add(this.bonusProgram2);

        Set<BonusProgram> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<BonusProgram> onlyTest1 = this.dao.getBonusProgramByFilter(
                BonusProgramDAO.getFilterBuilder()
                        .cntKm(1)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.bonusProgram1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByCntUseKm() {
        Collection<BonusProgram> all = this.dao.getBonusProgramByFilter(
                BonusProgramDAO.getFilterBuilder()
                        .cntUseKm(2)
                        .build()
        );
        Set<BonusProgram> expected = new HashSet<>();
        expected.add(this.bonusProgram1);
        expected.add(this.bonusProgram2);

        Set<BonusProgram> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<BonusProgram> onlyTest1 = this.dao.getBonusProgramByFilter(
                BonusProgramDAO.getFilterBuilder()
                        .cntUseKm(1)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.bonusProgram1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }
}
