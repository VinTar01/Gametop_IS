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

class TestLoginDAO {
	
	private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private DataSource dataSource;
    
    private UtenteBean utenteEsistente, utenteNonEsistente;
    private PersonaleBean personaleEsistente, personaleNonEsistente;

    private RegistrazioneModelDS UtenteDAO;
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
         
       
        System.out.println(utenteEsistente.getEmail() + " " + utenteEsistente.getNome());
    }
    
    
   
	
	/*@Test   
	public void testUtente() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbConnection.createStatement();            String query = "SELECT * FROM Cliente WHERE email = 'luigisodano@gmail.com'";
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                // Stampa i dettagli della persona                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Cognome: " + rs.getString("cognome"));
                System.out.println("Email: " + rs.getString("email"));
                // Aggiungi altre colonne se necessario    
                assertEquals("luigisodano@gmail.com", rs.getString("email"));
                
         }        } 
                catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
    
	@Test
	void testTrovaUtenteEsistente() throws Exception {
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
	    UtenteDAO = new RegistrazioneModelDS(ds);
        
       
       this.utenteEsistente.setNome("luigi");
       this.utenteEsistente.setCognome("Sodano");
       this.utenteEsistente.setEmail("luigisodano27@gmail.com");
       this.utenteEsistente.setIndirizzo("via Roma 41, Avellino");
       this.utenteEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
       this.UtenteDAO.doSave(this.utenteEsistente); //salvo nel db l'utente creato
         
		assertEquals(utenteEsistente, LoginDAO.doRetrieveByKey(utenteEsistente.getEmail()));
	}
	
	
	@Test
	void testTrovaUtenteNonEsistente() throws Exception {
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
	    UtenteDAO = new RegistrazioneModelDS(ds);
        
       
       this.utenteNonEsistente.setNome("luigi");
       this.utenteNonEsistente.setCognome("Sodano");
       this.utenteNonEsistente.setEmail("luigisodano85@gmail.com");
       this.utenteNonEsistente.setIndirizzo("via Roma 41, Avellino");
       this.utenteNonEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
      
		assertNotEquals(utenteNonEsistente, LoginDAO.doRetrieveByKey(utenteNonEsistente.getEmail()));
	}
	
	
	
	@Test
    public void testTrovaUtenteNull() throws Exception {
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
	    UtenteDAO = new RegistrazioneModelDS(ds);
	    
	    
	      
	       assertNotEquals(utenteEsistente,LoginDAO.doRetrieveByKey(null));
    }
	
	
	@Test
	void testTrovaPersonaleEsistente() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    personaleEsistente = new PersonaleBean();
	    LoginDAO = new LoginModelDS(ds);
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
        
       
       this.personaleEsistente.setNome("Pippo");
       this.personaleEsistente.setCognome("Franco");
       this.personaleEsistente.setEmail("pippofranco27@gmail.com");
       this.personaleEsistente.setRuolo("Gestore ordini");
       this.personaleEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
       this.PersonaleDAO.inserisciPersonale(this.personaleEsistente); //salvo nel db l'utente creato
         
		assertEquals(this.personaleEsistente, LoginDAO.doRetrieveByKeyPersonale("pippofranco27@gmail.com"));
	}
	
	
@Test
	void testTrovaPersonaleNonEsistente() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    personaleNonEsistente = new PersonaleBean();
	    LoginDAO = new LoginModelDS(ds);
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
        
       
       this.personaleNonEsistente.setNome("Pippo");
       this.personaleNonEsistente.setCognome("Franco");
       this.personaleNonEsistente.setEmail("pippofranco15@gmail.com");
       this.personaleNonEsistente.setRuolo("Gestore ordini");
       this.personaleNonEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0"); 
      
         
		assertNotEquals(personaleNonEsistente, LoginDAO.doRetrieveByKeyPersonale(personaleNonEsistente.getEmail()));
	}
	
	@Test
	void testTrovaPersonaleNull() throws Exception {
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
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
        
       
     
         
		assertNotEquals(personaleEsistente,  LoginDAO.doRetrieveByKeyPersonale(null));
	}
	
	@After
    public void tearDown() throws SQLException {
        // Pulisci il database dopo l'esecuzione dei test
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            connection.createStatement().execute("DROP ALL OBJECTS DELETE FILES");
        }
	}
	
	
	
	
}
