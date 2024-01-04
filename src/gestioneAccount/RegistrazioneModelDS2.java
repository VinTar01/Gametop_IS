package gestioneAccount;
import gestioneProdotti.ShopModel;
import it.unisa.utils.Utility;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

public class RegistrazioneModelDS2 implements ShopModel<UtenteBean> {
    private DataSource ds = null;

    public RegistrazioneModelDS2(DataSource ds) {
        this.ds = ds;
    }

    public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(buildSelectAllQuery(order));
            Utility.print(new String[]{"doRetrieveAll: " + preparedStatement.toString()});
            rs = preparedStatement.executeQuery();

            return processResultSet(rs);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
    }

    public UtenteBean doRetrieveByKey(String code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = this.ds.getConnection();
            preparedStatement = connection.prepareStatement(buildSelectByKeyQuery());
            preparedStatement.setString(1, code);
            Utility.print(new String[]{"doRetrieveByKey: " + preparedStatement.toString()});
            rs = preparedStatement.executeQuery();

            return processSingleResultSet(rs);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
    }

    public void doSave(UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(buildInsertQuery());
            prepareStatementForInsert(preparedStatement, item);
            Utility.print(new String[]{"doSave: " + preparedStatement.toString()});
            preparedStatement.executeUpdate();
            connection.commit();
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public boolean doUpdate(UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(buildUpdateQuery());
            prepareStatementForUpdate(preparedStatement, item);
            Utility.print(new String[]{"doUpdate: " + preparedStatement.toString()});
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public boolean doDelete(UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(buildDeleteQuery());
            preparedStatement.setString(1, item.getEmail());
            Utility.print(new String[]{"doDelete: " + preparedStatement.toString()});
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    public boolean doUpdatePassword(UtenteBean item) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(buildUpdatePasswordQuery());
            prepareStatementForUpdatePassword(preparedStatement, item);
            Utility.print(new String[]{"doUpdate: " + preparedStatement.toString()});
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

    private void closeResources(ResultSet rs, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodi ausiliari per costruzione query, elaborazione ResultSet e preparazione dichiarazioni...
    private String buildSelectAllQuery(String order) {
        String selectSQL = "SELECT * FROM Cliente";
        if (order != null && !order.equals("")) {
            selectSQL = selectSQL + " ORDER BY " + order;
        }
        return selectSQL;
    }

    private String buildSelectByKeyQuery() {
        return "SELECT * FROM Cliente WHERE email = ?";
    }

    private String buildInsertQuery() {
        return "INSERT INTO Cliente  (nome,cognome,email,indirizzo,password) VALUES (?,?,?,?,?)";
    }

    private String buildUpdateQuery() {
        return "UPDATE Cliente SET nome = ?, cognome = ? , email = ? , indirizzo = ?, password = ? WHERE email = ? ";
    }

    private String buildDeleteQuery() {
        return "DELETE FROM Cliente WHERE email = ?";
    }

    private String buildUpdatePasswordQuery() {
        return "UPDATE Cliente SET  password = ? WHERE email = ? ";
    }

    private Collection<UtenteBean> processResultSet(ResultSet rs) throws SQLException {
        LinkedList products = new LinkedList();
        while (rs.next()) {
            UtenteBean bean = new UtenteBean();
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setPassword(rs.getString("password"));
            products.add(bean);
        }
        return products;
    }

    private UtenteBean processSingleResultSet(ResultSet rs) throws SQLException {
        UtenteBean bean = new UtenteBean();
        while (rs.next()) {
            bean.setNome(rs.getString("nome"));
            bean.setCognome(rs.getString("cognome"));
            bean.setEmail(rs.getString("email"));
            bean.setIndirizzo(rs.getString("indirizzo"));
            bean.setPassword(rs.getString("password"));
        }
        return bean;
    }

    private void prepareStatementForInsert(PreparedStatement preparedStatement, UtenteBean item) throws SQLException {
        preparedStatement.setString(1, item.getNome());
        preparedStatement.setString(2, item.getCognome());
        preparedStatement.setString(3, item.getEmail());
        preparedStatement.setString(4, item.getIndirizzo());
        preparedStatement.setString(5, item.getPassword());
    }

    private void prepareStatementForUpdate(PreparedStatement preparedStatement, UtenteBean item) throws SQLException {
        preparedStatement.setString(1, item.getNome());
        preparedStatement.setString(2, item.getCognome());
        preparedStatement.setString(3, item.getEmail());
        preparedStatement.setString(4, item.getIndirizzo());
        preparedStatement.setString(5, item.getPassword());
        preparedStatement.setString(6, item.getEmail());
    }

    private void prepareStatementForUpdatePassword(PreparedStatement preparedStatement, UtenteBean item) throws SQLException {
        preparedStatement.setString(1, item.getPassword());
        preparedStatement.setString(2, item.getEmail());
    }
}

