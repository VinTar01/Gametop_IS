package test.java;

import gestioneAccount.PersonaleBean;
import gestioneAccount.UtenteBean;
//import it.unisa.utils.PasswordHasher;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.h2.Driver;
//import org.junit.After;
import org.junit.Assert;
//import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class TestLoginDAO {
   //rappresenta la connessione al DB
   private Connection db;
   //classi per l'accesso al DB
   private LoginModelDS LoginDAO;
   private RegistrazioneModelDS UtenteDAO;
   private GestoreRuoliModelDS PersonaleDAO;
   //bean per rappresentare Personale e Utente
   private PersonaleBean PersonaleEsistente;
   private PersonaleBean PersonaleNonEsistente;
   private UtenteBean utenteEsistente;
   private UtenteBean utenteNonEsistente;
   //Oggetto tester per l'interazione con il database.
   private static IDatabaseTester tester;
   //Rappresenta la sorgente dei dati per l'accesso al database.
   private DataSource ds;

   @BeforeEach
   public void setUp() throws SQLException, Exception {
      //configurazione del tester per un database H2 in memoria
      tester = new JdbcDatabaseTester(Driver.class.getName(), "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'resources/db/init/schema.sql'", "PROVA", "");
      //è un'operazione che ripristina lo stato del database allo stato iniziale prima dell'esecuzione di ciascun test. 
      //Ciò è utile per garantire che il database sia in uno stato coerente e noto prima di eseguire i test
      tester.setSetUpOperation(DatabaseOperation.REFRESH);
      //è un'operazione che elimina tutti i dati presenti nel database dopo l'esecuzione di ciascun test. 
      //Ciò è utile per pulire il database e assicurarsi che i test successivi partano da uno stato pulito.
      /*queste configurazioni assicurano che prima di ogni test il database venga ripristinato allo stato iniziale (REFRESH), 
      e dopo ogni test vengano eliminati tutti i dati (DELETE_ALL) per garantire l'isolamento tra i singoli test */
      tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
      /*creo un mock di DataSource.class, lo converto sa Object a DataSource e lo asseno a this.ds
      un mock di Mockito permette di simulae il comportamento di oggetti reali senza dover interagire con le vere implementazioni di classi o oggetti.
      in questo caso creando un mock per un datasource per simulare il comportamento di un datasource 
      senza effettivamente connettersi a un database reale.
      Questo è utile nei test unitari in cui si desidera concentrarsi solo sulla logica della classe in questione
      e non sulle dipendenze esterne come il database*/
      this.ds = (DataSource)Mockito.mock(DataSource.class); 
      /*definisce il comportamento che dovrebbe essere simulato quando il metodo getConnection()
       viene chiamato sull'oggetto mock this.ds
       Quando getConnection() verra chiamato su this.ds
       restituisce l'oggetto di connessione dal tester (tester.getConnection()), 
       e poi prende la connessione effettiva da questo oggetto (getConnection()). */
      Mockito.when(this.ds.getConnection()).thenReturn(tester.getConnection().getConnection());
      this.LoginDAO = new LoginModelDS(this.ds);
      this.UtenteDAO = new RegistrazioneModelDS(this.ds);
      this.PersonaleDAO = new GestoreRuoliModelDS(this.ds);
      this.PersonaleEsistente = new PersonaleBean();
      this.PersonaleEsistente.setNome("luigi");
      this.PersonaleEsistente.setCognome("Sodano");
      this.PersonaleEsistente.setEmail("luigiPersonale@gmail.com");
      this.PersonaleEsistente.setRuolo("Gestore Ordini");
      this.PersonaleEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0");
      this.PersonaleDAO.inserisciPersonale(this.PersonaleEsistente);//salvo nel db il Personale creato
      this.utenteEsistente = new UtenteBean();
      this.utenteEsistente.setNome("luigi");
      this.utenteEsistente.setCognome("Sodano");
      this.utenteEsistente.setEmail("luigi@gmail.com");
      this.utenteEsistente.setIndirizzo("via Roma 41, Avellino");
      this.utenteEsistente.setPassword("bbb7b19fe1ed2f32883d32234246d88462df528f1a05d358957aeed805a447e0");
      this.UtenteDAO.doSave(this.utenteEsistente); //salvo nel db l'utente creato
      //creo un bean per un utente ed un personale che non esistono nel db
      this.utenteNonEsistente = new UtenteBean();
      this.utenteNonEsistente.setNome("Utente");
      this.utenteNonEsistente.setCognome("Non esistente");
      this.utenteNonEsistente.setEmail("UtenteNonEsistente@gmail.com");
      this.utenteNonEsistente.setIndirizzo("Via San Nessuno 29");
      this.utenteNonEsistente.setPassword("AdminAdmin");
      this.PersonaleNonEsistente = new PersonaleBean();
      this.PersonaleNonEsistente.setNome("Utente");
      this.PersonaleNonEsistente.setCognome("Non esistente");
      this.PersonaleNonEsistente.setEmail("PersonaleNonEsistente@gmail.com");
      this.PersonaleNonEsistente.setPassword("AdminAdmin");
      this.PersonaleNonEsistente.setRuolo("Gestore Prodotti");
   }

   @Test
   public void TestTrovaUtenteEsistente() throws SQLException {
      Assertions.assertEquals(this.utenteEsistente, this.LoginDAO.doRetrieveByKey(this.utenteEsistente.getEmail()));
   }

   @Test
   public void TestTrovaUtenteNonEsistente() throws SQLException {
      Assert.assertNotEquals(this.utenteNonEsistente, this.LoginDAO.doRetrieveByKey(this.utenteNonEsistente.getEmail()));
   }

   @Test
   public void TestTrovaUtenteNull() throws SQLException {
      Assert.assertNotEquals(this.utenteEsistente, this.LoginDAO.doRetrieveByKey((String)null));
   }

   @Test
   public void TestTrovaPersonaleEsistente() throws SQLException {
      Assertions.assertEquals(this.PersonaleEsistente, this.LoginDAO.doRetrieveByKeyPersonale(this.PersonaleEsistente.getEmail()));
   }

   @Test
   public void TestTrovaPersonaleNonEsistente() throws SQLException {
      Assert.assertNotEquals(this.PersonaleNonEsistente, this.LoginDAO.doRetrieveByKeyPersonale(this.PersonaleNonEsistente.getEmail()));
   }

   @Test
   public void TestTrovaPersonalenull() throws SQLException {
      Assert.assertNotEquals(this.PersonaleEsistente, this.LoginDAO.doRetrieveByKeyPersonale((String)null));
   }

   /* 
   @Test
   public void TestVerificaLogin() throws SQLException, ClassNotFoundException {
      String email = "luigi@gmail.com";
      String password = PasswordHasher.hash("AdminAdmin");
      Assertions.assertEquals(this.LoginDAO.doRetrieveByKey(email), this.LoginDAO.validazione(email, password));
   }

   @Test
   public void TestVerificaLoginUteneteNull() throws SQLException, ClassNotFoundException {
      Assert.assertNotEquals(this.LoginDAO.doRetrieveByKey("luigi@gmail.com"), this.LoginDAO.validazione((String)null, (String)null));
   }

   @Test
   public void TestVerificaLoginPersonale() throws SQLException, ClassNotFoundException {
      String email = "luigiPersonale@gmail.com";
      String password = PasswordHasher.hash("AdminAdmin");
      Assertions.assertEquals(this.PersonaleDAO.doRetrieveByKey(email), this.LoginDAO.validazionePersonale(email, password));
      System.out.println("Accesso consentito al " + this.PersonaleDAO.doRetrieveByKey(email).getRuolo());
   }

   @Test
   public void TestVerificaLoginPersonaleNull() throws SQLException, ClassNotFoundException {
      Assert.assertNotEquals(this.PersonaleDAO.doRetrieveByKey("luigiPersonale@gmail.com"), this.LoginDAO.validazionePersonale((String)null, (String)null));
   }

   @Test
   public void TestVerificaLoginNonRegistrato() throws SQLException, ClassNotFoundException {
      String email = "UtenteNonEsistente@gmail.com";
      String password = PasswordHasher.hash("AdminAdmin");
      Assert.assertNotEquals(this.utenteNonEsistente, this.LoginDAO.validazione(email, password));
   }

   @Test
   public void TestVerificaLoginPeronaleNonInserito() throws SQLException, ClassNotFoundException {
      String email = "PersonaleNonEsistente@gmail.com";
      String password = PasswordHasher.hash("AdminAdmin");
      Assert.assertNotEquals(this.PersonaleNonEsistente, this.LoginDAO.validazionePersonale(email, password));
   }*/

   @AfterEach
   public void tearDown() throws SQLException {
      System.out.println("Sono entrato nella tearDown");
      this.UtenteDAO.doDelete(this.utenteEsistente);
      this.UtenteDAO.doDelete(this.utenteNonEsistente);
      this.PersonaleDAO.doDelete(this.PersonaleEsistente);
   }
}