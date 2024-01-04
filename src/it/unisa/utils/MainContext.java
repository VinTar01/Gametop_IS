    package it.unisa.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class MainContext implements ServletContextListener {
   public void contextInitialized(ServletContextEvent sce) {
      Utility.print(new String[]{"Startup web application"});
      ServletContext context = sce.getServletContext();
      DataSource ds = null;

      try {
         Context initCtx = new InitialContext();
         Context envCtx = (Context)initCtx.lookup("java:comp/env");
         ds = (DataSource)envCtx.lookup("jdbc/GametopDB");

         try {
            Connection con = null;

            try {
               con = ds.getConnection();
               DatabaseMetaData metaData = con.getMetaData();
               Utility.print(new String[]{"DBMS name " + metaData.getDatabaseProductName()});
               Utility.print(new String[]{"DBMS version " + metaData.getDatabaseProductVersion()});
            } finally {
               if (con != null) {
                  con.close();
               }

            }
         } catch (SQLException var13) {
            Utility.print(var13);
         }
      } catch (NamingException var14) {
         Utility.print(var14);
      }

      context.setAttribute("DataSource", ds);
      Utility.print(new String[]{"DataSource creation: " + ds.toString()});
   }

   public void contextDestroyed(ServletContextEvent sce) {
      ServletContext context = sce.getServletContext();
      context.removeAttribute("DataSource");
      Utility.print(new String[]{"Shutdown web application"});
   }
}