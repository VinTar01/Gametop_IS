import static org.junit.jupiter.api.Assertions.*;

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
import gestioneAccount.*;
import it.unisa.utils.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
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

class TestRegistrazioneDAO {
	
	private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private DataSource dataSource;
    
    private UtenteBean utenteEsistente, utenteNonEsistente;
    private PersonaleBean personaleEsistente, personaleNonEsistente;

    private RegistrazioneModelDS2 UtenteDAO;
    private GestorePersonaleModelDS2 PersonaleDAO;
    private LoginModelDS LoginDAO;
    
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
	public void testVerificaPresenzaUtenteNonEsistente() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    utenteNonEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
        
       
       this.utenteNonEsistente.setNome("luigi");
       this.utenteNonEsistente.setCognome("Sodano");
       this.utenteNonEsistente.setEmail("luigisodano85@gmail.com");
       this.utenteNonEsistente.setIndirizzo("via Roma 41, Avellino");
       this.utenteNonEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
      
		assertNotEquals(utenteNonEsistente, UtenteDAO.doRetrieveByKey(utenteNonEsistente.getEmail()));
    }
    
	
    @Test   
	public void testInserisciUtente() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    utenteNonEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
        
       
       this.utenteNonEsistente.setNome("luigi");
       this.utenteNonEsistente.setCognome("Sodano");
       this.utenteNonEsistente.setEmail("luigisodano88@gmail.com");
       this.utenteNonEsistente.setIndirizzo("via Roma 41, Avellino");
       this.utenteNonEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
       UtenteDAO.doSave(utenteNonEsistente);
      
		assertEquals(utenteNonEsistente, UtenteDAO.doRetrieveByKey(utenteNonEsistente.getEmail()));
    }
    
    
    @Test
    public void TestInserisciNuovoUtenteNull () throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    UtenteDAO = new RegistrazioneModelDS2(ds);
        
    	assertThrows(NullPointerException.class, () ->{ //deve lanciare una eccezione 
    		UtenteDAO.doSave(null);
        });
    	
    	

    }
	
	
    @Test
    public void TestInserisciNuovoUtenteEsistente () throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    LoginDAO = new LoginModelDS(ds);
	    utenteEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
	    
	       this.utenteEsistente.setNome("luigi");
	       this.utenteEsistente.setCognome("Sodano");
	       this.utenteEsistente.setEmail("luigisodano23@gmail.com");
	       this.utenteEsistente.setIndirizzo("via Roma 41, Avellino");
	       this.utenteEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
	       UtenteDAO.doSave(utenteEsistente);
    
	    assertThrows(SQLException.class, () ->{
    	UtenteDAO.doSave(utenteEsistente);
    });
    }
    
    
    @Test
    public void TestEliminaUtenteEsistente () throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    LoginDAO = new LoginModelDS(ds);
	    utenteEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
	    
	       this.utenteEsistente.setNome("luigi");
	       this.utenteEsistente.setCognome("Sodano");
	       this.utenteEsistente.setEmail("luigisodano2@gmail.com");
	       this.utenteEsistente.setIndirizzo("via Roma 41, Avellino");
	       this.utenteEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
	       UtenteDAO.doSave(utenteEsistente);
    assertEquals(true,UtenteDAO.doDelete(utenteEsistente));
    System.out.println("Utente Eliminato");
    assertNotEquals(utenteEsistente,UtenteDAO.doRetrieveByKey(utenteEsistente.getEmail())); // se non lo trovo, eliminazione ok
    }
    
    
    
    
    
    @Test
    public void TestModificaPasswordUtenteEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	  
	    utenteEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
	    
	       this.utenteEsistente.setNome("luigi");
	       this.utenteEsistente.setCognome("Sodano");
	       this.utenteEsistente.setEmail("luigisodano3@gmail.com");
	       this.utenteEsistente.setIndirizzo("via Roma 41, Avellino");
	       this.utenteEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
	       UtenteDAO.doSave(utenteEsistente);
    utenteEsistente = UtenteDAO.doRetrieveByKey(utenteEsistente.getEmail());
    utenteEsistente.setPassword("CIAO123456");
    assertEquals(true,UtenteDAO.doUpdatePassword(utenteEsistente));
    assertEquals(utenteEsistente,UtenteDAO.doRetrieveByKey(utenteEsistente.getEmail()));
    System.out.println("Utente Password Aggiornata "+utenteEsistente.getPassword());
    }
    
    
   
    
    
    @Test
    public void TestModificaPasswordUtenteNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    UtenteDAO = new RegistrazioneModelDS2(ds);
	    
    	assertThrows(NullPointerException.class, () ->{
    		UtenteDAO.doUpdatePassword(null);
        });
    }
    
    @Test
    public void TestModificaPasswordUtenteNonEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    LoginDAO = new LoginModelDS(ds);
	    utenteNonEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
        
       
       this.utenteNonEsistente.setNome("luigi");
       this.utenteNonEsistente.setCognome("Sodano");
       this.utenteNonEsistente.setEmail("luigisodano4@gmail.com");
       this.utenteNonEsistente.setIndirizzo("via Roma 41, Avellino");
       this.utenteNonEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
       
    
  
    utenteNonEsistente.setPassword("ABCDEF9339");
    assertEquals(true,UtenteDAO.doUpdatePassword(utenteNonEsistente));
    assertNotEquals(utenteNonEsistente,UtenteDAO.doRetrieveByKey(utenteNonEsistente.getEmail()));
    }
  
    
    @Test
    public void TestModificaDatiUtenteEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	 
	    utenteEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
	    
	       this.utenteEsistente.setNome("luigi");
	       this.utenteEsistente.setCognome("Sodano");
	       this.utenteEsistente.setEmail("luigisodano5@gmail.com");
	       this.utenteEsistente.setIndirizzo("via Roma 41, Avellino");
	       this.utenteEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
	       UtenteDAO.doSave(utenteEsistente);
	       
    utenteEsistente = UtenteDAO.doRetrieveByKey(utenteEsistente.getEmail());
    utenteEsistente.setNome("PPEsistente2");
    utenteEsistente.setCognome("esistente2");
    utenteEsistente.setEmail("esistente@gmail.com");
    utenteEsistente.setIndirizzo("Via San Francesco 29");
    utenteEsistente.setPassword("ABCD12345");

    assertEquals(true,UtenteDAO.doUpdate(utenteEsistente));
    
    System.out.println("Utente Password Aggiornata "+utenteEsistente.getPassword());
    }
    
    
    
    @Test
    public void TestModificaDatiUtenteNonEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    utenteNonEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
        
       
       this.utenteNonEsistente.setNome("luigi");
       this.utenteNonEsistente.setCognome("Sodano");
       this.utenteNonEsistente.setEmail("luigisodano6@gmail.com");
       this.utenteNonEsistente.setIndirizzo("via Roma 41, Avellino");
       this.utenteNonEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
    	
    utenteNonEsistente = UtenteDAO.doRetrieveByKey(utenteNonEsistente.getEmail());
    utenteNonEsistente.setNome("PPEsistente2");
    utenteNonEsistente.setCognome("esistente2");
    utenteNonEsistente.setEmail("esistente@gmail.com");
    utenteNonEsistente.setIndirizzo("Via San Francesco 29");
    utenteNonEsistente.setPassword("ABCD12345");

    assertNotEquals(false,UtenteDAO.doUpdate(utenteNonEsistente));
    }
    
    @Test
    public void TestModificaDatiUtenteNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    UtenteDAO = new RegistrazioneModelDS2(ds);
    	assertThrows(NullPointerException.class, () ->{
    		UtenteDAO.doUpdate(null);
        });
    }
    
    
    
    @Test
    public void TestTrovaTuttiUtenti() throws Exception {
 
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	   
	   // utenteEsistente = new UtenteBean();
	    UtenteDAO = new RegistrazioneModelDS2(ds);
	    
	     
    
    
    Collection<UtenteBean> expected = UtenteDAO.doRetrieveAll("nome");
    assertEquals(expected, UtenteDAO.doRetrieveAll("nome"));
    }

    
   
	
	@After
    public void tearDown() throws SQLException {
        // Pulisci il database dopo l'esecuzione dei test
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            connection.createStatement().execute("DROP ALL OBJECTS DELETE FILES");
        }
	}
	
	
	
	
}
