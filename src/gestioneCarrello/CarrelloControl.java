    package gestioneCarrello;

import gestioneAcquisti.OrdineBean;
import gestioneAcquisti.OrdineModelDS;
import gestioneProdotti.ShopBean;
import gestioneProdotti.ShopModelDS;
import it.unisa.utils.Utility;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet({"/CarrelloControl"})
public class CarrelloControl extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
      ShopModelDS model = new ShopModelDS(ds);
      GestioneCarrelloModelDS model2 = new GestioneCarrelloModelDS(ds);
      OrdineModelDS modelPagamento = new OrdineModelDS(ds);
      HttpSession session = request.getSession();
      String s = (String)session.getAttribute("nome");
      session.setAttribute("nome", s);
      String email = (String)session.getAttribute("email");
      Carts<ShopBean> cart = (Carts)request.getSession().getAttribute("carrello");
      if (cart == null) {
         cart = new Carts();
         request.getSession().setAttribute("carrello", cart);
      }

      String sort = request.getParameter("sort");
      String action = request.getParameter("action");
      String azione = request.getParameter("azione");
      List prodcart;
      SessionCarrelloBean addCart;
      ShopBean prod;
      Iterator var17;
      if (request.getSession().getAttribute("email") != null) {
         try {
            if (session.getAttribute("Cliente").equals("1")) {
               session.setAttribute("Cliente", "0");
               prodcart = cart.getItems();
               addCart = new SessionCarrelloBean();
               if (prodcart.size() > 0) {
                  var17 = prodcart.iterator();

                  while(var17.hasNext()) {
                     prod = (ShopBean)var17.next();
                     addCart.setIdemail(email);
                     addCart.setCodiceProdotto(prod.getCodiceProdotto());
                     model2.doSave(addCart);
                  }
               }
            }

            cart = model2.TrovaCarrello(email);
            System.out.println("trova carrello eseguita");
         } catch (SQLException var34) {
            var34.printStackTrace();
         }
      }

      try {
         String str;
         //String str;
         String idProdotto;
         if (azione != null && azione.equals("pagamentoCart")) {
            prodcart = cart.getItems();
            request.getSession().setAttribute("pagamento", azione);

            try {
               addCart = new SessionCarrelloBean();
               if (prodcart.size() > 0) {
                  var17 = prodcart.iterator();

                  while(var17.hasNext()) {
                     prod = (ShopBean)var17.next();
                     addCart.setIdemail(email);
                     addCart.setCodiceProdotto(prod.getCodiceProdotto());
                     model2.doSave2(addCart);
                  }

                  OrdineBean b = new OrdineBean();
                  int prezzo = (Integer)request.getSession().getAttribute("prezzo");
                  str = (String)session.getAttribute("nomeS");
                  str = (String)session.getAttribute("cognomeS");
                  idProdotto = (String)session.getAttribute("indirizzoS");
                  String capS = (String)session.getAttribute("capS");
                  String comuneS = (String)session.getAttribute("comuneS");
                  String provinciaS = (String)session.getAttribute("provinciaS");
                  System.out.println(" param " + prezzo);
                  System.out.println(" param " + str);
                  System.out.println(" param " + str);
                  System.out.println(" param " + idProdotto);
                  System.out.println(" param " + capS);
                  System.out.println(" param " + comuneS);
                  System.out.println(" param " + provinciaS);
                  b.setNome(str);
                  b.setCap(capS);
                  b.setComune(comuneS);
                  b.setCognome(str);
                  b.setIndirizzo(idProdotto);
                  b.setPrezzo(String.valueOf(prezzo));
                  b.setProvincia(provinciaS);
                  b.setEmail(email);
                  b.setControllato("false");
                  String prodotti = "";

                  //ShopBean prod;
                  for(Iterator var26 = prodcart.iterator(); var26.hasNext(); prodotti = prodotti + prod.getCodiceProdotto()) {
                     prod = (ShopBean)var26.next();
                  }

                  System.out.println("Tutti i codici dei prodotti" + prodotti);
                  b.setProdotti(prodotti);
                  modelPagamento.SalvaOrdine(b);
                  b.toString();
               }
            } catch (SQLException var32) {
               var32.printStackTrace();
            }
         }

         if (action != null) {
            if (action.equals("clearCart")) {
               cart.deleteItems();
               request.setAttribute("message", "Cart cleaned");

               try {
                  model2.deleteAll(email);
               } catch (SQLException var31) {
                  this.getServletContext().getRequestDispatcher("/logout.jsp").forward(request, response);
                  var31.printStackTrace();
               }
            } else if (action.equals("UpdateCart")) {
               cart.deleteItems();
               request.setAttribute("message", "Cart cleaned");

               try {
                  model2.deleteAll(email);
               } catch (SQLException var30) {
                  this.getServletContext().getRequestDispatcher("/logout.jsp").forward(request, response);
                  var30.printStackTrace();
               }
            } else {
               String id;
               ShopBean bean;
               if (action.equals("addCart")) {
                  request.getSession().setAttribute("acc", (Object)null);
                  id = request.getParameter("id");
                  bean = model.doRetrieveByKey(id);
                  session.setAttribute("id", id);
                  int c3 = bean.getQuantitaProdotto();
                  if (bean != null && !bean.isEmpty()) {
                     if (c3 == 0) {
                        System.out.println("finito2");
                     }

                     cart.addItem(bean);
                     String cont = (String)session.getAttribute("conto");
                     Integer c = Integer.valueOf(cont);
                     c = c + 1;
                     str = String.valueOf(c);
                     session.setAttribute("conto", str);
                     request.setAttribute("message", "Product " + bean.getTitolo() + " added to cart");
                     idProdotto = (String)session.getAttribute("id");
                     //SessionCarrelloBean
                      addCart = new SessionCarrelloBean();
                 
                     addCart.setIdemail(email);
                     addCart.setCodiceProdotto(idProdotto);

                     try {
                        model2.doSave(addCart);
                     } catch (SQLException var29) {
                        var29.printStackTrace();
                     }
                  }
               } else if (action.equals("deleteCart")) {
                  id = request.getParameter("id");
                  bean = model.doRetrieveByKey(id);
                  if (bean != null && !bean.isEmpty()) {
                     cart.deleteItem(bean);
                     String cont = (String)session.getAttribute("conto");
                     Integer c = Integer.valueOf(cont);
                     c = c - 1;
                     str = String.valueOf(c);
                     session.setAttribute("conto", str);
                     //SessionCarrelloBean
                      addCart = new SessionCarrelloBean();
                     
                     addCart.setIdemail(email);
                     addCart.setCodiceProdotto(id);

                     try {
                        model2.doDelete(addCart);
                     } catch (SQLException var28) {
                        this.getServletContext().getRequestDispatcher("/logout.jsp").forward(request, response);
                        var28.printStackTrace();
                     }

                     request.setAttribute("message", "Product " + bean.getTitolo() + " Deleted from cart");
                  }
               }
            }
         }
      } catch (Exception var33) {
         Utility.print(var33);
         request.setAttribute("error", var33.getMessage());
      }

      request.setAttribute("cart", cart);

      try {
         request.removeAttribute("products");
         request.setAttribute("products", model.doRetrieveAll(sort));
      } catch (SQLException var27) {
         Utility.print(var27);
         request.setAttribute("error", var27.getMessage());
      }

      response.setContentType("text/html");
      this.getServletContext().getRequestDispatcher("/carrello.jsp").forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
} 
