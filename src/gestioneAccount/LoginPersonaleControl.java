package gestioneAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import it.unisa.utils.PasswordHasher;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/LoginPersonaleControl" })
public class LoginPersonaleControl extends HttpServlet
{
    private static final long serialVersionUID = 5201749135928085764L;
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        //creo un DAO LoginModelDS
        final LoginModelDS model = new LoginModelDS(ds);
        final PrintWriter out = response.getWriter();
        PersonaleBean bean = new PersonaleBean();
        //l'applicazione sta gestendo l'autenticazione di un nuovo utente e vuole assicurarsi che qualsiasi sessione esistente 
        //(associata a un utente precedente) venga terminata prima di autenticare il nuovo utente
        final HttpSession sessionOld = request.getSession(true);
        if (sessionOld != null) {
            sessionOld.invalidate();
        }
        try {
            //crea un PersonaleBean con gli attributi della riga dell'username passato alla request tramite form
            bean = model.doRetrieveByKeyPersonale(request.getParameter("username"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (bean.getEmail() == null) {
            out.print("Errore di credenziali.Tornare indietro e riprovare!");
        }
        if (bean != null) {
            System.out.println("PASSWORD" + bean.getPassword());
            if (bean.getPassword().equals(PasswordHasher.hash(request.getParameter("password")))) {
                response.setStatus(200);
                System.out.println("sei entrato");
                final HttpSession session = request.getSession();
                session.setMaxInactiveInterval(300);
                session.setAttribute("RUOLO", (Object)bean.getRuolo());
                System.out.println("RUOLO" + bean.getRuolo());
                //in base al ruolo, viene indirizzato alla pagina corrispondente
                if (bean.getRuolo().equals("Gestore prodotti")) {
                    this.getServletContext().getRequestDispatcher("/adminProdotti.jsp").forward((ServletRequest)request, (ServletResponse)response);
                }
                else if (bean.getRuolo().equals("Gestore ordini")) {
                    this.getServletContext().getRequestDispatcher("/adminOrdini.jsp").forward((ServletRequest)request, (ServletResponse)response);
                }
                else if (bean.getRuolo().equals("Gestore ruoli")) {
                    this.getServletContext().getRequestDispatcher("/adminRuoli.jsp").forward((ServletRequest)request, (ServletResponse)response);
                }
                else {
                    System.out.println("nessun Accesso");
                }
            }
            else {
                out.print("Errore di credenziali.Tornare indietro e riprovare!");
            }
        }
    }
}