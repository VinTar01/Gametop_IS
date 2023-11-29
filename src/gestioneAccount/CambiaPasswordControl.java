package gestioneAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.sql.SQLException;
import it.unisa.utils.PasswordHasher;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/CambiaPasswordControl" })
public class CambiaPasswordControl extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.print("Inizio Control");
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        final RegistrazioneModelDS model = new RegistrazioneModelDS(ds);
        final LoginModelDS model2 = new LoginModelDS(ds);
        final PrintWriter out = response.getWriter();
        final String email = request.getParameter("email");
        final String vecchiaPassword = PasswordHasher.hash(request.getParameter("vecchiaPassword"));
        final String nuovaPassword = PasswordHasher.hash(request.getParameter("nuovaPassword"));
        final String confermaPassword = PasswordHasher.hash(request.getParameter("confermaPassword"));
        if (email == null) {
            out.print("L'utente esiste!");
            response.setStatus(200);
            return;
        }
        if (request.getParameter("vecchiaPassword") != null && request.getParameter("nuovaPassword") != null && request.getParameter("confermaPassword") != null) {
            try {
                UtenteBean bean = new UtenteBean();
                bean = model2.doRetrieveByKey(email);
                if (!bean.getPassword().equals(vecchiaPassword)) {
                    out.print("Le password non coincidono!");
                    response.setStatus(200);
                    return;
                }
                if (nuovaPassword.equals(confermaPassword)) {
                    bean.setPassword(nuovaPassword);
                    model.doUpdatePassword(bean);
                }
            }
            catch (SQLException e) {
                response.setStatus(400);
                return;
            }
            out.print("Utente creato con successo!");
            response.setStatus(200);
        }
    }
}