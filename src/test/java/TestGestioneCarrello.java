import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import gestioneAccount.UtenteBean;
import gestioneProdotti.ShopBean;
import gestioneCarrello.*;
import testingM.*;;

class TestGestioneCarrello {

	private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private Connection dbConnection;
    private GestioneCarrelloModelDS2 carrelloDAO;
    private ShopModelDS2 prodottoDAO;
    private RegistrazioneModelDS2 userDAO;
    private UtenteBean utenteEsistente, utenteNonEsistente;
    private SessionCarrelloBean carrello;
    private ShopBean prodotto1;
    private ShopBean prodotto2;
    private ShopBean prodotto3;
    private static IDatabaseTester tester;
    private DataSource ds;
    
 
    

    
    
    
    @Before
    public void setUp() throws Exception {
    	dbConnection=null;
    	 
    	    
    	  try {
    		    Class.forName("com.mysql.jdbc.Driver");
    		    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
    		    System.out.println("Connessione riuscita!");
    		} catch (Exception e) {
    		    System.out.println("Connessione fallita!");
    		    e.printStackTrace();
    		} finally {
    		    if (dbConnection != null) {
    		        try {
    		            dbConnection.close();
    		            System.out.println("Connessione chiusa correttamente!");
    		        } catch (SQLException sqe) {
    		            System.out.println("Impossibile chiudere la connessione in modo pulito.");
    		            sqe.printStackTrace();
    		        }
    		    }
    		}
         
       
      
    }
    
    
    
    @Test
    public void TestAggiungiAlCarrello() throws Exception {
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	      this.userDAO = new RegistrazioneModelDS2(ds);
	      this.prodottoDAO = new ShopModelDS2(ds);
	      this.carrelloDAO = new GestioneCarrelloModelDS2(ds);
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
	      this.prodotto1.setCopertina("ps5.png");
	      this.prodottoDAO.doSaveCategoria("Console", "1");
	      this.prodottoDAO.doSave(this.prodotto1);
	      
    	
    	
    	
    	
    	
    	
       String email = "luigiSod@gmail.com";
       String codiceProdotto = "C0001";
       this.carrello = new SessionCarrelloBean();
       this.carrello.setCodiceProdotto(codiceProdotto);
       this.carrello.setIdemail(email);
       assertEquals(true, this.carrelloDAO.doSave(this.carrello));
       
       prodottoDAO.doDeleteCategoria("Console");
    }
    
    
    @Test
    public void TestAggiungiAlCarrelloUtenteNonEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	      this.userDAO = new RegistrazioneModelDS2(ds);
	      this.prodottoDAO = new ShopModelDS2(ds);
	      this.carrelloDAO = new GestioneCarrelloModelDS2(ds);
	      this.utenteEsistente = new UtenteBean();
	      this.utenteEsistente.setNome("luigi");
	      this.utenteEsistente.setCognome("Sod");
	      this.utenteEsistente.setEmail("luigiSodNonEsistente@gmail.com");
	      this.utenteEsistente.setIndirizzo("Via San Francesco");
	      this.utenteEsistente.setPassword("ABCD1234");
	     
	      
	      this.prodotto1 = new ShopBean();
	      this.prodotto1.setCodiceProdotto("CO002");
	      this.prodotto1.setIdCategoria("Console");
	      this.prodotto1.setQuantitaProdotto(10);
	      this.prodotto1.setTitolo("PS4");
	      this.prodotto1.setDescrizione("Console SONY Playstation 4");
	      this.prodotto1.setPrezzo(200);
	      this.prodotto1.setCopertina("ps4.png");
	      this.prodottoDAO.doSaveCategoria("Console", "1");
	      this.prodottoDAO.doSave(this.prodotto1);
	      
    	
    	
    	
    	
    	
    	
