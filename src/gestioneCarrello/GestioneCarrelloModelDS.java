    package gestioneCarrello;

import gestioneProdotti.ShopBean;
import it.unisa.utils.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class GestioneCarrelloModelDS {
   private DataSource ds = null;

   public GestioneCarrelloModelDS(DataSource ds) {
      this.ds = ds;
   }

   public void doSave(SessionCarrelloBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String insertSQL = "INSERT INTO Carrello   (idemail,codiceProdotto) VALUES (?,?)";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(insertSQL);
         preparedStatement.setString(1, item.getIdemail());
         preparedStatement.setString(2, item.getCodiceProdotto());
         Utility.print(new String[]{"doSave: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
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

   public void doSave2(SessionCarrelloBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String insertSQL = "INSERT INTO Storico   (idemail,codiceProdotto) VALUES (?,?)";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(insertSQL);
         preparedStatement.setString(1, item.getIdemail());
         preparedStatement.setString(2, item.getCodiceProdotto());
         Utility.print(new String[]{"doSave: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
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

   public boolean doDelete(SessionCarrelloBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "DELETE FROM carrello WHERE idemail=? and codiceProdotto = ? ";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getIdemail());
         preparedStatement.setString(2, item.getCodiceProdotto());
         Utility.print(new String[]{"doDelete: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
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

   public boolean doDelete2(SessionCarrelloBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "DELETE FROM Storico WHERE idemail=? and codiceProdotto = ? ";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getIdemail());
         preparedStatement.setString(2, item.getCodiceProdotto());
         Utility.print(new String[]{"doDelete: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
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

   public Carts<ShopBean> TrovaCarrello(String email) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT prodotto.codiceProdotto , titolo, descrizione, prezzo , copertina FROM carrello , prodotto , cliente where carrello.idemail = ? && carrello.idemail = cliente.email && carrello.codiceProdotto=prodotto.codiceProdotto;";
      Carts products = new Carts();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, email);
         Utility.print(new String[]{"doRetrieveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            ShopBean bean = new ShopBean();
            bean.setCodiceProdotto(rs.getString("codiceProdotto"));
            String s = rs.getString("codiceProdotto");
            System.out.println(s);
            bean.setTitolo(rs.getString("titolo"));
            bean.setDescrizione(rs.getString("descrizione"));
            bean.setPrezzo(rs.getInt("prezzo"));
            bean.setCopertina(rs.getString("copertina"));
            products.addItem(bean);
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

   public Collection<ShopBean> TrovaCarrello2(String email) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT prodotto.codiceProdotto , titolo, descrizione, prezzo , copertina FROM Storico , prodotto , cliente where Storico.idemail = ? && Storico.idemail = cliente.email && Storico.codiceProdotto=prodotto.codiceProdotto;";
      LinkedList products = new LinkedList();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, email);
         Utility.print(new String[]{"doRetrieveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            ShopBean bean = new ShopBean();
            bean.setCodiceProdotto(rs.getString("codiceProdotto"));
            String s = rs.getString("codiceProdotto");
            System.out.println(s);
            bean.setTitolo(rs.getString("titolo"));
            bean.setDescrizione(rs.getString("descrizione"));
            bean.setPrezzo(rs.getInt("prezzo"));
            bean.setCopertina(rs.getString("copertina"));
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

   public boolean deleteAll(String item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "DELETE FROM carrello WHERE idemail= ?";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item);
         Utility.print(new String[]{"doDelete: " + preparedStatement.toString()});
         preparedStatement.executeUpdate();
         connection.commit();
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
