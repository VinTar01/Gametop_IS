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

@WebServlet({"/AdminProdotti"})
public class GestoreProdottiControl extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
      ShopModelDS model = new ShopModelDS(ds);
      String sort = request.getParameter("sort");
      String action = request.getParameter("action");

      try {
         if (action != null) {
            String codiceProdotto;
            if (action.equals("details")) {
            codiceProdotto = request.getParameter("id");
               request.removeAttribute("product");
               request.setAttribute("product", model.doRetrieveByKey(codiceProdotto));
            } else if (action.equals("delete")) {
               codiceProdotto = request.getParameter("id");
               ShopBean bean = model.doRetrieveByKey(codiceProdotto);
               if (bean != null && !bean.isEmpty()) {
                  model.doDelete(bean);
                  request.setAttribute("message", "Product " + bean.getTitolo() + " deleted");
               }
            } else {
               String titolo;
               String descrizione;
               String copertina;
               int prezzo;
               int quantitaProdotto;
               ShopBean bean;
               String idCategoria;
               if (action.equals("update")) {
            	  codiceProdotto = request.getParameter("id");
                  idCategoria = request.getParameter("idcategoria");
                  titolo = request.getParameter("titolo");
                  descrizione = request.getParameter("descrizione");
                  copertina = request.getParameter("copertina");
                  prezzo = Integer.parseInt(request.getParameter("prezzo"));
                  quantitaProdotto = Integer.parseInt(request.getParameter("quantitaProdotto"));
                  bean = new ShopBean();
                  bean.setCodiceProdotto(codiceProdotto);
                  bean.setIdCategoria(idCategoria);
                  bean.setQuantitaProdotto(quantitaProdotto);
                  bean.setTitolo(titolo);
                  bean.setDescrizione(descrizione);
                  bean.setPrezzo(prezzo);
                  bean.setCopertina(copertina);
                  model.doUpdate(bean);
                  request.setAttribute("message", "Product " + bean.getTitolo() + " updated");
               } else if (action.equals("insert")) {
            	   codiceProdotto = request.getParameter("codiceProdotto");
                  idCategoria = request.getParameter("idcategoria");
                  titolo = request.getParameter("titolo");
                  descrizione = request.getParameter("descrizione");
                  copertina = request.getParameter("copertina");
                  prezzo = Integer.parseInt(request.getParameter("prezzo"));
                  quantitaProdotto = Integer.parseInt(request.getParameter("quantitaProdotto"));
                  bean = new ShopBean();
                  bean.setCodiceProdotto(codiceProdotto);
                  bean.setIdCategoria(idCategoria);
                  bean.setQuantitaProdotto(quantitaProdotto);
                  bean.setTitolo(titolo);
                  bean.setDescrizione(descrizione);
                  bean.setPrezzo(prezzo);
                  bean.setCopertina(copertina);
                  model.doSave(bean);
                  request.setAttribute("message", "Product " + bean.getTitolo() + " added");
               }
            }
         }
      } catch (Exception var16) {
         Utility.print(var16);
         request.setAttribute("error", var16.getMessage());
      }

      try {
         request.removeAttribute("products");
         request.setAttribute("products", model.doRetrieveAll(sort));
      } catch (SQLException var15) {
         Utility.print(var15);
         request.setAttribute("error", var15.getMessage());
      }

      this.getServletContext().getRequestDispatcher("/adminProdotti.jsp").forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}