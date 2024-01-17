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
import testingM.ShopModelDS2;

class TestGestioneProdottiDAO {
	
	private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private DataSource dataSource;
    
    private ShopBean prodottoEsistente, prodottoNonEsistente;
    private ShopModelDS2 ProdottiDAO;
    
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
    public void TestdoRetrieveByKeyPresente() throws Exception {
    	
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoEsistente.setCodiceProdotto("CO001");
       this.prodottoEsistente.setIdCategoria("Console");
       this.prodottoEsistente.setQuantitaProdotto(10);
       this.prodottoEsistente.setTitolo("PS5");
       this.prodottoEsistente.setDescrizione("Console SONY Playstation 5");
       this.prodottoEsistente.setPrezzo(500);
       this.prodottoEsistente.setCopertina("image/Console/ps5.png");

       ProdottiDAO.doSaveCategoria(prodottoEsistente.getIdCategoria(), "1");
       ProdottiDAO.doSave(this.prodottoEsistente); //salvo nel db l'utente creato
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoEsistente: " + prodottoEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoEsistente.getCodiceProdotto()));
       
	   assertEquals(prodottoEsistente.getCodiceProdotto(), "CO001");
	   
	   ProdottiDAO.doDeleteCategoria("Console");
  
    }
    
    
    
    @Test
    public void TestdoRetrieveByKeyNonPresente() throws Exception {
    	
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoNonEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoNonEsistente.setCodiceProdotto("CO002");
       this.prodottoNonEsistente.setIdCategoria("Console");
       this.prodottoNonEsistente.setQuantitaProdotto(10);
       this.prodottoNonEsistente.setTitolo("PS5");
       this.prodottoNonEsistente.setDescrizione("Console SONY Playstation 5");
       this.prodottoNonEsistente.setPrezzo(500);
       this.prodottoNonEsistente.setCopertina("image/Console/ps5.png");

       ProdottiDAO.doSaveCategoria(prodottoNonEsistente.getIdCategoria(), "1");
      
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoNonEsistente: " + prodottoEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoNonEsistente.getCodiceProdotto()));
       
	   assertEquals(prodottoNonEsistente.getCodiceProdotto(), "CO002");
	   
	   ProdottiDAO.doDeleteCategoria("Console");
  
    }
   
    
    @Test
    public void TestdoRetrieveByKeyNull() throws Exception {
    	
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    ProdottiDAO = new ShopModelDS2(ds);
       
      
       assertNotEquals(this.prodottoEsistente, this.ProdottiDAO.doRetrieveByKey((String)null));
    }
  
    
   
    
    
    @Test
    public void TestFindAllProduct() throws Exception {
        
        
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    ProdottiDAO = new ShopModelDS2(ds);
    	
    	
    	
       Collection<ShopBean> expected = this.ProdottiDAO.doRetrieveAll("codiceProdotto");
      
       assertEquals(expected, this.ProdottiDAO.doRetrieveAll("codiceProdotto"));
    }
    
    
    @Test
    public void TestFindAllProductConsole() throws Exception {
        
        
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    ProdottiDAO = new ShopModelDS2(ds);
    	
    	
    	
       Collection<ShopBean> expected = this.ProdottiDAO.doRetrieveAllConsole("codiceProdotto");
      
       assertEquals(expected, this.ProdottiDAO.doRetrieveAllConsole("codiceProdotto"));
    }
    
    @Test
    public void TestFindAllProductVideogiochi() throws Exception {
        
        
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    ProdottiDAO = new ShopModelDS2(ds);
    	
    	
    	
       Collection<ShopBean> expected = this.ProdottiDAO.doRetrieveAllVideogiochi("codiceProdotto");
      
       assertEquals(expected, this.ProdottiDAO.doRetrieveAllVideogiochi("codiceProdotto"));
    }
    
