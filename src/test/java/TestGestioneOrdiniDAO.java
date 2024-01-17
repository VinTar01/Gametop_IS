import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import gestioneAccount.PersonaleBean;
import gestioneProdotti.*;
import it.unisa.utils.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.h2.Driver;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.mockito.Mockito;


import junit.framework.Assert;
import testingM.GestorePersonaleModelDS2;
import testingM.RegistrazioneModelDS2;
import testingM.GestioneOrdiniModelDS2;
import testingM.OrdineModelDS2;

import gestioneAcquisti.OrdineBean;

import gestioneAccount.UtenteBean;

class TestGestioneOrdiniDAO {
	
	private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private DataSource dataSource;
    
    private OrdineBean ordineEsistente1, ordineEsistente2, ordineNonEsistente;
    private GestioneOrdiniModelDS2 OrdiniDAO;
    private OrdineModelDS2 SaveOrdineDAO;
    private RegistrazioneModelDS2 RegDAO;
    private UtenteBean utenteEsistente1, utenteEsistente2;
    
    private Connection dbConnection;
    
    private DataSource ds;
    private static IDatabaseTester tester;
    
 
    

    
    
    
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
    public void TestFindOrdineByNumeroOrdine() throws Exception {
    	
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       ordineEsistente1 = new OrdineBean();
       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
       SaveOrdineDAO = new OrdineModelDS2(ds);
       RegDAO = new RegistrazioneModelDS2(ds);
       utenteEsistente1 = new UtenteBean();
       
       this.utenteEsistente1.setEmail("ordine1@gmail.com");
       this.utenteEsistente1.setNome("Ordine");
       this.utenteEsistente1.setCognome("Ordine");
       this.utenteEsistente1.setIndirizzo("Via delle vie");
       this.utenteEsistente1.setPassword("password123");
       

       RegDAO.doSave(utenteEsistente1);
       
       this.ordineEsistente1.setNumeroOrdine("1");
       this.ordineEsistente1.setEmail("ordine1@gmail.com");
       this.ordineEsistente1.setNome("Ordine");
       this.ordineEsistente1.setCognome("Ordine");
       this.ordineEsistente1.setIndirizzo("Via delle vie");
       this.ordineEsistente1.setCap("83100");
       this.ordineEsistente1.setComune("Avellino");
       this.ordineEsistente1.setProvincia("Avellino");
       this.ordineEsistente1.setProdotti("CO001");
       this.ordineEsistente1.setPrezzo("500");
       this.ordineEsistente1.setControllato("false");
       

     
       SaveOrdineDAO.SalvaOrdine(ordineEsistente1); //salvo nel db l'utente creato
      
       
	   assertEquals(ordineEsistente1.getNumeroOrdine(), "1");
	   
	   RegDAO.doDelete(utenteEsistente1);
	   OrdiniDAO.doDelete(ordineEsistente1);
	   
  
    }
    
    
    @Test
    public void TestFindOrdineByNumeroNonOrdine() throws Exception {
    	
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       ordineNonEsistente = new OrdineBean();
       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
       SaveOrdineDAO = new OrdineModelDS2(ds);
       RegDAO = new RegistrazioneModelDS2(ds);
 
       
       this.ordineNonEsistente.setNumeroOrdine("2");
       this.ordineNonEsistente.setEmail("ordine2@gmail.com");
       this.ordineNonEsistente.setNome("Ordine");
       this.ordineNonEsistente.setCognome("Ordine");
       this.ordineNonEsistente.setIndirizzo("Via delle vie");
       this.ordineNonEsistente.setCap("83100");
       this.ordineNonEsistente.setComune("Avellino");
       this.ordineNonEsistente.setProvincia("Avellino");
       this.ordineNonEsistente.setProdotti("CO001");
       this.ordineNonEsistente.setPrezzo("500");
       this.ordineNonEsistente.setControllato("false");
       

      
       
	   assertEquals((Object) null, OrdiniDAO.doRetrieveByKey(2).getNumeroOrdine());
	   
  
    }
    
    
    
    
    
    @Test
    public void TestFindOrdineByNumeroNonOrdineNull() throws Exception {
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
       assertThrows(NumberFormatException.class, () -> {
          this.OrdiniDAO.doRetrieveByKey(Integer.parseInt((String)null));
       });
    }
    
    
    
    @Test
    public void TestFindTuttiGliOrdini() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
	    OrdiniDAO = new GestioneOrdiniModelDS2(ds);
	    OrdineBean ordineAspettato = new OrdineBean();
	    ordineAspettato.setNumeroOrdine("1");
	    ordineAspettato.setEmail("ordine1@gmail.com");
	    ordineAspettato.setNome("Ordine");
	    ordineAspettato.setCognome("Ordine");
	    ordineAspettato.setIndirizzo("Via delle vie");
	    ordineAspettato.setCap("83100");
	    ordineAspettato.setComune("Avellino");
	    ordineAspettato.setProvincia("Avellino");
	    ordineAspettato.setProdotti("CO001");
	    ordineAspettato.setPrezzo("500");
	    ordineAspettato.setControllato("false");
	    
