    package gestioneAcquisti;

import it.unisa.utils.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class GestioneOrdiniModelDS {
   private DataSource ds = null;

   public GestioneOrdiniModelDS(DataSource ds) {
      this.ds = ds;
   }

   public Collection<OrdineBean> ritornaTuttiOrdiniDaControllare() throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT * FROM Ordine WHERE controllato= ? ";
      LinkedList products = new LinkedList();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, "false");
         Utility.print(new String[]{"doRetrieveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            OrdineBean bean = new OrdineBean();
            bean.setNumeroOrdine(rs.getString("numeroOrdine"));
            bean.setEmail(rs.getString("email"));
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setCap(rs.getString("cap"));
            bean.setComune(rs.getString("comune"));
            bean.setProvincia(rs.getString("provincia"));
            bean.setPrezzo(rs.getString("prezzo"));
            bean.setProdotti(rs.getString("prodotti"));
            bean.setControllato(rs.getString("controllato"));
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

   public OrdineBean doRetrieveByKey(String code) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      OrdineBean bean = new OrdineBean();
      String selectSQL = "SELECT * FROM Ordine WHERE numeroOrdine= ?";

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, code);
         Utility.print(new String[]{"doRetrieveByKey: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            bean.setNumeroOrdine(rs.getString("numeroOrdine"));
            bean.setEmail(rs.getString("email"));
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setCap(rs.getString("cap"));
            bean.setComune(rs.getString("comune"));
            bean.setProvincia(rs.getString("provincia"));
            bean.setPrezzo(rs.getString("prezzo"));
            bean.setProdotti(rs.getString("prodotti"));
            bean.setControllato(rs.getString("controllato"));
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

   public boolean doUpdate(OrdineBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "UPDATE Ordine SET email = ?, nome = ? , cognome = ? ,indirizzo = ?,cap = ?,comune = ?,provincia = ?,prezzo = ?,prodotti = ?,controllato =? WHERE numeroOrdine = ? ";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getEmail());
         preparedStatement.setString(2, item.getNome());
         preparedStatement.setString(3, item.getCognome());
         preparedStatement.setString(4, item.getIndirizzo());
         preparedStatement.setString(5, item.getCap());
         preparedStatement.setString(6, item.getComune());
         preparedStatement.setString(7, item.getProvincia());
         preparedStatement.setString(8, item.getPrezzo());
         preparedStatement.setString(9, item.getProdotti());
         preparedStatement.setString(10, item.getControllato());
         preparedStatement.setString(11, item.getNumeroOrdine());
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

   public boolean confermaOrdine(OrdineBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "UPDATE Ordine SET   controllato =? WHERE numeroOrdine = ? ";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, "true");
         preparedStatement.setString(2, item.getNumeroOrdine());
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

   public boolean doDelete(OrdineBean item) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String updateSQL = "DELETE FROM Ordine WHERE numeroOrdine= ?";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(updateSQL);
         preparedStatement.setString(1, item.getNumeroOrdine());
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

   public Collection<OrdineBean> ritornaTuttiOrdiniUtente(String email) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String selectSQL = "SELECT * FROM Ordine WHERE email= ? ";
      LinkedList products = new LinkedList();

      try {
         connection = this.ds.getConnection();
         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.setString(1, email);
         Utility.print(new String[]{"doRetrieveAll: " + preparedStatement.toString()});
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            OrdineBean bean = new OrdineBean();
            bean.setNumeroOrdine(rs.getString("numeroOrdine"));
            bean.setEmail(rs.getString("email"));
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setCap(rs.getString("cap"));
            bean.setComune(rs.getString("comune"));
            bean.setProvincia(rs.getString("provincia"));
            bean.setPrezzo(rs.getString("prezzo"));
            bean.setProdotti(rs.getString("prodotti"));
            bean.setControllato(rs.getString("controllato"));
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
}