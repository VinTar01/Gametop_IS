package gestioneProdotti;

import java.util.Collection;
import java.sql.SQLException;

//interfaccia che definisce le operazioni sulle tabelle
public interface ShopModel<T>
{
    T doRetrieveByKey(final String p0) throws SQLException;
    
    Collection<T> doRetrieveAll(final String p0) throws SQLException;
    
    void doSave(final T p0) throws SQLException;
    
    boolean doUpdate(final T p0) throws SQLException;
    
    boolean doDelete(final T p0) throws SQLException;
}