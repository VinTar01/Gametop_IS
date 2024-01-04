   package gestioneAcquisti;

import it.unisa.utils.Utility;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet({"/AdminOrdini"})
public class GestoreOrdiniControl extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
      GestioneOrdiniModelDS mO = new GestioneOrdiniModelDS(ds);
      String action = request.getParameter("action");

      try {
         if (action != null) {
            String id;
            if (action.equals("details")) {
               id = request.getParameter("id");
               request.removeAttribute("product");
               request.setAttribute("product", mO.doRetrieveByKey(id));
            } else {
               OrdineBean bean;
               if (action.equals("delete")) {
                  id = request.getParameter("id");
                  bean = mO.doRetrieveByKey(id);
                  if (bean != null) {
                     mO.doDelete(bean);
                     request.setAttribute("message", "Product " + bean.getNumeroOrdine() + " deleted");
                  }
               } else if (action.equals("update")) {
                  id = request.getParameter("id");
                  String numeroOrdine = request.getParameter("NumeroOrdine");
                  OrdineBean o = mO.doRetrieveByKey(id);
                  String email = o.getEmail();
                  String nome = request.getParameter("nome");
                  String cognome = request.getParameter("cognome");
                  String indirizzo = request.getParameter("indirizzo");
                  String cap = request.getParameter("cap");
                  String comune = request.getParameter("comune");
                  String provincia = request.getParameter("provincia");
                  String prezzo = request.getParameter("prezzo");
                  String prodotti = request.getParameter("prodotti");
                  String controllato = request.getParameter("controllato");
                  /*OrdineBean*/ bean = new OrdineBean();
                  bean.setNumeroOrdine(id);
                  bean.setEmail(email);
                  bean.setNome(nome);
                  bean.setCognome(cognome);
                  bean.setIndirizzo(indirizzo);
                  bean.setCap(cap);
                  bean.setComune(comune);
                  bean.setProvincia(provincia);
                  bean.setPrezzo(prezzo);
                  bean.setProdotti(prodotti);
                  bean.setControllato(controllato);
                  mO.doUpdate(bean);
                  request.setAttribute("message", "Product " + bean.getNumeroOrdine() + " updated");
               } else if (action.equals("conferma")) {
                  id = request.getParameter("id");
                  bean = mO.doRetrieveByKey(id);
                  mO.confermaOrdine(bean);
                  request.setAttribute("message", "Numero Ordine " + bean.getNumeroOrdine() + " confermato");
               }
            }
         }
      } catch (Exception var21) {
         Utility.print(var21);
         request.setAttribute("error", var21.getMessage());
      }

      try {
         request.setAttribute("products", mO.ritornaTuttiOrdiniDaControllare());
      } catch (SQLException var20) {
         var20.printStackTrace();
      }

      this.getServletContext().getRequestDispatcher("/adminOrdini.jsp").forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
