package gestioneAcquisti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.sql.SQLException;
import it.unisa.utils.Utility;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/AdminOrdini" })
public class GestoreOrdiniControl extends HttpServlet
{
    //gestisce le operazioni per la gestione degli ordini da parte di un gestore ordini
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        //creo un nuovo GestioneOrdiniModelDS
        final GestioneOrdiniModelDS mO = new GestioneOrdiniModelDS(ds);
        //ottengo dalla request l'action selezionata tramite un bottone
        final String action = request.getParameter("action");
        try {
            if (action != null) {
                //
                if (action.equals("details")) {
                    //prende id e prodotto passati dal gestore per fare la ricerca
                    final String ordine = request.getParameter("id");
                    request.removeAttribute("product");
                    request.setAttribute("product", (Object)mO.doRetrieveByKey(ordine));
                }
                else if (action.equals("delete")) {
                    //prende id dell'ordine da rimuovere
                    final String id = request.getParameter("id");
                    //ricerca il bean associato all'id passato
                    final OrdineBean bean = mO.doRetrieveByKey(id);
                    if (bean != null) {
                        //cancella l'ordine
                        mO.doDelete(bean);
                        //setta un attributo message per la request che contiene il messaggio Product num ordine deleted
                        request.setAttribute("message", (Object)("Product " + bean.getNumeroOrdine() + " deleted"));
                    }
                }
                //se il gestore ordini clicca su update
                else if (action.equals("update")) {
                    //ottiene dalla requse tutti i parametri di un ordine
                    final String id = request.getParameter("id");
                    final String numeroOrdine = request.getParameter("NumeroOrdine");
                    //cerca un ordine associato all'id ottenuto
                    final OrdineBean o = mO.doRetrieveByKey(id);
                    //ottiene l'email di quell'ordine
                    final String email = o.getEmail();
                    final String nome = request.getParameter("nome");
                    final String cognome = request.getParameter("cognome");
                    final String indirizzo = request.getParameter("indirizzo");
                    final String cap = request.getParameter("cap");
                    final String comune = request.getParameter("comune");
                    final String provincia = request.getParameter("provincia");
                    final String prezzo = request.getParameter("prezzo");
                    final String prodotti = request.getParameter("prodotti");
                    final String controllato = request.getParameter("controllato");
                    //crea un nuovo bean
                    final OrdineBean bean2 = new OrdineBean();
                    //setto i parametri del nuovo bean con quelli aggiornati del vecchio mantenendo lo stesso numer ordine 
                    bean2.setNumeroOrdine(id);
                    bean2.setEmail(email);
                    bean2.setNome(nome);
                    bean2.setCognome(cognome);
                    bean2.setIndirizzo(indirizzo);
                    bean2.setCap(cap);
                    bean2.setComune(comune);
                    bean2.setProvincia(provincia);
                    bean2.setPrezzo(prezzo);
                    bean2.setProdotti(prodotti);
                    bean2.setControllato(controllato);
                    mO.doUpdate(bean2);
                    request.setAttribute("message", (Object)("Product " + bean2.getNumeroOrdine() + " updated"));
                }
                else if (action.equals("conferma")) {
                    final String id = request.getParameter("id");
                    final OrdineBean bean = mO.doRetrieveByKey(id);
                    mO.confermaOrdine(bean);
                    request.setAttribute("message", (Object)("Numero Ordine " + bean.getNumeroOrdine() + " confermato"));
                }
            }
        }
        catch (Exception e) {
            Utility.print(e);
            request.setAttribute("error", (Object)e.getMessage());
        }
        try {
            request.setAttribute("products", (Object)mO.ritornaTuttiOrdiniDaControllare());
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/adminOrdini.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}