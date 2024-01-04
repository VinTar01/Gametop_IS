    package gestioneAccount;

import gestioneProdotti.ShopModel;
import it.unisa.utils.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class RegistrazioneModelDS implements ShopModel<UtenteBean> {
   private DataSource ds = null;

   public RegistrazioneModelDS(DataSource ds) {
      this.ds = ds;
   }

   public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT * FROM Cliente";
      if (order != null && !order.equals("")) {
         selectSQL = selectSQL + " ORDER BY " + order;
      }

      LinkedList products = new LinkedList();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         Utility.print(new String[]{"doRetrieveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            UtenteBean bean = new UtenteBean();
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setPassword(rs.getString("password"));
            products.add(bean);
         }
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

      return products;
   }

   public UtenteBean doRetrieveByKey(String code) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      UtenteBean bean = new UtenteBean();
      String selectSQL = "SELECT * FROM Cliente WHERE email = ?";

      try {
         connection = this.ds.getConnection();
         System.out.println("connection" + connection.toString());
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

   /*public UtenteBean doRetrieveByKey(String email, String password) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      UtenteBean bean = new UtenteBean();
      String selectSQL = "SELECT * FROM Cliente WHERE email = ? and password = ?";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, email);
         preparedStatement.setString(1, password);
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
   }*/

   public void doSave(UtenteBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String insertSQL = "INSERT INTO Cliente  (nome,cognome,email,indirizzo,password) VALUES (?,?,?,?,?)";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(insertSQL);
         preparedStatement.setString(1, item.getNome());
         preparedStatement.setString(2, item.getCognome());
         preparedStatement.setString(3, item.getEmail());
         preparedStatement.setString(4, item.getIndirizzo());
         preparedStatement.setString(5, item.getPassword());
         Utility.print(new String[]{"doSave: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
         System.out.println("ds" + this.ds.toString());
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

   public boolean doUpdate(UtenteBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "UPDATE Cliente SET nome = ?, cognome = ? , email = ? , indirizzo = ?, password = ? WHERE email = ? ";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getNome());
         preparedStatement.setString(2, item.getCognome());
         preparedStatement.setString(3, item.getEmail());
         preparedStatement.setString(4, item.getIndirizzo());
         preparedStatement.setString(5, item.getPassword());
         preparedStatement.setString(6, item.getEmail());
         Utility.print(new String[]{"doUpdate: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
         return true;
      } catch (SQLException var29) {
         var29.printStackTrace();
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

      return false;
   }

   public boolean doDelete(UtenteBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "DELETE FROM Cliente WHERE email = ?";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getEmail());
         Utility.print(new String[]{"doDelete: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
         return true;
      } catch (SQLException var29) {
         var29.printStackTrace();
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

      return false;
   }

   public boolean doUpdatePassword(UtenteBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "UPDATE Cliente SET  password = ? WHERE email = ? ";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getPassword());
         preparedStatement.setString(2, item.getEmail());
         Utility.print(new String[]{"doUpdate: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
         return true;
      } catch (SQLException var29) {
         var29.printStackTrace();
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

      return false;
   }
}