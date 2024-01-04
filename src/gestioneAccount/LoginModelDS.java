    package gestioneAccount;

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
            System.out.println("PASSWORD DAO " + bean.getPassword());
            bean.setRuolo(rs.getString("ruolo"));
         }

         Utility.print(new String[]{bean.toString()});
         return bean;
      } finally {
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
         } finally {
            if (connection != null) {
               connection.close();
            }

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
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
         } finally {
            if (connection != null) {
               connection.close();
            }

         }

      }
   }

   private void printSQLException(SQLException e) {
   }
}
