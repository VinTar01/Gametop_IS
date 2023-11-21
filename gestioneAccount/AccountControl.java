package gestioneAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.sql.SQLException;
import gestioneAcquisti.GestioneOrdiniModelDS;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/AccountControl" })
public class AccountControl extends HttpServlet
{
    //l'utente è gia loggato, aggiorna la lista degli ordini della pagina utente
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        //creo un DAO GestioneOrdiniModelDS
        final GestioneOrdiniModelDS model2 = new GestioneOrdiniModelDS(ds);
        //ricava email dalla request passata tramite form
        final String email = (String)request.getSession().getAttribute("email");
        if (email != null) {
            try {
                //ottiene tutti gli ordini comprati dall'utente della email ottenuta 
                request.removeAttribute("products");
                request.setAttribute("products", (Object)model2.ritornaTuttiOrdiniUtente(email));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ci sei quasi");
        //indirizzare alla pagina jsp con la lista degli ordini aggiornata
        this.getServletContext().getRequestDispatcher("/account.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}