       String email = "luigiSodNonEsistente@gmail.com";
       String codiceProdotto = "C0002";
       this.carrello = new SessionCarrelloBean();
       this.carrello.setCodiceProdotto(codiceProdotto);
       this.carrello.setIdemail(email);
       assertEquals(false, this.carrelloDAO.doSave(this.carrello));
    }

    
    
    @Test
    public void TestEliminaTuttoIlCarrello() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    this.userDAO = new RegistrazioneModelDS2(ds);
	      this.prodottoDAO = new ShopModelDS2(ds);
	      this.carrelloDAO = new GestioneCarrelloModelDS2(ds);
	      this.utenteEsistente = new UtenteBean();
	      this.utenteEsistente.setNome("luigi2");
	      this.utenteEsistente.setCognome("Sod2");
	      this.utenteEsistente.setEmail("luigiSod2@gmail.com");
	      this.utenteEsistente.setIndirizzo("Via San Francesco");
	      this.utenteEsistente.setPassword("ABCD1234");
	      userDAO.doSave(utenteEsistente);
	      
	      this.prodotto1 = new ShopBean();
	      this.prodotto1.setCodiceProdotto("AC003");
	      this.prodotto1.setIdCategoria("Accessori");
	      this.prodotto1.setQuantitaProdotto(10);
	      this.prodotto1.setTitolo("Cuffie");
	      this.prodotto1.setDescrizione("Cuffie gaming");
	      this.prodotto1.setPrezzo(200);
	      this.prodotto1.setCopertina("cuffie.png");
	      this.prodottoDAO.doSaveCategoria("Accessori", "3");
	      this.prodottoDAO.doSave(this.prodotto1);
	      
	    
	    
	    
       String email = "luigiSod2@gmail.com";
       String codiceProdotto = "AC003";
       this.carrello = new SessionCarrelloBean();
       this.carrello.setIdemail(email);
       this.carrello.setCodiceProdotto(codiceProdotto);
       this.carrelloDAO.doSave(this.carrello);
       
       
       assertEquals(true, this.carrelloDAO.deleteAll("luigiSod2@gmail.com"));
       
       prodottoDAO.doDeleteCategoria("Accessori");
       userDAO.doDelete(utenteEsistente);
    }
    
    
    @Test
    public void TestRimuoviDalCarrello() throws Exception {
    	
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	      this.userDAO = new RegistrazioneModelDS2(ds);
	      this.prodottoDAO = new ShopModelDS2(ds);
	      this.carrelloDAO = new GestioneCarrelloModelDS2(ds);
    	
    	
    	
    	
    	  
	      this.utenteEsistente = new UtenteBean();
	      this.utenteEsistente.setNome("luigi2");
	      this.utenteEsistente.setCognome("Sod2");
	      this.utenteEsistente.setEmail("luigiSod2@gmail.com");
	      this.utenteEsistente.setIndirizzo("Via San Francesco");
	      this.utenteEsistente.setPassword("ABCD1234");
	      userDAO.doSave(utenteEsistente);
	      
	      this.prodotto1 = new ShopBean();
	      this.prodotto1.setCodiceProdotto("AC003");
	      this.prodotto1.setIdCategoria("Accessori");
	      this.prodotto1.setQuantitaProdotto(10);
	      this.prodotto1.setTitolo("Cuffie");
	      this.prodotto1.setDescrizione("Cuffie gaming");
	      this.prodotto1.setPrezzo(200);
	      this.prodotto1.setCopertina("cuffie.png");
	      this.prodottoDAO.doSaveCategoria("Accessori", "3");
	      this.prodottoDAO.doSave(this.prodotto1);
	      
	    
	    
	    
       String email = "luigiSod2@gmail.com";
       String codiceProdotto = "AC003";
       this.carrello = new SessionCarrelloBean();
       this.carrello.setIdemail(email);
       this.carrello.setCodiceProdotto(codiceProdotto);
       this.carrelloDAO.doSave(this.carrello);
       
       
       assertEquals(true, this.carrelloDAO.doDelete(carrello));
    }
    
    
    @Test
    public void TestRitornaCarrello() throws Exception {
    	
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	      this.userDAO = new RegistrazioneModelDS2(ds);
	      this.prodottoDAO = new ShopModelDS2(ds);
	      this.carrelloDAO = new GestioneCarrelloModelDS2(ds);
    	
    	
	      this.utenteEsistente = new UtenteBean();
	      this.utenteEsistente.setNome("luigi3");
	      this.utenteEsistente.setCognome("Sod3");
	      this.utenteEsistente.setEmail("luigiSod3@gmail.com");
	      this.utenteEsistente.setIndirizzo("Via San Francesco");
	      this.utenteEsistente.setPassword("ABCD1234");
	      userDAO.doSave(utenteEsistente);
    	
       Collection<ShopBean> expected = new LinkedList<ShopBean>();
       ShopBean prodotto1 = new ShopBean();
       prodotto1.setCodiceProdotto("CO005");
       prodotto1.setIdCategoria("Console");
       prodotto1.setQuantitaProdotto(10);
       prodotto1.setTitolo("Xbox One");
       prodotto1.setDescrizione("Console Xbox one");
       prodotto1.setPrezzo(500);
       prodotto1.setCopertina("xbox1.png");
       expected.add(prodotto1);
       prodotto2 = new ShopBean();
       prodotto2.setCodiceProdotto("VD002");
       prodotto2.setIdCategoria("Videogiochi");
       prodotto2.setQuantitaProdotto(20);
       prodotto2.setTitolo("Grand Theft Auto 5");
       prodotto2.setDescrizione("L'ultimo capitolo della saga Grand Theft Auto");
       prodotto2.setPrezzo(50);
       prodotto2.setCopertina("gta5.png");
       expected.add(prodotto2);
       
       
       
       
       
       this.carrello = new SessionCarrelloBean();
       this.carrello.setCodiceProdotto("CO005");
       this.carrello.setIdemail("luigiSod3@gmail.com");
       this.carrelloDAO.doSave(this.carrello);
       
       this.carrello = new SessionCarrelloBean();
       this.carrello.setCodiceProdotto("VD002");
       this.carrello.setIdemail("luigiSod3@gmail.com");
       this.carrelloDAO.doSave(this.carrello);
       
       Collection<ShopBean> actual = new LinkedList<ShopBean>();

       actual.add(prodotto1);
       actual.add(prodotto2);
       
       assertEquals(expected, actual);
       this.userDAO.doDelete(this.utenteEsistente);
    }
    
    
    
    
    @Test
    public void TestRitornaCarrelloUtenteNonEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	      this.userDAO = new RegistrazioneModelDS2(ds);
	      this.prodottoDAO = new ShopModelDS2(ds);
	      this.carrelloDAO = new GestioneCarrelloModelDS2(ds);
    	
    	
	      this.utenteNonEsistente = new UtenteBean();
	      this.utenteNonEsistente.setNome("luigi33");
	      this.utenteNonEsistente.setCognome("Sod33");
	      this.utenteNonEsistente.setEmail("luigiSod33@gmail.com");
	      this.utenteNonEsistente.setIndirizzo("Via San Francesco");
	      this.utenteNonEsistente.setPassword("ABCD1234");
	      
	      
	      Collection<ShopBean> expected = new LinkedList<ShopBean>();
	       ShopBean prodotto1 = new ShopBean();
	       prodotto1.setCodiceProdotto("CO005");
	       prodotto1.setIdCategoria("Console");
	       prodotto1.setQuantitaProdotto(10);
	       prodotto1.setTitolo("Xbox One");
	       prodotto1.setDescrizione("Console Xbox one");
	       prodotto1.setPrezzo(500);
	       prodotto1.setCopertina("xbox1.png");
	       expected.add(prodotto1);
	       prodotto2 = new ShopBean();
	       prodotto2.setCodiceProdotto("VD002");
	       prodotto2.setIdCategoria("Videogiochi");
	       prodotto2.setQuantitaProdotto(20);
	       prodotto2.setTitolo("Grand Theft Auto 5");
	       prodotto2.setDescrizione("L'ultimo capitolo della saga Grand Theft Auto");
	       prodotto2.setPrezzo(50);
	       prodotto2.setCopertina("gta5.png");
	       expected.add(prodotto2);
	       
	       
	       
	       
	       
	       this.carrello = new SessionCarrelloBean();
	       this.carrello.setCodiceProdotto("CO008");
	       this.carrello.setIdemail("luigiSod33@gmail.com");
	       this.carrelloDAO.doSave(this.carrello);
	       
	       this.carrello = new SessionCarrelloBean();
	       this.carrello.setCodiceProdotto("VD005");
	       this.carrello.setIdemail("luigiSod33@gmail.com");
	       this.carrelloDAO.doSave(this.carrello);
	       Object actual = new LinkedList<ShopBean>();
	       try {
	           actual = this.carrelloDAO.TrovaCarrello("luigiSod33@gmail.com");
	        } catch (SQLException var5) {
	           var5.printStackTrace();
	        }

	        assertNotEquals(expected, actual);
	       
	      
    }

    @Test
    public void TestRitornaCarrelloUtenteNull() throws Exception {
    	 dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
  	   
 	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
 	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
 	    	    "PROVA",
 	    	    ""
 	    	    );
 	    ds = Mockito.mock(DataSource.class);
 	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
 	    
 	    
 	      this.userDAO = new RegistrazioneModelDS2(ds);
 	      this.prodottoDAO = new ShopModelDS2(ds);
 	      this.carrelloDAO = new GestioneCarrelloModelDS2(ds);
     	
    	
    	
       Collection<ShopBean> expected = new LinkedList<ShopBean>();
       ShopBean shop = new ShopBean();
       shop.setCodiceProdotto("CO001");
       shop.setIdCategoria("Console");
       shop.setQuantitaProdotto(10);
       shop.setTitolo("PS5");
       shop.setDescrizione("Console SONY Playstation 5");
       shop.setPrezzo(500);
       shop.setCopertina("ps5.png");
       expected.add(shop);
       shop = new ShopBean();
       shop.setCodiceProdotto("VD002");
       shop.setIdCategoria("Videogiochi");
       shop.setQuantitaProdotto(20);
       shop.setTitolo("Grand Theft Auto 5");
       shop.setDescrizione("L'ultimo capitolo della saga Grand Theft Auto");
       shop.setPrezzo(50);
       shop.setCopertina("gta5.png");
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

       assertNotEquals(expected, actual);
       
    }
    
    @After
    public void tearDown() throws SQLException {
        // Pulisci il database dopo l'esecuzione dei test
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            connection.createStatement().execute("DROP ALL OBJECTS DELETE FILES");
        }
	}

}
