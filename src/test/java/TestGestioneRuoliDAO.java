import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

//import gestioneAccount.GestorePersonaleModelDS;

import gestioneAccount.LoginModelDS;
import gestioneAccount.PersonaleBean;
import gestioneAccount.UtenteBean;
import testingM.GestorePersonaleModelDS2;
import testingM.RegistrazioneModelDS2;

class TestGestioneRuoliDAO {

	private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private DataSource dataSource;
    
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
    public void TestFindPersonaleByEmailExisting() throws Exception {
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
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
        
  
       
      
      this.personaleEsistente.setNome("francesco");
      this.personaleEsistente.setCognome("leo");
      this.personaleEsistente.setEmail("francesco1@gmail.com");
      this.personaleEsistente.setPassword("ABCD123456");
      this.personaleEsistente.setRuolo("Gestore Ordini");
      this.PersonaleDAO.inserisciPersonale(this.personaleEsistente);
         
		assertEquals(personaleEsistente, PersonaleDAO.doRetrieveByKey(personaleEsistente.getEmail()));
	}
    
    
    
    @Test
    public void TestFindPersonaleByEmailNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
       
      
       assertNotEquals(this.personaleEsistente, this.PersonaleDAO.doRetrieveByKey((String)null));
    }
       
    
    @Test
    public void TestFindPersonaleByEmailNotExisting() throws Exception {
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
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
       
      
      this.personaleNonEsistente.setNome("francesco");
      this.personaleNonEsistente.setCognome("leo");
      this.personaleNonEsistente.setEmail("francesco2@gmail.com");
      this.personaleNonEsistente.setPassword("ABCD123456");
      this.personaleNonEsistente.setRuolo("Gestore Ordini");
      
         
		assertNotEquals(personaleNonEsistente, PersonaleDAO.doRetrieveByKey(personaleNonEsistente.getEmail()));
	}
    
    
    @Test
    public void TestDeletePersonale() throws Exception {
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
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
        
      
      this.personaleEsistente.setNome("francesco");
      this.personaleEsistente.setCognome("leo");
      this.personaleEsistente.setEmail("francesco3@gmail.com");
      this.personaleEsistente.setPassword("ABCD123456");
      this.personaleEsistente.setRuolo("Gestore Ordini");
      this.PersonaleDAO.inserisciPersonale(this.personaleEsistente);
      this.PersonaleDAO.doDelete(this.personaleEsistente);
      assertNotEquals(this.personaleEsistente, this.PersonaleDAO.doRetrieveByKey("francesco3@gmail.com")); 
    }

    @Test
    public void TestDeletePersonaleNonEsistente() throws Exception {
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
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
       
      
      this.personaleNonEsistente.setNome("francesco");
      this.personaleNonEsistente.setCognome("leo");
      this.personaleNonEsistente.setEmail("francesco6@gmail.com");
      this.personaleNonEsistente.setPassword("ABCD123456");
      this.personaleNonEsistente.setRuolo("Gestore Ordini");
      
      assertEquals(false, this.PersonaleDAO.doDelete(this.personaleNonEsistente));
    }

    @Test
    public void TestDeletePersonaleNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
       assertThrows(NullPointerException.class, () -> {
          this.PersonaleDAO.doDelete((PersonaleBean)null);
       });
    }

    @Test
    public void TestFindTuttoPersonale() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
    	
    	
    	
       Collection<PersonaleBean> expected = this.PersonaleDAO.stampaTuttoIlPersonale();
      
       assertEquals(expected, this.PersonaleDAO.stampaTuttoIlPersonale());
    }

    @Test
    public void TestUpdatePersonale() throws Exception {
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
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
	    
       this.personaleEsistente.setNome("Francesco3");
       this.personaleEsistente.setCognome("Leo3");
       this.personaleEsistente.setPassword("ABCD1234");
       this.personaleEsistente.setEmail("kekkoleo12@gmail.com");
       this.personaleEsistente.setRuolo("Gestore prodotti");
       this.PersonaleDAO.inserisciPersonale(this.personaleEsistente);
       
       this.personaleEsistente.setNome("Francesco4");
       this.personaleEsistente.setCognome("Leo4");
       this.personaleEsistente.setPassword("ABCD12345");
       this.personaleEsistente.setEmail("kekkoleo13@gmail.com");
       this.personaleEsistente.setRuolo("Gestore prodotti");
       this.PersonaleDAO.doUpdate(this.personaleEsistente);
       
       this.personaleEsistente = this.PersonaleDAO.doRetrieveByKey("kekkoleo13@gmail.com");
       assertEquals(this.personaleEsistente.getNome(), this.PersonaleDAO.doRetrieveByKey("kekkoleo13@gmail.com").getNome());
       assertEquals(this.personaleEsistente.getCognome(), this.PersonaleDAO.doRetrieveByKey("kekkoleo13@gmail.com").getCognome());
       assertEquals(this.personaleEsistente.getEmail(), this.PersonaleDAO.doRetrieveByKey("kekkoleo13@gmail.com").getEmail());
       assertEquals(this.personaleEsistente.getPassword(), this.PersonaleDAO.doRetrieveByKey("kekkoleo13@gmail.com").getPassword());
       assertEquals(this.personaleEsistente.getRuolo(), this.PersonaleDAO.doRetrieveByKey("kekkoleo13@gmail.com").getRuolo());
    }

    @Test
    public void TestUpdatePersonaleNonEsistente() throws Exception {
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
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
	    
       
      
       this.personaleNonEsistente.setNome("Francesco4");
       this.personaleNonEsistente.setCognome("Leo4");
       this.personaleNonEsistente.setPassword("ABCD12345");
       this.personaleNonEsistente.setEmail("kekkoleononesiste13@gmail.com");
       this.personaleNonEsistente.setRuolo("Gestore prodotti");
       
       
       personaleNonEsistente = PersonaleDAO.doRetrieveByKey(personaleNonEsistente.getEmail());
       personaleNonEsistente.setNome("Francesco5");
       personaleNonEsistente.setCognome("Leo5");
       personaleNonEsistente.setEmail("kekkoleononesistente14@gmail.com");
       personaleNonEsistente.setRuolo("Gestore prodotti");
       personaleNonEsistente.setPassword("ABCD1234");

       assertNotEquals(false, PersonaleDAO.doUpdate(personaleNonEsistente));
      }
    
    
  
    
    @Test
    public void TestUpdatePersonaleNull() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    PersonaleDAO = new GestorePersonaleModelDS2(ds);
    	assertThrows(NullPointerException.class, () ->{
    		PersonaleDAO.doUpdate(null);
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
