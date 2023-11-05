package gestioneAccount;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import it.unisa.utils.Utility;
import java.util.LinkedList;
import java.util.Collection;
import javax.sql.DataSource;
import gestioneProdotti.ShopModel;

public class RegistrazioneModelDS implements ShopModel<UtenteBean>
{
    private DataSource ds;
    
    public RegistrazioneModelDS(final DataSource ds) {
        this.ds = null;
        this.ds = ds;
    }
    
    //restituisce tutte le righe della tabella Cliente e crea un oggetto UtenteBean per ognuno inserendoli in una lista
    public Collection<UtenteBean> doRetrieveAll(final String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM Cliente";
        if (order != null && !order.equals("")) {
            selectSQL = String.valueOf(selectSQL) + " ORDER BY " + order;
        }
        final Collection<UtenteBean> users = new LinkedList<UtenteBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            Utility.print("doRetrieveAll: " + preparedStatement.toString());
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final UtenteBean bean = new UtenteBean();
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setEmail(rs.getString("email"));
                bean.setIndirizzo(rs.getString("indirizzo"));
                bean.setPassword(rs.getString("password"));
                users.add(bean);
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
        return users;
    }
    
    //ricerca una riga nella tabella Cliente che ha email uguale a quella passata e crea un UtenteBean con gli attributi della riga
    public UtenteBean doRetrieveByKey(final String code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final UtenteBean bean = new UtenteBean();
        final String selectSQL = "SELECT * FROM Cliente WHERE email = ?";
        connection = this.ds.getConnection();
        System.out.println("connection" + connection.toString());
        preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, code);
        Utility.print("doRetrieveByKey: " + preparedStatement.toString());
        final ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setPassword(rs.getString("password"));
        }
        Utility.print(bean.toString());
        return bean;
    }
    
    //ricerca una riga nella tabella Cliente che ha email e pswd uguale a quella passata e crea un UtenteBean con gli attributi della riga
    public UtenteBean doRetrieveByKey(final String email, final String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final UtenteBean bean = new UtenteBean();
        final String selectSQL = "SELECT * FROM Cliente WHERE email = ? and password = ?";
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(1, password);
            Utility.print("doRetrieveByKey: " + preparedStatement.toString());
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setEmail(rs.getString("email"));
                bean.setIndirizzo(rs.getString("indirizzo"));
                bean.setPassword(rs.getString("password"));
            }
            Utility.print(bean.toString());
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
    
    //inserisci nella tabella Clienti una riga con gli attributi dell'UtenteBean passato
    public void doSave(final UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String insertSQL = "INSERT INTO Cliente  (nome,cognome,email,indirizzo,password) VALUES (?,?,?,?,?)";
        connection = this.ds.getConnection();
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setString(1, item.getNome());
        preparedStatement.setString(2, item.getCognome());
        preparedStatement.setString(3, item.getEmail());
        preparedStatement.setString(4, item.getIndirizzo());
        preparedStatement.setString(5, item.getPassword());
        Utility.print("doSave: " + preparedStatement.toString());
        preparedStatement.executeUpdate();
        connection.commit();
        System.out.println("ds" + this.ds.toString());
    }
    
    //aggiorna una riga della tabella Cliente con i parametri del bean passato in base all'email
    public boolean doUpdate(final UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "UPDATE Cliente SET nome = ?, cognome = ? , email = ? , indirizzo = ?, password = ? WHERE email = ? ";
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
            Utility.print("doUpdate: " + preparedStatement.toString());
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
    
    //elimina una riga in base all'email del bean passato
    public boolean doDelete(final UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "DELETE FROM Cliente WHERE email = ?";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item.getEmail());
            Utility.print("doDelete: " + preparedStatement.toString());
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
    
    //aggiorna la riga del cliente con email uguale a quello del Bean passato aggiornando solo la password settando la stessa di quella del bean 
    public boolean doUpdatePassword(final UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "UPDATE Cliente SET  password = ? WHERE email = ? ";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item.getPassword());
            preparedStatement.setString(2, item.getEmail());
            Utility.print("doUpdate: " + preparedStatement.toString());
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