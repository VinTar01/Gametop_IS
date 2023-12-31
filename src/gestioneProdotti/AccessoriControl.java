   package gestioneProdotti;

import it.unisa.utils.Utility;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet({"/AccessoriControl"})
public class AccessoriControl extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
      ShopModelDS model = new ShopModelDS(ds);

      try {
         request.setAttribute("Accessori", model.doRetrieveAllAccessori(""));
      } catch (SQLException var6) {
         Utility.print(var6);
         request.setAttribute("error", var6.getMessage());
      }

      this.getServletContext().getRequestDispatcher("/accessori.jsp").forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}