        Collection<OrdineBean> actual = this.OrdiniDAO.ritornaTuttiOrdiniDaControllare();

      
      
        for (OrdineBean ordineEffettivo : actual) {
            assertEquals(ordineAspettato.getNumeroOrdine(), ordineEffettivo.getNumeroOrdine());
        }
    }
    
    
    
     @Test
    public void TestUpdateOrdine() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
       ordineEsistente2 = new OrdineBean();
       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
       SaveOrdineDAO = new OrdineModelDS2(ds);
       RegDAO = new RegistrazioneModelDS2(ds);
       utenteEsistente2 = new UtenteBean();
       
       
       this.utenteEsistente2.setEmail("ordine2@gmail.com");
       this.utenteEsistente2.setNome("Ordine2");
       this.utenteEsistente2.setCognome("Ordine2");
       this.utenteEsistente2.setIndirizzo("Via delle vie");
       this.utenteEsistente2.setPassword("password123");
       
       RegDAO.doSave(utenteEsistente2);
       
       this.ordineEsistente2.setNumeroOrdine("3");
       this.ordineEsistente2.setEmail("ordine2@gmail.com");
       this.ordineEsistente2.setNome("Ordine2");
       this.ordineEsistente2.setCognome("Ordine2");
       this.ordineEsistente2.setIndirizzo("Via delle vie");
       this.ordineEsistente2.setCap("83100");
       this.ordineEsistente2.setComune("Avellino");
       this.ordineEsistente2.setProvincia("Avellino");
       this.ordineEsistente2.setProdotti("CO002");
       this.ordineEsistente2.setPrezzo("500");
       this.ordineEsistente2.setControllato("false");
       
       SaveOrdineDAO.SalvaOrdine(ordineEsistente2);
       
       ordineEsistente2.setProdotti("CO006");
       
       
       
       
       assertEquals(ordineEsistente2.getProdotti(), "CO006");
       
       RegDAO.doDelete(utenteEsistente2);
	   OrdiniDAO.doDelete(ordineEsistente2);
       
       
    }

    @Test
    public void TestUpdateOrdineNonEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
	       ordineNonEsistente = new OrdineBean();
	       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
	       SaveOrdineDAO = new OrdineModelDS2(ds);
	       RegDAO = new RegistrazioneModelDS2(ds);
	       
	       this.ordineNonEsistente.setNumeroOrdine("4");
	       this.ordineNonEsistente.setEmail("ordine3@gmail.com");
	       this.ordineNonEsistente.setNome("Ordine3");
	       this.ordineNonEsistente.setCognome("Ordine3");
	       this.ordineNonEsistente.setIndirizzo("Via delle vie");
	       this.ordineNonEsistente.setCap("83100");
	       this.ordineNonEsistente.setComune("Avellino");
	       this.ordineNonEsistente.setProvincia("Avellino");
	       this.ordineNonEsistente.setProdotti("CO002");
	       this.ordineNonEsistente.setPrezzo("500");
	       this.ordineNonEsistente.setControllato("false");
	       
    	
	       this.ordineNonEsistente.setNumeroOrdine("5");
	       assertNotEquals(ordineNonEsistente.getNumeroOrdine(), OrdiniDAO.doRetrieveByKey(5));
    }
    
       
      
    
    @Test
    public void TestUpdateOrdineNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	 OrdiniDAO=new GestioneOrdiniModelDS2(ds);
    
    	  assertThrows(NullPointerException.class, () -> {
              this.OrdiniDAO.doUpdate((OrdineBean)null);
           });
    }
        
    
    @Test
    public void TestDeleteOrdineEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       ordineEsistente1 = new OrdineBean();
       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
       SaveOrdineDAO = new OrdineModelDS2(ds);
       RegDAO = new RegistrazioneModelDS2(ds);
       utenteEsistente1 = new UtenteBean();
       
       this.utenteEsistente1.setEmail("ordine7@gmail.com");
       this.utenteEsistente1.setNome("Ordine7");
       this.utenteEsistente1.setCognome("Ordine7");
       this.utenteEsistente1.setIndirizzo("Via delle vie");
       this.utenteEsistente1.setPassword("password123");
       

       RegDAO.doSave(utenteEsistente1);
       
       this.ordineEsistente1.setNumeroOrdine("7");
       this.ordineEsistente1.setEmail("ordine7@gmail.com");
       this.ordineEsistente1.setNome("Ordine7");
       this.ordineEsistente1.setCognome("Ordine7");
       this.ordineEsistente1.setIndirizzo("Via delle vie");
       this.ordineEsistente1.setCap("83100");
       this.ordineEsistente1.setComune("Avellino");
       this.ordineEsistente1.setProvincia("Avellino");
       this.ordineEsistente1.setProdotti("CO001");
       this.ordineEsistente1.setPrezzo("500");
       this.ordineEsistente1.setControllato("false");
       

     
       SaveOrdineDAO.SalvaOrdine(ordineEsistente1); //salvo nel db l'utente creato
      
    	
       assertEquals(true, this.OrdiniDAO.doDelete(this.ordineEsistente1));
     
    }

    @Test
    public void TestDeleteOrdineNonEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
	       ordineNonEsistente = new OrdineBean();
	       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
	       SaveOrdineDAO = new OrdineModelDS2(ds);
	       RegDAO = new RegistrazioneModelDS2(ds);
	       
	       this.ordineNonEsistente.setNumeroOrdine("200");
	       this.ordineNonEsistente.setEmail("ordine200@gmail.com");
	       this.ordineNonEsistente.setNome("Ordine200");
	       this.ordineNonEsistente.setCognome("Ordine200");
	       this.ordineNonEsistente.setIndirizzo("Via delle vie");
	       this.ordineNonEsistente.setCap("83100");
	       this.ordineNonEsistente.setComune("Avellino");
	       this.ordineNonEsistente.setProvincia("Avellino");
	       this.ordineNonEsistente.setProdotti("CO002");
	       this.ordineNonEsistente.setPrezzo("500");
	       this.ordineNonEsistente.setControllato("false");
	       
    	
    
      assertNotEquals(this.ordineNonEsistente, this.OrdiniDAO.doRetrieveByKey(200));
    }
    
    @Test
    public void TestDeleteOrdineNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
       assertThrows(NullPointerException.class, () -> {
          this.OrdiniDAO.doDelete((OrdineBean)null);
       });
    }

   
    @Test
    public void TestConfermaOrdineEsistente() throws Exception {
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       ordineEsistente1 = new OrdineBean();
       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
       SaveOrdineDAO = new OrdineModelDS2(ds);
       RegDAO = new RegistrazioneModelDS2(ds);
       utenteEsistente1 = new UtenteBean();
       
       this.utenteEsistente1.setEmail("ordine22@gmail.com");
       this.utenteEsistente1.setNome("Ordine22");
       this.utenteEsistente1.setCognome("Ordine22");
       this.utenteEsistente1.setIndirizzo("Via delle vie");
       this.utenteEsistente1.setPassword("password123");
       

       RegDAO.doSave(utenteEsistente1);
       
       this.ordineEsistente1.setNumeroOrdine("22");
       this.ordineEsistente1.setEmail("ordine22@gmail.com");
       this.ordineEsistente1.setNome("Ordine22");
       this.ordineEsistente1.setCognome("Ordine22");
       this.ordineEsistente1.setIndirizzo("Via delle vie");
       this.ordineEsistente1.setCap("83100");
       this.ordineEsistente1.setComune("Avellino");
       this.ordineEsistente1.setProvincia("Avellino");
       this.ordineEsistente1.setProdotti("CO001");
       this.ordineEsistente1.setPrezzo("500");
       this.ordineEsistente1.setControllato("false");
       

     
       SaveOrdineDAO.SalvaOrdine(ordineEsistente1); //salvo nel db l'utente creato
      
    
       assertEquals(true, this.OrdiniDAO.confermaOrdine(this.ordineEsistente1));
    }

    @Test
    public void TestConfermaOrdineNonEsistente() throws Exception {
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
	       ordineNonEsistente = new OrdineBean();
	       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
	       SaveOrdineDAO = new OrdineModelDS2(ds);
	       RegDAO = new RegistrazioneModelDS2(ds);
	       
	       this.ordineNonEsistente.setNumeroOrdine("200");
	       this.ordineNonEsistente.setEmail("ordine200@gmail.com");
	       this.ordineNonEsistente.setNome("Ordine200");
	       this.ordineNonEsistente.setCognome("Ordine200");
	       this.ordineNonEsistente.setIndirizzo("Via delle vie");
	       this.ordineNonEsistente.setCap("83100");
	       this.ordineNonEsistente.setComune("Avellino");
	       this.ordineNonEsistente.setProvincia("Avellino");
	       this.ordineNonEsistente.setProdotti("CO002");
	       this.ordineNonEsistente.setPrezzo("500");
	       this.ordineNonEsistente.setControllato("false");
	       
    	
    	
           assertNotEquals(true, OrdiniDAO.doRetrieveByKey(200).getControllato());
    }


    @Test
    public void TestConfermaOrdineNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
	   
	       OrdiniDAO = new GestioneOrdiniModelDS2(ds);
	      
	       

       assertThrows(NullPointerException.class, () -> {
          this.OrdiniDAO.confermaOrdine((OrdineBean)null);
       });
    
    }
    
    
    
    @After
    public void tearDown() throws SQLException {
      // Pulisci il database dopo l'esecuzione dei test
      try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
     	  connection.createStatement().execute("DROP ALL OBJECTS DELETE FILES");
     	   }
       }
     		
  }
    
    