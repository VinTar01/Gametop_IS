    package gestioneAccount;

import gestioneAcquisti.GestioneOrdiniModelDS;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet({"/AccountControl"})
public class AccountControl extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
      GestioneOrdiniModelDS model2 = new GestioneOrdiniModelDS(ds);
      String email = (String)request.getSession().getAttribute("email");
      if (email != null) {
         try {
            request.removeAttribute("products");
            request.setAttribute("products", model2.ritornaTuttiOrdiniUtente(email));
         } catch (SQLException var7) {
            var7.printStackTrace();
         }
      }

      System.out.println("ci sei quasi");
      this.getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
