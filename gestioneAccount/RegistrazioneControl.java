package gestioneAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;
import it.unisa.utils.PasswordHasher;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/RegistrazioneControl" })
public class RegistrazioneControl extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        //creo un DAO RegistrazioneModelDS
        final RegistrazioneModelDS model = new RegistrazioneModelDS(ds);
        final PrintWriter out = response.getWriter();
        //prendo dalla request i parametri inseriti nella form
        final String nome = request.getParameter("nome");
        final String cognome = request.getParameter("cognome");
        final String email = request.getParameter("email");
        final String indirizzo = request.getParameter("indirizzo");
        final String password = PasswordHasher.hash(request.getParameter("password"));
        final UtenteBean bean = new UtenteBean();
        if (nome == null || email == null) {
            out.print("L'utente esiste! (Utente o email non inserite)");
            response.setStatus(400);
            return;
        }
        //se la password non è null crea un UtenteBean con gli attributi passati
        if (request.getParameter("password") != null) {
            bean.setNome(nome);
            bean.setCognome(cognome);
            bean.setEmail(email);
            bean.setIndirizzo(indirizzo);
            bean.setPassword(password);
            try {
                //salvo nel db una riga con gli attributi del bean creato
                model.doSave(bean);
                final HttpSession session = request.getSession();
                final String conto = "0";
                session.setAttribute("conto", (Object)conto);
                session.setAttribute("nome", (Object)nome);
                session.setAttribute("email", (Object)email);
                session.setAttribute("Cliente", (Object)"1");
                session.setMaxInactiveInterval(300);
            }
            catch (SQLException e) {
                response.setStatus(400);
                return;
            }
            out.print("Utente creato con successo!");
            response.setStatus(200);
            return;
        }
        out.print("Le password non coincidono!");
        response.setStatus(400);
    }
}