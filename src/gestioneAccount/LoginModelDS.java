package gestioneAccount;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import it.unisa.utils.Utility;
import javax.sql.DataSource;

public class LoginModelDS
{
    private DataSource ds;
    
    public LoginModelDS(final DataSource ds) {
        this.ds = null;
        this.ds = ds;
    }
    
    //Ricerca una riga di Personale tramite la chiave
    public PersonaleBean doRetrieveByKeyPersonale(final String code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final PersonaleBean bean = new PersonaleBean();
        //seleziona tutti gli attributi della tupla che ha email uguale a quella passata
        final String selectSQL = "SELECT * FROM Personale WHERE email= ?";
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, code);

            Utility.print(new String[] { "doRetrieveByKey: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setRuolo(rs.getString("ruolo"));
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
    
    //Ricerca una riga di Cliente tramite la chiave
    public UtenteBean doRetrieveByKey(final String code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final UtenteBean bean = new UtenteBean();
        final String selectSQL = "SELECT * FROM cliente WHERE email= ?";
        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, code);
            Utility.print(new String[] { "doRetrieveByKey: " + preparedStatement.toString() });
            final ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
                bean.setEmail(rs.getString("email"));
                bean.setIndirizzo(rs.getString("indirizzo"));
                bean.setPassword(rs.getString("password"));
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

    private void printSQLException(final SQLException e) {
    }
    
}