    package gestioneAcquisti;

import it.unisa.utils.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

public class OrdineModelDS {
   private DataSource ds = null;

   public OrdineModelDS(DataSource ds) {
      this.ds = ds;
   }

   public void SalvaOrdine(OrdineBean o) throws SQLException {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      String insertSQL = "INSERT INTO Ordine  (email,nome,cognome,indirizzo,cap,comune,provincia,prezzo,prodotti,controllato) VALUES (?,?,?,?,?,?,?,?,?,?)";

      try {
         connection = this.ds.getConnection();
         connection.setAutoCommit(false);
         preparedStatement = connection.prepareStatement(insertSQL);
         preparedStatement.setString(1, o.getEmail());
         preparedStatement.setString(2, o.getNome());
         preparedStatement.setString(3, o.getCognome());
         preparedStatement.setString(4, o.getIndirizzo());
         preparedStatement.setString(5, o.getCap());
         preparedStatement.setString(6, o.getComune());
         preparedStatement.setString(7, o.getProvincia());
         preparedStatement.setString(8, o.getPrezzo());
         preparedStatement.setString(9, o.getProdotti());
         preparedStatement.setString(10, o.getControllato());
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
}