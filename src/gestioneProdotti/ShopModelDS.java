    package gestioneProdotti;

import it.unisa.utils.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class ShopModelDS implements ShopModel<ShopBean> {
   private DataSource ds = null;

   public ShopModelDS(DataSource ds) {
      this.ds = ds;
   }

   public Collection<ShopBean> doRetrieveAll(String order) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT * FROM Prodotto";
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
            ShopBean bean = new ShopBean();
            bean.setCodiceProdotto(rs.getString("codiceProdotto"));
            bean.setIdCategoria(rs.getString("idCategoria"));
            bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
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

   public ShopBean doRetrieveByKey(String code) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      ShopBean bean = new ShopBean();
      String selectSQL = "SELECT * FROM Prodotto WHERE codiceProdotto= ?";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, code);
         Utility.print(new String[]{"doRetrieveByKey: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            bean.setCodiceProdotto(rs.getString("codiceProdotto"));
            bean.setIdCategoria(rs.getString("idCategoria"));
            bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
            bean.setTitolo(rs.getString("titolo"));
            bean.setDescrizione(rs.getString("descrizione"));
            bean.setPrezzo(rs.getInt("prezzo"));
            bean.setCopertina(rs.getString("copertina"));
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

   public void doSave(ShopBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String insertSQL = "INSERT INTO Prodotto  (codiceProdotto,idcategoria,quantitaProdotto,titolo,descrizione,prezzo,copertina) VALUES (?,?,?,?,?,?,?)";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(insertSQL);
         preparedStatement.setString(1, item.getCodiceProdotto());
         preparedStatement.setString(2, item.getIdCategoria());
         preparedStatement.setInt(3, item.getQuantitaProdotto());
         preparedStatement.setString(4, item.getTitolo());
         preparedStatement.setString(5, item.getDescrizione());
         preparedStatement.setInt(6, item.getPrezzo());
         if (item.getIdCategoria().equals("Console")) {
            preparedStatement.setString(7, "image/Console/" + item.getCopertina());
         } else if (item.getIdCategoria().equals("Videogiochi")) {
            preparedStatement.setString(7, "image/Videogiochi/" + item.getCopertina());
         } else if (item.getIdCategoria().equals("Accessori")) {
            preparedStatement.setString(7, "image/Accessori/" + item.getCopertina());
         }

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

   public boolean doUpdate(ShopBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "UPDATE Prodotto SET idcategoria = ?, quantitaProdotto = ? , titolo = ? ,descrizione = ?,prezzo = ?,copertina = ? WHERE codiceProdotto = ? ";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getIdCategoria());
         preparedStatement.setInt(2, item.getQuantitaProdotto());
         preparedStatement.setString(3, item.getTitolo());
         preparedStatement.setString(4, item.getDescrizione());
         preparedStatement.setInt(5, item.getPrezzo());
         if (item.getIdCategoria().equals("Console")) {
            preparedStatement.setString(6, "image/Console/" + item.getCopertina());
         } else if (item.getIdCategoria().equals("Videogiochi")) {
            preparedStatement.setString(6, "image/Videogiochi/" + item.getCopertina());
         } else if (item.getIdCategoria().equals("Accessori")) {
            preparedStatement.setString(6, "image/Accessori/" + item.getCopertina());
         }

         preparedStatement.setString(7, item.getCodiceProdotto());
         Utility.print(new String[]{"doUpdate: " + preparedStatement.toString()});
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

      return true;
   }

   public boolean doDelete(ShopBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "DELETE FROM Prodotto WHERE codiceProdotto = ?";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getCodiceProdotto());
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

   public Collection<ShopBean> doRetrieveAllAccessori(String order) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT * FROM Prodotto where idcategoria = 'Accessori' ";
      if (order != null && !order.equals("")) {
         selectSQL = selectSQL + "ORDER BY " + order;
      }

      LinkedList products = new LinkedList();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         Utility.print(new String[]{"doRetriveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            ShopBean bean = new ShopBean();
            bean.setCodiceProdotto(rs.getString("codiceProdotto"));
            bean.setIdCategoria(rs.getString("idCategoria"));
            bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
            bean.setTitolo(rs.getString("titolo"));
            bean.setDescrizione(rs.getString("descrizione"));
            bean.setPrezzo(rs.getInt("prezzo"));
            bean.setCopertina(rs.getString("copertina"));
            products.add(bean);
         }
      } finally {
         if (preparedStatement != null) {
            preparedStatement.close();
         }

         if (connection != null) {
            connection.close();
         }

      }

      return products;
   }

   public Collection<ShopBean> doRetrieveAllVideogiochi(String order) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT * FROM Prodotto where idcategoria = 'Videogiochi' ";
      if (order != null && !order.equals("")) {
         selectSQL = selectSQL + "ORDER BY " + order;
      }

      LinkedList products = new LinkedList();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         Utility.print(new String[]{"doRetriveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            ShopBean bean = new ShopBean();
            bean.setCodiceProdotto(rs.getString("codiceProdotto"));
            bean.setIdCategoria(rs.getString("idCategoria"));
            bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
            bean.setTitolo(rs.getString("titolo"));
            bean.setDescrizione(rs.getString("descrizione"));
            bean.setPrezzo(rs.getInt("prezzo"));
            bean.setCopertina(rs.getString("copertina"));
            products.add(bean);
         }
      } finally {
         if (preparedStatement != null) {
            preparedStatement.close();
         }

         if (connection != null) {
            connection.close();
         }

      }

      return products;
   }

   public Collection<ShopBean> doRetrieveAllConsole(String order) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT * FROM Prodotto where idcategoria = 'Console' ";
      if (order != null && !order.equals("")) {
         selectSQL = selectSQL + "ORDER BY " + order;
      }

      LinkedList products = new LinkedList();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         Utility.print(new String[]{"doRetriveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            ShopBean bean = new ShopBean();
            bean.setCodiceProdotto(rs.getString("codiceProdotto"));
            bean.setIdCategoria(rs.getString("idCategoria"));
            bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
            bean.setTitolo(rs.getString("titolo"));
            bean.setDescrizione(rs.getString("descrizione"));
            bean.setPrezzo(rs.getInt("prezzo"));
            bean.setCopertina(rs.getString("copertina"));
            products.add(bean);
         }
      } finally {
         if (preparedStatement != null) {
            preparedStatement.close();
         }

         if (connection != null) {
            connection.close();
         }

      }

      return products;
   }
}