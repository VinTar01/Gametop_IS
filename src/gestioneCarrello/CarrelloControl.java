package gestioneCarrello;

import java.io.IOException;
import javax.servlet.ServletException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import it.unisa.utils.Utility;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import gestioneAcquisti.OrdineBean;
import java.sql.SQLException;
import gestioneProdotti.ShopBean;
import gestioneAcquisti.OrdineModelDS;
import gestioneProdotti.ShopModelDS;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/CarrelloControl" })
public class CarrelloControl extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        //crea uno shopModel per la gestione dei prodotti
        final ShopModelDS model = new ShopModelDS(ds);
        //crea un GestioneCarrelloModel perla gestione del carrello
        final GestioneCarrelloModelDS model2 = new GestioneCarrelloModelDS(ds);
        //crea un OrdineModel per la gestione del pagamento
        final OrdineModelDS modelPagamento = new OrdineModelDS(ds);
        final HttpSession session = request.getSession();
        final String s = (String)session.getAttribute("nome");
        session.setAttribute("nome", (Object)s);
        final String email = (String)session.getAttribute("email");
        Carts<ShopBean> cart = (Carts<ShopBean>)request.getSession().getAttribute("carrello");
        if (cart == null) {
            cart = new Carts<ShopBean>();
            request.getSession().setAttribute("carrello", (Object)cart);
        }
        final String sort = request.getParameter("sort");
        final String action = request.getParameter("action");
        final String azione = request.getParameter("azione");
        if (request.getSession().getAttribute("email") != null) {
            try {
                if (session.getAttribute("Cliente").equals("1")) {
                    session.setAttribute("Cliente", (Object)"0");
                    final List<ShopBean> prodcart2 = (List<ShopBean>)cart.getItems();
                    final SessionCarrelloBean addCart2 = new SessionCarrelloBean();
                    if (prodcart2.size() > 0) {
                        //salva nel db ad ogni iterazione il singolo elemento del carrello (coppia email, codiceProdotoo)
                        for (final ShopBean prod : prodcart2) {
                            addCart2.setIdemail(email);
                            addCart2.setCodiceProdotto(prod.getCodiceProdotto());
                            model2.doSave(addCart2);
                        }
                    }
                }
                cart = (Carts<ShopBean>)model2.TrovaCarrello(email);
                System.out.println("trova carrello eseguita");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //quando azione è pagamentoCart, salva gli elementi del carrello nella tabella Storico
        try {
            if (azione != null && azione.equals("pagamentoCart")) {
                final List<ShopBean> prodcart3 = (List<ShopBean>)cart.getItems();
                request.getSession().setAttribute("pagamento", (Object)azione);
                try {
                    final SessionCarrelloBean addCart3 = new SessionCarrelloBean();
                    if (prodcart3.size() > 0) {
                        for (final ShopBean prod : prodcart3) {
                            addCart3.setIdemail(email);
                            addCart3.setCodiceProdotto(prod.getCodiceProdotto());
                            model2.doSave2(addCart3);
                        }
                        final OrdineBean b = new OrdineBean();
                        final int prezzo = (int)request.getSession().getAttribute("prezzo");
                        final String nomeS = (String)session.getAttribute("nomeS");
                        final String cognomeS = (String)session.getAttribute("cognomeS");
                        final String indirizzoS = (String)session.getAttribute("indirizzoS");
                        final String capS = (String)session.getAttribute("capS");
                        final String comuneS = (String)session.getAttribute("comuneS");
                        final String provinciaS = (String)session.getAttribute("provinciaS");
                        System.out.println(" param " + prezzo);
                        System.out.println(" param " + nomeS);
                        System.out.println(" param " + cognomeS);
                        System.out.println(" param " + indirizzoS);
                        System.out.println(" param " + capS);
                        System.out.println(" param " + comuneS);
                        System.out.println(" param " + provinciaS);
                        b.setNome(nomeS);
                        b.setCap(capS);
                        b.setComune(comuneS);
                        b.setCognome(cognomeS);
                        b.setIndirizzo(indirizzoS);
                        b.setPrezzo(new StringBuilder(String.valueOf(prezzo)).toString());
                        b.setProvincia(provinciaS);
                        b.setEmail(email);
                        b.setControllato("false");
                        String prodotti = "";
                        for (final ShopBean prod2 : prodcart3) {
                            prodotti = String.valueOf(prodotti) + prod2.getCodiceProdotto();
                        }
                        System.out.println("Tutti i codici dei prodotti" + prodotti);
                        //setto al bean ordine la stringa con i codici di tutti i prodotti dell'ordine pagato
                        b.setProdotti(prodotti);
                        modelPagamento.SalvaOrdine(b);
                        b.toString();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (action != null) {
                if (action.equals("clearCart")) {
                    cart.deleteItems();
                    request.setAttribute("message", (Object)"Cart cleaned");
                    try {
                        //elimina tutte i prodotti nella tabella Carrello in base all'email associata al carrello che è statpo svuotato
                        model2.deleteAll(email);
                    }
                    catch (SQLException e) {
                        this.getServletContext().getRequestDispatcher("/logout.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        e.printStackTrace();
                    }
                }
                //forse da togliere
                else if (action.equals("UpdateCart")) {
                    cart.deleteItems(); //cart.getItems()
                    request.setAttribute("message", (Object)"Cart cleaned");
                    try {
                        model2.deleteAll(email);  //
                    }
                    catch (SQLException e) {
                        this.getServletContext().getRequestDispatcher("/logout.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        e.printStackTrace();
                    }
                }
                else if (action.equals("addCart")) {
                    request.getSession().setAttribute("acc", (Object)null);
                    //ottengo id del prodotto da aggiungere
                    final String id = request.getParameter("id");
                    //salvo in un bean il prodotto cercandolo per id nel db
                    final ShopBean bean = model.doRetrieveByKey(id);
                    session.setAttribute("id", (Object)id);
                    //ottengo la quantità del prodotto da aggiungere
                    final int c3 = bean.getQuantitaProdotto();
                    if (bean != null && !bean.isEmpty()) {
                        //se quantità è 0, scorte finite
                        if (c3 == 0) {
                            System.out.println("finito2");
                        }
                        //aggiungo il prodotto al carrello
                        cart.addItem(bean);
                        //prendo dalla session l'attributo conto che rappresenta il numero di prod nel carrello
                        final String cont = (String)session.getAttribute("conto");
                        Integer c4 = Integer.valueOf(cont);
                        //lo incremento
                        ++c4; 
                        final String str = String.valueOf(c4);
                        //lo riconverto in stringa e lo rimetto come attributi della session
                        session.setAttribute("conto", (Object)str);
                        request.setAttribute("message", (Object)("Product " + bean.getTitolo() + " added to cart"));
                        //ottiene dalla sessione l'id del prodotto aggiunto
                        final String idProdotto = (String)session.getAttribute("id");
                        //salva in un sessioncarrelloBean email dell'utente ed id del prodotto aggiunto
                        final SessionCarrelloBean addCart4 = new SessionCarrelloBean();
                        addCart4.setIdemail(email);
                        addCart4.setCodiceProdotto(idProdotto);
                        try {
                            //salva in nella tabella Carrello 
                            model2.doSave(addCart4);
                        }
                        catch (SQLException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                else if (action.equals("deleteCart")) {
                    final String id = request.getParameter("id");
                    final ShopBean bean = model.doRetrieveByKey(id);
                    if (bean != null && !bean.isEmpty()) {
                        //tolgo il prodotto dal carrello
                        cart.deleteItem(bean);
                        //ottengo dalla session il numero di prodotti del carrello
                        final String cont2 = (String)session.getAttribute("conto");
                        Integer c5 = Integer.valueOf(cont2);
                        //lo decremento
                        --c5;
                        final String str2 = String.valueOf(c5);
                        ////lo riconverto in stringa e lo rimetto come attributi della session
                        session.setAttribute("conto", (Object)str2);
                        final SessionCarrelloBean addCart5 = new SessionCarrelloBean();
                        //setta email dell'account e id del prodotto rimosso dl carrello
                        addCart5.setIdemail(email);
                        addCart5.setCodiceProdotto(id);
                        try {
                            //rimuovo la riga nel db dalla tabella Carrello
                            model2.doDelete(addCart5);
                        }
                        catch (SQLException e4) {
                            this.getServletContext().getRequestDispatcher("/logout.jsp").forward((ServletRequest)request, (ServletResponse)response);
                            e4.printStackTrace();
                        }
                        request.setAttribute("message", (Object)("Product " + bean.getTitolo() + " Deleted from cart"));
                    }
                }
            }
        }
        catch (Exception e5) {
            Utility.print(e5);
            request.setAttribute("error", (Object)e5.getMessage());
        }
        request.setAttribute("cart", (Object)cart);
        try {
            request.removeAttribute("products");
            request.setAttribute("products", (Object)model.doRetrieveAll(sort));
        }
        catch (SQLException e) {
            Utility.print((Exception)e);
            request.setAttribute("error", (Object)e.getMessage());
        }
        response.setContentType("text/html");
        this.getServletContext().getRequestDispatcher("/carrello.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}