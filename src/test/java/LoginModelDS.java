 package test;

import gestioneAccount.PersonaleBean;
import gestioneAccount.UtenteBean;
import it.unisa.utils.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class LoginModelDS {
   private DataSource ds = null;

   public LoginModelDS(DataSource ds) {
      this.ds = ds;
   }

   public PersonaleBean doRetrieveByKeyPersonale(String code) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      PersonaleBean bean = new PersonaleBean();
      String selectSQL = "SELECT * FROM Personale WHERE email= ?";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, code);
         Utility.print(new String[]{"doRetrieveByKey: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setPassword(rs.getString("password"));
            bean.setRuolo(rs.getString("ruolo"));
         }

         Utility.print(new String[]{bean.toString()});
         return bean;
      } finally {
         if (preparedStatement != null) {
            preparedStatement.close();
         }

      }
   }

   public UtenteBean doRetrieveByKey(String code) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      UtenteBean bean = new UtenteBean();
      String selectSQL = "SELECT * FROM cliente WHERE email= ?";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, code);
         Utility.print(new String[]{"doRetrieveByKey: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setPassword(rs.getString("password"));
         }

         Utility.print(new String[]{bean.toString()});
         return bean;
      } finally {
         if (preparedStatement != null) {
            preparedStatement.close();
         }

      }
   }
/* 
   public boolean validate(UtenteBean item) throws ClassNotFoundException, SQLException {
      boolean status = false;
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT  *  FROM cliente WHERE email= ? and password = ? ";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, item.getEmail());
         preparedStatement.setString(2, item.getPassword());
         System.out.println(preparedStatement);
         ResultSet rs = preparedStatement.executeQuery();
         status = rs.next();
      } catch (SQLException var7) {
         this.printSQLException(var7);
      }

      return status;
   }

   public PersonaleBean validazionePersonale(String email, String password) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      PersonaleBean bean = new PersonaleBean();
      String selectSQL = "SELECT * FROM Personale WHERE email = ? and password = ?";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, email);
         preparedStatement.setString(2, password);
         Utility.print(new String[]{"doRetrieveByKey: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setPassword(rs.getString("password"));
            bean.setRuolo(rs.getString("ruolo"));
         }

         Utility.print(new String[]{bean.toString()});
         return bean;
      } catch (SQLException var8) {
         var8.printStackTrace();
         return null;
      }
   }

   
   public UtenteBean validazione(String email, String password) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      UtenteBean bean = new UtenteBean();
      String selectSQL = "SELECT * FROM Cliente WHERE email = ? and password = ?";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, email);
         preparedStatement.setString(2, password);
         Utility.print(new String[]{"doRetrieveByKey: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setPassword(rs.getString("password"));
         }

         Utility.print(new String[]{bean.toString()});
         return bean;
      } catch (SQLException var8) {
         var8.printStackTrace();
         return null;
      }*/
   private void printSQLException(SQLException e) {
   }
}
    