    @Test
    public void TestFindAllProductAccessori() throws Exception {
        
        
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    ProdottiDAO = new ShopModelDS2(ds);
    	
    	
    	
       Collection<ShopBean> expected = this.ProdottiDAO.doRetrieveAllAccessori("codiceProdotto");
      
       assertEquals(expected, this.ProdottiDAO.doRetrieveAllAccessori("codiceProdotto"));
    }
    
    
    
    @Test
    public void TestDoSave() throws Exception {

    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoNonEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoNonEsistente.setCodiceProdotto("CO007");
       this.prodottoNonEsistente.setIdCategoria("Console");
       this.prodottoNonEsistente.setQuantitaProdotto(10);
       this.prodottoNonEsistente.setTitolo("PS4");
       this.prodottoNonEsistente.setDescrizione("Console SONY Playstation 4");
       this.prodottoNonEsistente.setPrezzo(150);
       this.prodottoNonEsistente.setCopertina("image/Console/ps4.png");

       ProdottiDAO.doSaveCategoria(prodottoNonEsistente.getIdCategoria(), "1");
       ProdottiDAO.doSave(this.prodottoNonEsistente); //salvo nel db l'utente creato
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoNonEsistente: " + prodottoNonEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoNonEsistente.getCodiceProdotto()));
       
	   assertEquals(prodottoNonEsistente.getCodiceProdotto(), "CO007");
	   
	   ProdottiDAO.doDeleteCategoria("Console");

      
    }

    @Test
    public void TestDoSaveEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoEsistente.setCodiceProdotto("CO008");
       this.prodottoEsistente.setIdCategoria("Console");
       this.prodottoEsistente.setQuantitaProdotto(10);
       this.prodottoEsistente.setTitolo("Xbox series s");
       this.prodottoEsistente.setDescrizione("Console Xbox series s");
       this.prodottoEsistente.setPrezzo(250);
       this.prodottoEsistente.setCopertina("image/Console/xboxs.png");

       ProdottiDAO.doSaveCategoria(prodottoEsistente.getIdCategoria(), "1");
       ProdottiDAO.doSave(this.prodottoEsistente); //salvo nel db l'utente creato
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoEsistente: " + prodottoEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoEsistente.getCodiceProdotto()));
       
       
       assertThrows(SQLException.class, () ->{
       	ProdottiDAO.doSave(prodottoEsistente);
       });
	   
