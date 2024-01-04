    package gestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;

public interface ShopModel<T> {
   T doRetrieveByKey(String var1) throws SQLException;

   Collection<T> doRetrieveAll(String var1) throws SQLException;

   void doSave(T var1) throws SQLException;

   boolean doUpdate(T var1) throws SQLException;

   boolean doDelete(T var1) throws SQLException;
}