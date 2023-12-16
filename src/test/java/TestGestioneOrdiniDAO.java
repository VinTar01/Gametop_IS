    package test.java;

import gestioneAccount.UtenteBean;
import gestioneAcquisti.OrdineBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
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
import test.java.GestioneOrdiniModelDS;
import test.java.OrdineModelDS;
import test.java.RegistrazioneModelDS;

public class TestGestioneOrdiniDAO {
   private Connection db;
   private GestioneOrdiniModelDS ordineDAO;
   private OrdineBean ordineEsistente;
   private OrdineBean ordineEsistente2;
   private OrdineBean ordineNonEsistente;
   private UtenteBean cliente;
   private UtenteBean cliente2;
   private UtenteBean cliente3;
   private RegistrazioneModelDS userDAO;
   private OrdineModelDS pagaDS;
   private static IDatabaseTester tester;
   private DataSource ds;

   @BeforeEach
   public void setUp() throws SQLException, Exception {
      tester = new JdbcDatabaseTester(Driver.class.getName(), "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "PROVA", "");
      tester.setSetUpOperation(DatabaseOperation.REFRESH);
      tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
      this.ds = (DataSource)Mockito.mock(DataSource.class);
      Mockito.when(this.ds.getConnection()).thenReturn(tester.getConnection().getConnection());
      this.ordineDAO = new GestioneOrdiniModelDS(this.ds);
      this.userDAO = new RegistrazioneModelDS(this.ds);
      this.pagaDS = new OrdineModelDS(this.ds);
      this.cliente = new UtenteBean();
      this.cliente.setNome("Francesco9");
      this.cliente.setCognome("Leo9");
      this.cliente.setEmail("francesco1@gmail.com");
      this.cliente.setIndirizzo("Via San Francesco 29");
      this.cliente.setPassword("ABCD1234");
      this.userDAO.doSave(this.cliente);
      this.cliente2 = new UtenteBean();
      this.cliente2.setNome("Francesco2");
      this.cliente2.setCognome("Leo2");
      this.cliente2.setEmail("francesco2@gmail.com");
      this.cliente2.setIndirizzo("Via San Francesco 29");
      this.cliente2.setPassword("ABCD1234");
      this.userDAO.doSave(this.cliente2);
      this.cliente3 = new UtenteBean();
      this.cliente3.setNome("Francesco3");
      this.cliente3.setCognome("Leo3");
      this.cliente3.setEmail("francesco3@gmail.com");
      this.cliente3.setIndirizzo("Via San Francesco 29");
      this.cliente3.setPassword("ABCD1234");
      this.userDAO.doSave(this.cliente3);
      this.ordineEsistente = new OrdineBean();
      this.ordineEsistente.setEmail("francesco1@gmail.com");
      this.ordineEsistente.setNome("Francesco");
      this.ordineEsistente.setCognome("Leo");
      this.ordineEsistente.setIndirizzo("Via San Francesco 30");
      this.ordineEsistente.setCap("83100");
      this.ordineEsistente.setComune("Mercogliano");
      this.ordineEsistente.setProvincia("Avellino");
      this.ordineEsistente.setPrezzo("230");
      this.ordineEsistente.setProdotti("5");
      this.ordineEsistente.setControllato("false");
      this.pagaDS.SalvaOrdine(this.ordineEsistente);
      this.ordineEsistente2 = new OrdineBean();
      this.ordineEsistente2.setEmail("francesco2@gmail.com");
      this.ordineEsistente2.setNome("Francesco2");
      this.ordineEsistente2.setCognome("Leo2");
      this.ordineEsistente2.setIndirizzo("Via San Francesco 32");
      this.ordineEsistente2.setCap("83102");
      this.ordineEsistente2.setComune("Mercogliano2");
      this.ordineEsistente2.setProvincia("Avellino2");
      this.ordineEsistente2.setPrezzo("2302");
      this.ordineEsistente2.setProdotti("52");
      this.ordineEsistente2.setControllato("false");
      this.pagaDS.SalvaOrdine(this.ordineEsistente2);
   }

   @Test
   public void TestFindOrdineByNumeroOrdine() throws SQLException {
      this.ordineEsistente = this.ordineDAO.doRetrieveByKey("1");
      System.out.println("OrdineEsistente" + this.ordineEsistente.getNome() + this.ordineEsistente.getNumeroOrdine());
      Assertions.assertEquals(this.ordineEsistente, this.ordineDAO.doRetrieveByKey("1"));
   }

   @Test
   public void TestFindOrdineByNumeroNonOrdine() throws SQLException {
      Assertions.assertEquals((Object)null, this.ordineDAO.doRetrieveByKey("100").getNumeroOrdine());
   }

   @Test
   public void TestFindOrdineByNumeroNonOrdineNull() throws SQLException {
      Assertions.assertThrows(NumberFormatException.class, () -> {
         this.ordineDAO.doRetrieveByKey(Integer.parseInt((String)null));
      });
   }

   @Test
   public void TestFindTuttiOrdini() throws SQLException {
      new LinkedList<OrdineBean>();
      Collection<OrdineBean> actual = this.ordineDAO.ritornaTuttiOrdiniDaControllare();
      Collection<OrdineBean> expected = new LinkedList<OrdineBean>();
      OrdineBean ordineEsistenteCollectio = new OrdineBean();
      ordineEsistenteCollectio.setNumeroOrdine("1");
      ordineEsistenteCollectio.setEmail("francesco1@gmail.com");
      ordineEsistenteCollectio.setNome("Francesco");
      ordineEsistenteCollectio.setCognome("Leo");
      ordineEsistenteCollectio.setIndirizzo("Via San Francesco 30");
      ordineEsistenteCollectio.setCap("83100");
      ordineEsistenteCollectio.setComune("Mercogliano");
      ordineEsistenteCollectio.setProvincia("Avellino");
      ordineEsistenteCollectio.setPrezzo("230");
      ordineEsistenteCollectio.setProdotti("5");
      ordineEsistenteCollectio.setControllato("false");
      expected.add(ordineEsistenteCollectio);
      OrdineBean ordineEsistenteCollectio2 = new OrdineBean();
      ordineEsistenteCollectio2.setNumeroOrdine("2");
      ordineEsistenteCollectio2.setEmail("francesco2@gmail.com");
      ordineEsistenteCollectio2.setNome("Francesco2");
      ordineEsistenteCollectio2.setCognome("Leo2");
      ordineEsistenteCollectio2.setIndirizzo("Via San Francesco 32");
      ordineEsistenteCollectio2.setCap("83102");
      ordineEsistenteCollectio2.setComune("Mercogliano2");
      ordineEsistenteCollectio2.setProvincia("Avellino2");
      ordineEsistenteCollectio2.setPrezzo("2302");
      ordineEsistenteCollectio2.setProdotti("52");
      ordineEsistenteCollectio2.setControllato("false");
      expected.add(ordineEsistenteCollectio2);
      Iterator<OrdineBean> var6 = expected.iterator();

      OrdineBean prod;
      while(var6.hasNext()) {
         prod = (OrdineBean)var6.next();
         System.out.println("Ordini inseriti" + prod.getNumeroOrdine());
      }

      var6 = actual.iterator();

      while(var6.hasNext()) {
         prod = (OrdineBean)var6.next();
         System.out.println("Ordini Presenti" + prod.getNumeroOrdine());
         System.out.println("Ordini Presenti" + prod.getEmail());
      }

      Assertions.assertEquals(expected.size(), this.ordineDAO.ritornaTuttiOrdiniDaControllare().size());
   }

   @Test
   public void TestUpdateOrdine() throws SQLException {
      OrdineBean ordineEsistenteDaModificare = new OrdineBean();
      System.out.println("Ordini da modificare" + this.ordineEsistente2.getNumeroOrdine());
      ordineEsistenteDaModificare.setNumeroOrdine("1");
      ordineEsistenteDaModificare.setEmail("francesco2@gmail.com");
      ordineEsistenteDaModificare.setNome("Francesco3");
      ordineEsistenteDaModificare.setCognome("Ciccone3");
      ordineEsistenteDaModificare.setIndirizzo("Via San Francesco 330");
      ordineEsistenteDaModificare.setCap("83100");
      ordineEsistenteDaModificare.setComune("Montella");
      ordineEsistenteDaModificare.setProvincia("Avellino");
      ordineEsistenteDaModificare.setPrezzo("230");
      ordineEsistenteDaModificare.setProdotti("5");
      ordineEsistenteDaModificare.setControllato("false");
      Assertions.assertEquals(true, this.ordineDAO.doUpdate(ordineEsistenteDaModificare));
   }

   @Test
   public void TestUpdateOrdineNonEsistente() throws SQLException {
      OrdineBean ordineEsistenteDaModificare = new OrdineBean();
      System.out.println("Ordini da modificare" + this.ordineEsistente2.getNumeroOrdine());
      ordineEsistenteDaModificare.setNumeroOrdine("0");
      ordineEsistenteDaModificare.setEmail("");
      ordineEsistenteDaModificare.setNome("Francesco3");
      ordineEsistenteDaModificare.setCognome("Ciccone3");
      ordineEsistenteDaModificare.setIndirizzo("Via San Francesco 330");
      ordineEsistenteDaModificare.setCap("83100");
      ordineEsistenteDaModificare.setComune("Montella");
      ordineEsistenteDaModificare.setProvincia("Avellino");
      ordineEsistenteDaModificare.setPrezzo("230");
      ordineEsistenteDaModificare.setProdotti("5");
      ordineEsistenteDaModificare.setControllato("false");
      Assertions.assertEquals(false, this.ordineDAO.doUpdate(ordineEsistenteDaModificare));
   }

   @Test
   public void TestUpdateOrdineNull() throws SQLException {
      Assertions.assertThrows(NullPointerException.class, () -> {
         this.ordineDAO.doUpdate((OrdineBean)null);
      });
   }

   @Test
   public void TestDeleteOrdineNull() throws SQLException {
      Assertions.assertThrows(NullPointerException.class, () -> {
         this.ordineDAO.doDelete((OrdineBean)null);
      });
   }

   @Test
   public void TestDeleteOrdineEsistente() throws SQLException {
      Assertions.assertEquals(true, this.ordineDAO.doDelete(this.ordineEsistente));
      Assert.assertNotEquals(this.ordineEsistente, this.ordineDAO.doRetrieveByKey("1"));
   }

   @Test
   public void TestDeleteOrdineNonEsistente() throws SQLException {
      Assert.assertNotEquals(this.ordineNonEsistente, this.ordineDAO.doRetrieveByKey("200"));
   }

   @Test
   public void TestConfermaOrdineNonEsistente() throws SQLException {
      Assertions.assertThrows(NullPointerException.class, () -> {
         this.ordineDAO.confermaOrdine(this.ordineNonEsistente);
      });
   }

   @Test
   public void TestConfermaOrdineEsistente() throws SQLException {
      Assertions.assertEquals(true, this.ordineDAO.confermaOrdine(this.ordineEsistente));
   }

   @Test
   public void TestConfermaOrdineNull() throws SQLException {
      Assertions.assertThrows(NullPointerException.class, () -> {
         this.ordineDAO.confermaOrdine((OrdineBean)null);
      });
   }

   @AfterEach
   public void tearDown() throws SQLException {
      System.out.println("Sono entrato nella tearDown");
      this.userDAO.doDelete(this.cliente);
      this.userDAO.doDelete(this.cliente2);
      this.userDAO.doDelete(this.cliente3);
      this.ordineDAO.doDelete(this.ordineEsistente2);
      this.ordineDAO.doDelete(this.ordineEsistente);
   }
}
    