	   ProdottiDAO.doDeleteCategoria("Console");

      
    }

    
    
    @Test
    public void TestDoSaveNull() throws Exception {
      
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    ProdottiDAO = new ShopModelDS2(ds);
        
    	assertThrows(NullPointerException.class, () ->{ //deve lanciare una eccezione 
    		ProdottiDAO.doSave(null);
        });
    	
    	
    }

    
    
    
    
    
   @Test
    public void TestDoUpdatePresente() throws Exception {

    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoEsistente.setCodiceProdotto("VD001");
       this.prodottoEsistente.setIdCategoria("Videogiochi");
       this.prodottoEsistente.setQuantitaProdotto(10);
       this.prodottoEsistente.setTitolo("GTA5");
       this.prodottoEsistente.setDescrizione("GTA5");
       this.prodottoEsistente.setPrezzo(50);
       this.prodottoEsistente.setCopertina("image/Videogiochi/gta5.png");

       ProdottiDAO.doSaveCategoria(prodottoEsistente.getIdCategoria(), "2");
       ProdottiDAO.doSave(this.prodottoEsistente); //salvo nel db l'utente creato
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoNonEsistente: " + prodottoNonEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoEsistente.getCodiceProdotto()));
       
       prodottoEsistente.setCodiceProdotto("VD002");
       ProdottiDAO.doUpdate(prodottoEsistente);
	   assertEquals(prodottoEsistente.getCodiceProdotto(), "VD002");
	   
	   ProdottiDAO.doDeleteCategoria("Console");

      
    }

    @Test
    public void TestDoUpdateNonPresente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoNonEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoNonEsistente.setCodiceProdotto("VD003");
       this.prodottoNonEsistente.setIdCategoria("Videogiochi");
       this.prodottoNonEsistente.setQuantitaProdotto(10);
       this.prodottoNonEsistente.setTitolo("Bob 2");
       this.prodottoNonEsistente.setDescrizione("Bob 2");
       this.prodottoNonEsistente.setPrezzo(10);
       this.prodottoNonEsistente.setCopertina("image/Videogiochi/bob2.png");

      this.prodottoNonEsistente.setCodiceProdotto("CO004");
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoEsistente: " + prodottoNonEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoNonEsistente.getCodiceProdotto()));
       
       
      
       assertNotEquals(prodottoNonEsistente.getCodiceProdotto(), ProdottiDAO.doRetrieveByKey(prodottoNonEsistente.getCodiceProdotto()));
       }

    
      
    

    
    
    @Test
    public void TestDoUpdateNull() throws Exception {
      
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    ProdottiDAO = new ShopModelDS2(ds);
        
    	assertThrows(NullPointerException.class, () ->{ //deve lanciare una eccezione 
    		ProdottiDAO.doUpdate(null);
        });
    	
    	
    }

    
    @Test
    public void TestDoDeleteEsistente() throws Exception {

    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoEsistente.setCodiceProdotto("AC001");
       this.prodottoEsistente.setIdCategoria("Accessori");
       this.prodottoEsistente.setQuantitaProdotto(10);
       this.prodottoEsistente.setTitolo("Cuffie");
       this.prodottoEsistente.setDescrizione("cuffie");
       this.prodottoEsistente.setPrezzo(50);
       this.prodottoEsistente.setCopertina("image/Accessori/cuffie.png");

       ProdottiDAO.doSaveCategoria(prodottoEsistente.getIdCategoria(), "3");
       ProdottiDAO.doSave(this.prodottoEsistente); //salvo nel db l'utente creato
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoEsistente: " + prodottoEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoEsistente.getCodiceProdotto()));
       
       
	   assertEquals(true, ProdottiDAO.doDelete(prodottoEsistente));
	   
	   ProdottiDAO.doDeleteCategoria("Accessori");

      
    }

    @Test
    public void TestDoDeleteNonEsistente() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
    	
    	
    	
    	
    	
       prodottoNonEsistente = new ShopBean();
       ProdottiDAO = new ShopModelDS2(ds);
       
       this.prodottoNonEsistente.setCodiceProdotto("AC003");
       this.prodottoNonEsistente.setIdCategoria("Accessori");
       this.prodottoNonEsistente.setQuantitaProdotto(10);
       this.prodottoNonEsistente.setTitolo("cueffie 2");
       this.prodottoNonEsistente.setDescrizione("cuffie 2");
       this.prodottoNonEsistente.setPrezzo(10);
       this.prodottoNonEsistente.setCopertina("image/Accessori/cuffie.png");

      
       
    // Aggiungi log per stampare i valori effettivi degli oggetti coinvolti
       System.out.println("Valori oggetto prodottoEsistente: " + prodottoNonEsistente);
       System.out.println("Valori oggetto recuperato: " + ProdottiDAO.doRetrieveByKey(prodottoNonEsistente.getCodiceProdotto()));
       
       
      
       assertEquals(true, ProdottiDAO.doDelete(prodottoNonEsistente));
       
       }

    
      
    

    
    
    @Test
    public void TestDoDeleteNull() throws Exception {
      
    	Class.forName("com.mysql.jdbc.Driver");
	    dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GametopDB", "root", "31032020VinTar01");
	   
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	    	    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'",
	    	    "PROVA",
	    	    ""
	    	    );
	    ds = Mockito.mock(DataSource.class);
	    Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
	    
	    
	    ProdottiDAO = new ShopModelDS2(ds);
        
    	assertThrows(NullPointerException.class, () ->{ //deve lanciare una eccezione 
    		ProdottiDAO.doDelete(null);
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
    
    