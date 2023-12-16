package test.java;

import gestioneAccount.UtenteBean;
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
import test.java.RegistrazioneModelDS;
import test.java.ShopModelDS;

public class TestGestioneProdottiDAO {
   private Connection db;
   private static IDatabaseTester tester;
   private DataSource ds;
   private ShopModelDS productDAO;
   private ShopBean prodotto1;
   private ShopBean prodotto2;
   private ShopBean prodotto3;
   private ShopBean prodottoNonInserito;
   private RegistrazioneModelDS userDAO;
   private UtenteBean utenteEsistente;

   @Before
   public void setUp() throws SQLException, Exception {
      tester = new JdbcDatabaseTester(Driver.class.getName(), "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "PROVA", "");
      tester.setSetUpOperation(DatabaseOperation.REFRESH);
      tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
      this.ds = (DataSource)Mockito.mock(DataSource.class);
      Mockito.when(this.ds.getConnection()).thenReturn(tester.getConnection().getConnection());
      this.userDAO = new RegistrazioneModelDS(this.ds);
      this.productDAO = new ShopModelDS(this.ds);
      this.utenteEsistente = new UtenteBean();
      this.utenteEsistente.setNome("luigi");
      this.utenteEsistente.setCognome("PP");
      this.utenteEsistente.setEmail("luigiPP@gmail.com");
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
      this.prodotto2.setDescrizione("L'ultimo capitolo della saga di Grand Theft Auto");
      this.prodotto2.setPrezzo(50);
      this.prodotto2.setCopertina("donna_maglia2.png");
      this.prodotto3 = new ShopBean();
      this.prodotto3.setCodiceProdotto("AC003");
      this.prodotto3.setIdCategoria("Accessori");
      this.prodotto3.setQuantitaProdotto(30);
      this.prodotto3.setTitolo("Cuffie Sony");
      this.prodotto3.setDescrizione("L'ultimo modello di cuffie da gaming Sony");
      this.prodotto3.setPrezzo(100);
      this.prodotto3.setCopertina("bimba.maglia3.png");
      this.prodottoNonInserito = new ShopBean();
      this.prodottoNonInserito.setCodiceProdotto("CO004");
      this.prodottoNonInserito.setIdCategoria("Console");
      this.prodottoNonInserito.setQuantitaProdotto(10);
      this.prodottoNonInserito.setTitolo("Nintendo Switch");
      this.prodottoNonInserito.setDescrizione("Console Nintendo Nintendo Switch");
      this.prodottoNonInserito.setPrezzo(200);
      this.prodottoNonInserito.setCopertina("uomo_maglia1.png");
      this.productDAO.doSave(this.prodotto1);
      this.productDAO.doSave(this.prodotto2);
      this.productDAO.doSave(this.prodotto3);
   }

   @After
   public void tearDown() throws Exception {
      this.productDAO.doDelete(this.prodottoNonInserito);
      System.out.println("Sono entrato nella tearDown");
      this.productDAO.doDelete(this.prodotto1);
      this.productDAO.doDelete(this.prodotto2);
      this.productDAO.doDelete(this.prodotto3);
      this.userDAO.doDelete(this.utenteEsistente);
   }

   @Test
   public void TestdoRetrieveByKeyPresente() throws SQLException {
      ShopBean expected = new ShopBean();
      expected.setCodiceProdotto("CO001");
      expected.setIdCategoria("Console");
      expected.setQuantitaProdotto(10);
      expected.setTitolo("PS5");
      expected.setDescrizione("Console SONY Playstation 5");
      expected.setPrezzo(500);
      expected.setCopertina("image/Uomo/uomo_maglia1.png");
      ShopBean actual = null;

      try {
         actual = this.productDAO.doRetrieveByKey("CO0001");
      } catch (SQLException var4) {
         var4.printStackTrace();
      }

      Assertions.assertEquals(expected, actual);
   }

   @Test
   public void TestdoRetrieveByKeyNonPresente() throws SQLException {
      ShopBean expected = new ShopBean();
      ShopBean actual = null;

      try {
         actual = this.productDAO.doRetrieveByKey("CO000");
      } catch (SQLException var4) {
         var4.printStackTrace();
      }

      Assertions.assertEquals(expected, actual);
   }

   @Test
   public void TestdoRetrieveByKeyNull() throws SQLException {
      Assert.assertNotEquals(this.prodotto1, this.productDAO.doRetrieveByKey((String)null));
      Assert.assertNotEquals(this.prodotto2, this.productDAO.doRetrieveByKey((String)null));
      Assert.assertNotEquals(this.prodotto3, this.productDAO.doRetrieveByKey((String)null));
   }
   @Test
   public void TestFindAllProduct() throws SQLException {
       Collection<ShopBean> expected = new LinkedList<ShopBean>();
       ShopBean shop = new ShopBean();
       shop.setCodiceProdotto("CO0001");
       shop.setIdCategoria("Console");
       shop.setQuantitaProdotto(10);
       shop.setTitolo("PS5");
       shop.setDescrizione("Console SONY Playstation 5");
       shop.setPrezzo(9);
       shop.setCopertina("image/Console/console_maglia1.png");
       expected.add(shop);
       shop = new ShopBean();
       shop.setCodiceProdotto("MG002");
       shop.setIdCategoria("Videogiochi");
       shop.setQuantitaProdotto(20);
       shop.setTitolo("Grand Theft Auto 5");
       shop.setDescrizione("L'ultimo capitolo della saga di Grand Theft Auto");
       shop.setPrezzo(15);
       shop.setCopertina("image/Videogiochi/videogiochi_maglia2.png");
       expected.add(shop);
       shop = new ShopBean();
       shop.setCodiceProdotto("AC003");
       shop.setIdCategoria("Accessori");
       shop.setQuantitaProdotto(30);
       shop.setTitolo("Cuffie Sony");
       shop.setDescrizione("L'ultimo modello di cuffie da gaming Sony");
       shop.setPrezzo(34);
       shop.setCopertina("image/Accessori/accessori_maglia3.png");
       expected.add(shop);
       Object actual = new LinkedList<ShopBean>();
   
       try {
           actual = this.productDAO.doRetrieveAll("");
       } catch (SQLException var5) {
           var5.printStackTrace();
       }
   
       Assertions.assertEquals(expected, actual);
   }
   
   @Test
   public void TestFindAllProductUomo() throws SQLException {
       Collection<ShopBean> expected = new LinkedList<ShopBean>();
       ShopBean shop = new ShopBean();
       shop.setCodiceProdotto("CO0001");
       shop.setIdCategoria("Console");
       shop.setQuantitaProdotto(10);
       shop.setTitolo("PS5");
       shop.setDescrizione("Console SONY Playstation 5");
       shop.setPrezzo(9);
       shop.setCopertina("image/Console/console_maglia1.png");
       expected.add(shop);
       Object actual = new LinkedList<ShopBean>();
   
       try {
           actual = this.productDAO.doRetrieveAllConsole("");
       } catch (SQLException var5) {
           var5.printStackTrace();
       }
   
       Assertions.assertEquals(expected, actual);
   }
   
   @Test
   public void TestFindAllProductBambini() throws SQLException {
       Collection<ShopBean> expected = new LinkedList<ShopBean>();
       ShopBean shop = new ShopBean();
       shop.setCodiceProdotto("AC003");
       shop.setIdCategoria("Accessori");
       shop.setQuantitaProdotto(30);
       shop.setTitolo("Cuffie Sony");
       shop.setDescrizione("L'ultimo modello di cuffie da gaming Sony");
       shop.setPrezzo(34);
       shop.setCopertina("image/Accessori/accessori_maglia3.png");
       expected.add(shop);
       Object actual = new LinkedList<ShopBean>();
   
       try {
           actual = this.productDAO.doRetrieveAllAccessori("");
       } catch (SQLException var5) {
           var5.printStackTrace();
       }
   
       Assertions.assertEquals(expected, actual);
   }
   
   @Test
   public void TestFindAllProductDonna() throws SQLException {
       Collection<ShopBean> expected = new LinkedList<ShopBean>();
       ShopBean shop = new ShopBean();
       shop.setCodiceProdotto("VD002");
       shop.setIdCategoria("Videogiochi");
       shop.setQuantitaProdotto(20);
       shop.setTitolo("Grand Theft Auto 5");
       shop.setDescrizione("L'ultimo capitolo della saga di Grand Theft Auto");
       shop.setPrezzo(15);
       shop.setCopertina("image/Videogiochi/videogiochi_maglia2.png");
       expected.add(shop);
       Object actual = new LinkedList<ShopBean>();
   
       try {
           actual = this.productDAO.doRetrieveAllVideogiochi("");
       } catch (SQLException var5) {
           var5.printStackTrace();
       }
   
       Assertions.assertEquals(expected, actual);
   }
   
   @Test
   public void TestFindAllProductNull() throws SQLException {
       Collection<ShopBean> expected = new LinkedList();
       ShopBean shop = new ShopBean();
       shop.setCodiceProdotto("VD002");
       shop.setIdCategoria("Videogiochi");
       shop.setQuantitaProdotto(20);
       shop.setTitolo("Grand Theft Auto 5");
       shop.setDescrizione("L'ultimo capitolo della saga di Grand Theft Auto");
       shop.setPrezzo(15);
       shop.setCopertina("image/Videogiochi/videogiochi_maglia2.png");
       expected.add(shop);
       Object actual = new LinkedList<ShopBean>();
   
       try {
           actual = this.productDAO.doRetrieveAll((String)null);
       } catch (SQLException var5) {
           var5.printStackTrace();
       }
   
       Assert.assertNotEquals(expected, actual);
   }
   



   @Test
   public void TestDoUpdate() throws SQLException {
      ShopBean expected = new ShopBean();
      new ShopBean();
      expected.setCodiceProdotto("CO001");
      expected.setIdCategoria("Console");
      expected.setQuantitaProdotto(50);
      expected.setTitolo("PS5");
      expected.setDescrizione("Console SONY Playstation 5");
      expected.setPrezzo(9);
      expected.setCopertina("image/Uomo/uomo_maglia1.png");
      boolean prova = false;

      try {
         prova = this.productDAO.doUpdate(expected);
         ShopBean var2 = this.productDAO.doRetrieveByKey("CO0001");
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

      Assertions.assertEquals(true, prova);
   }

   @Test
   public void TestDoUpdateNonPresente() throws SQLException {
      ShopBean expected = new ShopBean();
      ShopBean actual = new ShopBean();
      expected.setCodiceProdotto("CO004");
      expected.setIdCategoria("Console");
      expected.setQuantitaProdotto(50);
      expected.setTitolo("PS5");
      expected.setDescrizione("Console SONY Playstation 5");
      expected.setPrezzo(9);
      expected.setCopertina("image/Uomo/uomo_maglia1.png");
      boolean prova = false;

      try {
         prova = this.productDAO.doUpdate(expected);
         actual = this.productDAO.doRetrieveByKey("CO004");
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

      Assertions.assertEquals(true, prova);
      Assert.assertNotEquals(expected, actual);
   }

   @Test
   public void TestDoUpdateNull() throws SQLException {
      ShopBean expected = new ShopBean();
      ShopBean actual = new ShopBean();
      expected.setCodiceProdotto("CO001");
      expected.setIdCategoria("Uomo");
      expected.setQuantitaProdotto(50);
      expected.setTitolo("PS5");
      expected.setDescrizione("Console SONY Playstation 5");
      expected.setPrezzo(9);
      expected.setCopertina("image/Uomo/uomo_maglia1.png");
      boolean prova = false;

      try {
         prova = this.productDAO.doUpdate(expected);
         actual = this.productDAO.doRetrieveByKey((String)null);
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

      Assertions.assertEquals(true, prova);
      Assert.assertNotEquals(expected, actual);
   }

   @Test
   public void TestDoSave() throws SQLException {
      try {
         this.productDAO.doSave(this.prodottoNonInserito);
      } catch (SQLException var2) {
         var2.printStackTrace();
      }

      Assertions.assertEquals(this.prodottoNonInserito.getCodiceProdotto(), this.productDAO.doRetrieveByKey("CO004").getCodiceProdotto());
   }

   @Test
   public void TestDoSaveNonEsistente() throws SQLException {
      Assertions.assertThrows(SQLException.class, () -> {
         this.productDAO.doSave(this.prodotto1);
      });
   }

   @Test
   public void TestDoSaveNull() throws SQLException {
      Assertions.assertThrows(NullPointerException.class, () -> {
         this.productDAO.doSave((ShopBean)null);
      });
   }

   @Test
   public void TestDoDeleteEsistente() throws SQLException {
      boolean actual = false;

      try {
         actual = this.productDAO.doDelete(this.prodotto1);
      } catch (SQLException var3) {
         var3.printStackTrace();
      }

      Assertions.assertEquals(true, actual);
   }

   @Test
   public void TestDoDeleteNull() throws SQLException {
      Assertions.assertThrows(NullPointerException.class, () -> {
         this.productDAO.doDelete((ShopBean)null);
      });
   }

   @Test
   public void TestDoDeleteEsistenteNonEsistente() throws SQLException {
      Assert.assertNotEquals(this.prodottoNonInserito, this.productDAO.doRetrieveByKey("CO004"));
      this.productDAO.doDelete(this.prodottoNonInserito);
      Assert.assertNotEquals(this.prodottoNonInserito, this.productDAO.doRetrieveByKey("CO004"));
   }
}