   package test.java;

import gestioneAccount.PersonaleBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.h2.Driver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import test.java.GestoreRuoliModelDS;

public class TestGestioneRuoliDAO {
   private Connection db;
   private GestoreRuoliModelDS personaleDAO;
   private PersonaleBean personaleEsistente;
   private PersonaleBean personaleNonEsistente;
   private static IDatabaseTester tester;
   private DataSource ds;

   @Before
   public void setUp() throws SQLException, Exception {
      tester = new JdbcDatabaseTester(Driver.class.getName(), "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "PROVA", "");
      tester.setSetUpOperation(DatabaseOperation.REFRESH);
      tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
      this.ds = (DataSource)Mockito.mock(DataSource.class);
      Mockito.when(this.ds.getConnection()).thenReturn(tester.getConnection().getConnection());
      this.personaleDAO = new GestoreRuoliModelDS(this.ds);
      this.personaleEsistente = new PersonaleBean();
      this.personaleEsistente.setNome("Francesco1");
      this.personaleEsistente.setCognome("Leo1");
      this.personaleEsistente.setEmail("francesco1@gmail.com");
      this.personaleEsistente.setPassword("ABCD1234");
      this.personaleEsistente.setRuolo("Gestore Ordini");
      this.personaleDAO.inserisciPersonale(this.personaleEsistente);
      this.personaleNonEsistente = new PersonaleBean();
      this.personaleNonEsistente.setNome("Francesco2");
      this.personaleNonEsistente.setCognome("Leo2");
      this.personaleNonEsistente.setEmail("francesco2@gmail.com");
      this.personaleNonEsistente.setPassword("ABCD1234");
      this.personaleNonEsistente.setRuolo("Gestore Ordini");
   }

   @Test
   public void TestFindPersonaleByEmailExisting() throws SQLException {
      Assertions.assertEquals(this.personaleEsistente, this.personaleDAO.doRetrieveByKey("francesco1@gmail.com"));
   }

   @Test
   public void TestFindPersonaleByEmailNull() throws SQLException {
      Assert.assertNotEquals(this.personaleEsistente, this.personaleDAO.doRetrieveByKey((String)null));
   }

   @Test
   public void TestFindPersonaleByEmailNotExisting() throws SQLException {
      Assert.assertNotEquals(this.personaleNonEsistente, this.personaleDAO.doRetrieveByKey("francesco2@gmail.com"));
   }

   @Test
   public void TestDeletePersonale() throws SQLException {
      this.personaleDAO.doDelete(this.personaleEsistente);
      Assert.assertNotEquals(this.personaleEsistente, this.personaleDAO.doRetrieveByKey("francesco2@gmail.com"));
   }

   @Test
   public void TestDeletePersonaleNonEsistente() throws SQLException {
      Assertions.assertEquals(false, this.personaleDAO.doDelete(this.personaleNonEsistente));
   }

   @Test
   public void TestDeletePersonaleNull() throws SQLException {
      Assertions.assertThrows(NullPointerException.class, () -> {
         this.personaleDAO.doDelete((PersonaleBean)null);
      });
   }

   @Test
   public void TestFindTuttoPersonale() throws SQLException {
      Collection<PersonaleBean> expected = new LinkedList();
      expected.add(this.personaleEsistente);
      Assertions.assertEquals(expected, this.personaleDAO.stampaTuttoIlPersonale());
   }

   @Test
   public void TestUpdatePersonale() throws SQLException {
      this.personaleEsistente.setNome("Francesco3");
      this.personaleEsistente.setCognome("Leo3");
      this.personaleEsistente.setPassword("ABCD1234");
      this.personaleEsistente.setRuolo("Gestore prodotti");
      this.personaleDAO.doUpdate(this.personaleEsistente);
      this.personaleEsistente = this.personaleDAO.doRetrieveByKey("francesco1@gmail.com");
      Assertions.assertEquals(this.personaleEsistente.getNome(), this.personaleDAO.doRetrieveByKey("francesco1@gmail.com").getNome());
      Assertions.assertEquals(this.personaleEsistente.getCognome(), this.personaleDAO.doRetrieveByKey("francesco1@gmail.com").getCognome());
      Assertions.assertEquals(this.personaleEsistente.getEmail(), this.personaleDAO.doRetrieveByKey("francesco1@gmail.com").getEmail());
      Assertions.assertEquals(this.personaleEsistente.getPassword(), this.personaleDAO.doRetrieveByKey("francesco1@gmail.com").getPassword());
      Assertions.assertEquals(this.personaleEsistente.getRuolo(), this.personaleDAO.doRetrieveByKey("francesco1@gmail.com").getRuolo());
   }

   @Test
   public void TestUpdatePersonaleNonEsistente() throws SQLException {
      this.personaleEsistente.setNome("Francesco3");
      this.personaleEsistente.setCognome("Leo3");
      this.personaleEsistente.setPassword("ABCD1234");
      this.personaleEsistente.setRuolo("Gestore prodotti");
      Assertions.assertEquals(false, this.personaleDAO.doUpdate(this.personaleNonEsistente));
   }

   @Test
   public void TestUpdatePersonaleNull() throws SQLException {
      Assertions.assertEquals(false, this.personaleDAO.doUpdate((PersonaleBean)null));
   }

   @After
   public void tearDown() throws SQLException {
      System.out.println("Sono entrato nella tearDown");
      this.personaleDAO.doDelete(this.personaleEsistente);
      this.personaleDAO.doDelete(this.personaleNonEsistente);
   }
}