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

@WebServlet({ "/AccountCambiaDatiControl" })
public class CambiaDatiAccountControl extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        //crea un DAO RegistrazioneModelDS
        final RegistrazioneModelDS model = new RegistrazioneModelDS(ds);
        //crea un DAO LoginModelDS
        final LoginModelDS model2 = new LoginModelDS(ds);
        final PrintWriter out = response.getWriter();
        final HttpSession session = request.getSession();
        //ottengo l'email dalla sessione
        final String email = (String)session.getAttribute("email");
        //ottengo i parametri della request inseriti nel form di cambio dati
        final String vecchiaPassword = PasswordHasher.hash(request.getParameter("vecchiaPassword"));
        final String nuovaPassword = PasswordHasher.hash(request.getParameter("nuovaPassword"));
        final String confermaPassword = PasswordHasher.hash(request.getParameter("confermaPassword"));
        UtenteBean bean = new UtenteBean();
        if (email == null) {
            out.print("L'utente esiste!");
            response.setStatus(400);
            return;
        }
        if (request.getParameter("nuovaPassword") != null && request.getParameter("vecchiaPassword") != null && request.getParameter("confermaPassword") != null) {
            try {
                //crea un nuovo UtenteBean in base all'email ottenuta dalla sessione
                bean = model2.doRetrieveByKey(email);
                //se il bean ottenuto ha psw uguale alla vecchia psw vado avanti (è la vecchia psw ad essere memorizzata nel db e quindi nel bean)
                if (!bean.getPassword().equals(vecchiaPassword)) {
                    response.setStatus(400);
                    return;
                }
                //controllo se la nuova psw e conferma pse sono uguali
                if (!nuovaPassword.equals(confermaPassword)) {
                    response.setStatus(400);
                    return;
                }
                //se passo i controlli precedenti, aggiorno la psw del bean e lo aggiorno nel db
                bean.setPassword(nuovaPassword);
                model.doUpdate(bean);
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