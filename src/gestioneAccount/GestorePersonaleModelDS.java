package gestioneAccount;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import it.unisa.utils.Utility;
import java.util.LinkedList;
import java.util.Collection;
import javax.sql.DataSource;

public class GestorePersonaleModelDS
{
    private DataSource ds;
    
    public GestorePersonaleModelDS(final DataSource ds) {
        this.ds = null;
        this.ds = ds;
    }
    
    public Collection<PersonaleBean> stampaTuttoIlPersonale() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String selectSQL = "SELECT * FROM Personale";
        final Collection<PersonaleBean> admins = new LinkedList<PersonaleBean>();
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            Utility.print("doRetrieveAll: " + preparedStatement.toString());
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                final PersonaleBean bean = new PersonaleBean();
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setRuolo(rs.getString("ruolo"));
                admins.add(bean);
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
        return admins;
    }
    
    public PersonaleBean doRetrieveByKey(final String code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final PersonaleBean bean = new PersonaleBean();
        final String selectSQL = "SELECT * FROM Personale WHERE email = ?";
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, code);
            Utility.print("doRetrieveByKey: " + preparedStatement.toString());
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setRuolo(rs.getString("ruolo"));
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
    
    public void inserisciPersonale(final PersonaleBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String insertSQL = "INSERT INTO Personale  (nome,cognome,email,password,ruolo) VALUES (?,?,?,?,?)";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, item.getNome());
            preparedStatement.setString(2, item.getCognome());
            preparedStatement.setString(3, item.getEmail());
            preparedStatement.setString(4, item.getPassword());
            preparedStatement.setString(5, item.getRuolo());
            Utility.print("doSave: " + preparedStatement.toString());
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
    
    public boolean doUpdate(final PersonaleBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "UPDATE Personale SET nome = ?, cognome = ? , email = ? ,password = ?  ruolo = ? WHERE email = ? ";
        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, item.getNome());
            preparedStatement.setString(2, item.getCognome());
            preparedStatement.setString(3, item.getEmail());
            preparedStatement.setString(4, item.getPassword());
            preparedStatement.setString(5, item.getRuolo());
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
    
    public boolean doDelete(final PersonaleBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String updateSQL = "DELETE FROM Personale WHERE email = ?";
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
}  