package test.java;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import it.unisa.utils.Utility;
import java.util.LinkedList;
import java.util.Collection;
import javax.sql.DataSource;
import gestioneProdotti.*;

public class ShopModelDS implements ShopModel<ShopBean>
{
    private DataSource ds;
    
    public ShopModelDS(final DataSource ds) {
        this.ds = null;
        this.ds = ds;
    }
    
    //ottengo una collezione di prodotto, tutti quelli nella tabella
    public Collection<ShopBean> doRetrieveAll(final String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM Prodotto";
        if (order != null && !order.equals("")) {
            selectSQL = String.valueOf(selectSQL) + " ORDER BY " + order;
        }
        final Collection<ShopBean> products = new LinkedList<ShopBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            Utility.print(new String[] { "doRetrieveAll: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final ShopBean bean = new ShopBean();
                bean.setCodiceProdotto(rs.getString("codiceProdotto"));
                bean.setIdCategoria(rs.getString("idCategoria"));
                bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
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
    
    //cerca un prodotto in base al codice 
    public ShopBean doRetrieveByKey(final String code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final ShopBean bean = new ShopBean();
        final String selectSQL = "SELECT * FROM Prodotto WHERE codiceProdotto= ?";
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, code);
            Utility.print(new String[] { "doRetrieveByKey: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bean.setCodiceProdotto(rs.getString("codiceProdotto"));
                bean.setIdCategoria(rs.getString("idCategoria"));
                bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getInt("prezzo"));
                bean.setCopertina(rs.getString("copertina"));
            }
            Utility.print(new String[] { bean.toString() });
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
        return bean;
    }
    
    public void doSave(final ShopBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String insertSQL = "INSERT INTO Prodotto  (codiceProdotto,idcategoria,quantitaProdotto,titolo,descrizione,prezzo,copertina) VALUES (?,?,?,?,?,?,?)";
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
            }
            else if (item.getIdCategoria().equals("Videogiochi")) {
                preparedStatement.setString(7, "image/Videogiochi/" + item.getCopertina());
            }
            else if (item.getIdCategoria().equals("Accessori")) {
                preparedStatement.setString(7, "image/Accessori/" + item.getCopertina());
            }
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
    
    public boolean doUpdate(final ShopBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "UPDATE Vestito SET idcategoria = ?, quantitaProdotto = ? , titolo = ? ,descrizione = ?,prezzo = ?,copertina = ? WHERE codiceVestito = ? ";
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
            }
            else if (item.getIdCategoria().equals("Videogiochi")) {
                preparedStatement.setString(6, "image/Videogiochi/" + item.getCopertina());
            }
            else if (item.getIdCategoria().equals("Accessori")) {
                preparedStatement.setString(6, "image/Accessori/" + item.getCopertina());
            }
            preparedStatement.setString(7, item.getCodiceProdotto());
            Utility.print(new String[] { "doUpdate: " + preparedStatement.toString() });
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
        return true;
    }
    
    public boolean doDelete(final ShopBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "DELETE FROM Vestito WHERE codiceProdotto = ?";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item.getCodiceProdotto());
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

    public Collection<ShopBean> doRetrieveAllAccessori(final String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM Prodotto where idcategoria = 'Accessori' ";
        if (order != null && !order.equals("")) {
            selectSQL = String.valueOf(selectSQL) + "ORDER BY " + order;
        }
        final Collection<ShopBean> products = new LinkedList<ShopBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            Utility.print(new String[] { "doRetriveAll: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final ShopBean bean = new ShopBean();
                bean.setCodiceProdotto(rs.getString("codiceProdotto"));
                bean.setIdCategoria(rs.getString("idCategoria"));
                bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getInt("prezzo"));
                bean.setCopertina(rs.getString("copertina"));
                products.add(bean);
            }
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        return products;
    }
    
    public Collection<ShopBean> doRetrieveAllConsole(final String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM Prodotto where idcategoria = 'Console' ";
        if (order != null && !order.equals("")) {
            selectSQL = String.valueOf(selectSQL) + "ORDER BY " + order;
        }
        final Collection<ShopBean> products = new LinkedList<ShopBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            Utility.print(new String[] { "doRetriveAll: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final ShopBean bean = new ShopBean();
                bean.setCodiceProdotto(rs.getString("codiceProdotto"));
                bean.setIdCategoria(rs.getString("idCategoria"));
                bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getInt("prezzo"));
                bean.setCopertina(rs.getString("copertina"));
                products.add(bean);
            }
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        return products;
    }

    public Collection<ShopBean> doRetrieveAllVideogiochi(final String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM Prodotto where idcategoria = 'Videogiochi' ";
        if (order != null && !order.equals("")) {
            selectSQL = String.valueOf(selectSQL) + "ORDER BY " + order;
        }
        final Collection<ShopBean> products = new LinkedList<ShopBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            Utility.print(new String[] { "doRetriveAll: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final ShopBean bean = new ShopBean();
                bean.setCodiceProdotto(rs.getString("codiceProdotto"));
                bean.setIdCategoria(rs.getString("idCategoria"));
                bean.setQuantitaProdotto(rs.getInt("quantitaProdotto"));
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getInt("prezzo"));
                bean.setCopertina(rs.getString("copertina"));
                products.add(bean);
            }
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        return products;
    }
    
}