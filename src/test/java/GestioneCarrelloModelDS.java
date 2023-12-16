package test.java;

import java.util.LinkedList;
import java.util.Collection;
import gestioneProdotti.ShopBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import it.unisa.utils.Utility;
import javax.sql.DataSource;
import gestioneCarrello.*;

public class GestioneCarrelloModelDS
{
    private DataSource ds;
    
    public GestioneCarrelloModelDS(final DataSource ds) {
        this.ds = null;
        this.ds = ds;
    }
    
    //inserisce una riga in SessionCarrello che ha gli attributi del parametro passato
    public void doSave(final SessionCarrelloBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String insertSQL = "INSERT INTO Carrello   (idemail,codiceProdotto) VALUES (?,?)";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, item.getIdemail());
            preparedStatement.setString(2, item.getCodiceProdotto());
            Utility.print(new String[] { "doSave: " + preparedStatement.toString() });
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        if (connection != null) {
            connection.close();
        }
    }
    
    //inserisce una riga in ordini che ha che ha idemail e codiceProdotto uguali a quelli del bean passato
    public void doSave2(final SessionCarrelloBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String insertSQL = "INSERT INTO Storico   (idemail,codiceProdotto) VALUES (?,?)";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, item.getIdemail());
            preparedStatement.setString(2, item.getCodiceProdotto());
            Utility.print(new String[] { "doSave: " + preparedStatement.toString() });
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        if (connection != null) {
            connection.close();
        }
    }
    
    
    
    //cancella una riga di carrello in base all'email ed al codice prodotto del bean passato
    public boolean doDelete(final SessionCarrelloBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "DELETE FROM carrello WHERE idemail=? and codiceProdotto = ? ";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item.getIdemail());
            preparedStatement.setString(2, item.getCodiceProdotto());
            Utility.print(new String[] { "doDelete: " + preparedStatement.toString() });
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        if (connection != null) {
            connection.close();
        }
        return false;
    }
    
    //cancella una riga di ordini in base all'email ed al codice prodotto del bean passato
    public boolean doDelete2(final SessionCarrelloBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "DELETE FROM Storico WHERE idemail=? and codiceProdotto = ? ";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item.getIdemail());
            preparedStatement.setString(2, item.getCodiceProdotto());
            Utility.print(new String[] { "doDelete: " + preparedStatement.toString() });
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        if (connection != null) {
            connection.close();
        }
        return false;
    }
    
    //ritorna un oggetto Carts 
    //Carts rappresenta un carrello con tutti i suoi prodotti
    //questo metodo permette di trovare uno specifico carrello di un email passata e tutti i prodotti di questo carrello
    public Carts<ShopBean> TrovaCarrello(final String email) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String selectSQL = "SELECT prodotto.codiceProdotto , titolo, descrizione, prezzo , copertina FROM carrello , prodotto , cliente where carrello.idemail = ? && carrello.idemail = cliente.email && carrello.codiceProdotto=prodotto.codiceProdotto;";
        final Carts<ShopBean> products = new Carts<ShopBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            Utility.print(new String[] { "doRetrieveAll: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final ShopBean bean = new ShopBean();
                bean.setCodiceProdotto(rs.getString("codiceProdotto"));
                final String s = rs.getString("codiceProdotto");
                System.out.println(s);
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getInt("prezzo"));
                bean.setCopertina(rs.getString("copertina"));
                products.addItem(bean);
            }
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        if (connection != null) {
            connection.close();
        }
        return products;
    }
    
    //invece di ritornare un ooggetto carrello (Carts) ritorna una collezione di prodotti (ShopBean)
    public Collection<ShopBean> TrovaCarrello2(final String email) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //forse ORDINE
        final String selectSQL = "SELECT prodotto.codiceProdotto , titolo, descrizione, prezzo , copertina FROM storico , prodotto , cliente where ordini.idemail = ? && ordini.idemail = cliente.email && ordini.codiceProdotto=prodotto.codiceProdotto;";
        final Collection<ShopBean> products = new LinkedList<ShopBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            Utility.print(new String[] { "doRetrieveAll: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final ShopBean bean = new ShopBean();
                bean.setCodiceProdotto(rs.getString("codiceProdotto"));
                final String s = rs.getString("codiceProdotto");
                System.out.println(s);
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getInt("prezzo"));
                bean.setCopertina(rs.getString("copertina"));
                products.add(bean);
            }
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        if (connection != null) {
            connection.close();
        }
        return products;
    }
    
    //elimina tutte le righe da carrello in base all'email passata
    //tutti gli elem del carrello fatti dall email item
    public boolean deleteAll(final String item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "DELETE FROM carrello WHERE idemail= ?";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item);
            Utility.print(new String[] { "doDelete: " + preparedStatement.toString() });
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
        if (connection != null) {
            connection.close();
        }
        return false;
    }
}