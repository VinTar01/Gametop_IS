package test.java;



import gestioneAccount.UtenteBean;
import gestioneCarrello.SessionCarrelloBean;
import gestioneProdotti.ShopBean;
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
import test.java.GestioneCarrelloModelDS;
import test.java.RegistrazioneModelDS;
import test.java.ShopModelDS;

public class TestGestioneCarrelloDAO {
   private Connection db;
   private GestioneCarrelloModelDS carrelloDAO;
   private ShopModelDS prodottoDAO;
   private RegistrazioneModelDS userDAO;
   private UtenteBean utenteEsistente;
   private SessionCarrelloBean carrello;
   private ShopBean prodotto1;
   private ShopBean prodotto2;
   private ShopBean prodotto3;
   private static IDatabaseTester tester;
   private DataSource ds;

   @Before
   public void setUp() throws SQLException, Exception {
      tester = new JdbcDatabaseTester(Driver.class.getName(), "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "PROVA", "");
      tester.setSetUpOperation(DatabaseOperation.REFRESH);
      tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
      this.ds = (DataSource)Mockito.mock(DataSource.class);
      Mockito.when(this.ds.getConnection()).thenReturn(tester.getConnection().getConnection());
      this.userDAO = new RegistrazioneModelDS(this.ds);
      this.prodottoDAO = new ShopModelDS(this.ds);
      this.carrelloDAO = new GestioneCarrelloModelDS(this.ds);
      this.utenteEsistente = new UtenteBean();
      this.utenteEsistente.setNome("luigi");
      this.utenteEsistente.setCognome("Sod");
      this.utenteEsistente.setEmail("luigiSod@gmail.com");
      this.utenteEsistente.setIndirizzo("Via San Francesco 29");
      this.utenteEsistente.setPassword("ABCD1234");
      this.userDAO.doSave(this.utenteEsistente);
      this.prodotto1 = new ShopBean();
      this.prodotto1.setCodiceProdotto("CO001");
      this.prodotto1.setIdCategoria("Console");
      this.prodotto1.setQuantitaProdotto(10);
      this.prodotto1.setTitolo("PS5");
      this.prodotto1.setDescrizione("Console SONY Playstation 5");
      this.prodotto1.setPrezzo(500);
      this.prodotto1.setCopertina("uomo_maglia1.png");
      this.prodotto2 = new ShopBean();
      this.prodotto2.setCodiceProdotto("VD002");
      this.prodotto2.setIdCategoria("Videogiochi");
      this.prodotto2.setQuantitaProdotto(20);
      this.prodotto2.setTitolo("Grand Theft Auto 5");
      this.prodotto2.setDescrizione("L'ultimo capitolo della saga Grand Theft Auto");
      this.prodotto2.setPrezzo(50);
      this.prodotto2.setCopertina("uomo_maglia2.png");
      this.prodotto3 = new ShopBean();
      this.prodotto3.setCodiceProdotto("AC003");
      this.prodotto3.setIdCategoria("Accessori");
      this.prodotto3.setQuantitaProdotto(30);
      this.prodotto3.setTitolo("Cuffie Sony");
      this.prodotto3.setDescrizione("L'ultimo modello di cuffie da gaming Sony");
      this.prodotto3.setPrezzo(100);
      this.prodotto3.setCopertina("bimba.maglia3.png");
      this.prodottoDAO.doSave(this.prodotto1);
      this.prodottoDAO.doSave(this.prodotto2);
      this.prodottoDAO.doSave(this.prodotto3);
   }

   @Test
   public void TestAggiungiAlCarrello() throws SQLException {
      String email = "luigiSod@gmail.com";
      String codiceProdotto = "AC003";
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto(codiceProdotto);
      this.carrello.setIdemail(email);
      Assertions.assertEquals(true, this.carrelloDAO.doSave(this.carrello));
   }

   @Test
   public void TestAggiungiAlCarrelloUtenteNonEsistente() throws SQLException {
      String email = "SodNonLog@gmail.com";
      String codiceProdotto = "AC003";
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto(codiceProdotto);
      this.carrello.setIdemail(email);
      Assertions.assertEquals(false, this.carrelloDAO.doSave(this.carrello));
   }

   @Test
   public void TestEliminaTuttoIlCarrello() throws SQLException {
      String email = "luigiSod@gmail.com";
      String codiceProdotto = "AC003";
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto(codiceProdotto);
      this.carrello.setIdemail(email);
      this.carrelloDAO.doSave(this.carrello);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("MG002");
      this.carrello.setIdemail("luigiPP@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      Assertions.assertEquals(true, this.carrelloDAO.deleteAll("luigiPP@gmail.com"));
   }

   @Test
   public void TestRimuoviDalCarrello() throws SQLException {
      String email = "luigiSod@gmail.com";
      String codiceProdotto = "AC003";
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto(codiceProdotto);
      this.carrello.setIdemail(email);
      Assertions.assertEquals(true, this.carrelloDAO.doDelete(this.carrello));
   }

   @Test
   public void TestRitornaCarrello() throws SQLException {
      Collection<ShopBean> expected = new LinkedList<ShopBean>();
      ShopBean shop = new ShopBean();
      shop.setCodiceProdotto("CO001");
      shop.setIdCategoria("Console");
      shop.setQuantitaProdotto(10);
      shop.setTitolo("PS5");
      shop.setDescrizione("Console SONY Playstation 5");
      shop.setPrezzo(500);
      shop.setCopertina("uomo_maglia1.png");
      expected.add(shop);
      shop = new ShopBean();
      shop.setCodiceProdotto("VD002");
      shop.setIdCategoria("Videogiochi");
      shop.setQuantitaProdotto(20);
      shop.setTitolo("Grand Theft Auto 5");
      shop.setDescrizione("L'ultimo capitolo della saga Grand Theft Auto");
      shop.setPrezzo(50);
      shop.setCopertina("uomo_maglia2.png");
      expected.add(shop);
      shop = new ShopBean();
      shop.setCodiceProdotto("AC003");
      shop.setIdCategoria("Accessori");
      shop.setQuantitaProdotto(30);
      shop.setTitolo("Cuffie Sony");
      shop.setDescrizione("L'ultimo modello di cuffie da gaming Sony");
      shop.setPrezzo(100);
      shop.setCopertina("bimba.maglia3.png");
      expected.add(shop);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("CO001");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("VD002");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("AC003");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      Object actual = new LinkedList<ShopBean>();

      try {
         actual = this.carrelloDAO.TrovaCarrello("luigiSod@gmail.com");
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

      Assertions.assertEquals(expected, actual);
      this.userDAO.doDelete(this.utenteEsistente);
   }

   @Test
   public void TestRitornaCarrelloUtenteNonEsistente() throws SQLException {
      Collection<ShopBean> expected = new LinkedList<ShopBean>();
      ShopBean shop = new ShopBean();
      shop.setCodiceProdotto("CO001");
      shop.setIdCategoria("Console");
      shop.setQuantitaProdotto(10);
      shop.setTitolo("PS5");
      shop.setDescrizione("Console SONY Playstation 5");
      shop.setPrezzo(500);
      shop.setCopertina("uomo_maglia1.png");
      expected.add(shop);
      shop = new ShopBean();
      shop.setCodiceProdotto("VD002");
      shop.setIdCategoria("Videogiochi");
      shop.setQuantitaProdotto(20);
      shop.setTitolo("Grand Theft Auto 5");
      shop.setDescrizione("L'ultimo capitolo della saga Grand Theft Auto");
      shop.setPrezzo(50);
      shop.setCopertina("uomo_maglia2.png");
      expected.add(shop);
      shop = new ShopBean();
      shop.setCodiceProdotto("AC003");
      shop.setIdCategoria("Accessori");
      shop.setQuantitaProdotto(30);
      shop.setTitolo("Cuffie Sony");
      shop.setDescrizione("L'ultimo modello di cuffie da gaming Sony");
      shop.setPrezzo(100);
      shop.setCopertina("bimba.maglia3.png");
      expected.add(shop);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("CO001");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("VD002");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("AC003");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      Object actual = new LinkedList<ShopBean>();
      try {
         actual = this.carrelloDAO.TrovaCarrello("g@gmail.com");
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

      Assert.assertNotEquals(expected, actual);
      this.userDAO.doDelete(this.utenteEsistente);
   }

   @Test
   public void TestRitornaCarrelloUtenteNull() throws SQLException {
      Collection<ShopBean> expected = new LinkedList<ShopBean>();
      ShopBean shop = new ShopBean();
      shop.setCodiceProdotto("CO001");
      shop.setIdCategoria("Console");
      shop.setQuantitaProdotto(10);
      shop.setTitolo("PS5");
      shop.setDescrizione("Console SONY Playstation 5");
      shop.setPrezzo(500);
      shop.setCopertina("uomo_maglia1.png");
      expected.add(shop);
      shop = new ShopBean();
      shop.setCodiceProdotto("VD002");
      shop.setIdCategoria("Videogiochi");
      shop.setQuantitaProdotto(20);
      shop.setTitolo("Grand Theft Auto 5");
      shop.setDescrizione("L'ultimo capitolo della saga Grand Theft Auto");
      shop.setPrezzo(50);
      shop.setCopertina("uomo_maglia2.png");
      expected.add(shop);
      shop = new ShopBean();
      shop.setCodiceProdotto("AC003");
      shop.setIdCategoria("Accessori");
      shop.setQuantitaProdotto(30);
      shop.setTitolo("Cuffie Sony");
      shop.setDescrizione("L'ultimo modello di cuffie da gaming Sony");
      shop.setPrezzo(100);
      shop.setCopertina("bimba.maglia3.png");
      expected.add(shop);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("CO001");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("VD002");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      this.carrello = new SessionCarrelloBean();
      this.carrello.setCodiceProdotto("AC003");
      this.carrello.setIdemail("luigiSod@gmail.com");
      this.carrelloDAO.doSave(this.carrello);
      Object actual = new LinkedList<ShopBean>();

      try {
         actual = this.carrelloDAO.TrovaCarrello((String)null);
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

      Assert.assertNotEquals(expected, actual);
      this.userDAO.doDelete(this.utenteEsistente);
   }

   @After
   public void tearDown() throws SQLException {
      System.out.println("Sono entrato nella tearDown");
      this.carrelloDAO.doDelete(this.carrello);
      this.prodottoDAO.doDelete(this.prodotto1);
      this.prodottoDAO.doDelete(this.prodotto2);
      this.prodottoDAO.doDelete(this.prodotto3);
      this.userDAO.doDelete(this.utenteEsistente);
   }
}
    