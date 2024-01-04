package gestioneAccount;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet({"/AccountModificaDatiControl"})
public class CambiaDatiAccountControl2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        RegistrazioneModelDS model = new RegistrazioneModelDS(ds);
        LoginModelDS model1 = new LoginModelDS(ds);
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String nuovoNome = request.getParameter("nuovoNome");
        String nuovoCognome = request.getParameter("nuovoCognome");
        
        System.out.println(email);
        System.out.println(nuovoNome);
        System.out.println(nuovoCognome);

        if (email == null || nuovoNome == null || nuovoCognome == null) {
            out.print("Dati mancanti!");
            response.setStatus(400);
            return;
        }

        try {
            UtenteBean bean = model1.doRetrieveByKey(email);
            bean.setNome(nuovoNome);
            bean.setCognome(nuovoCognome);
            
            // Aggiungi altri campi se necessario
            
            model.doUpdate(bean);

            out.print("Dati personali aggiornati con successo!");
            response.setStatus(200);
        } catch (SQLException e) {
            out.print("Errore durante l'aggiornamento dei dati personali");
            response.setStatus(500);
        }
    }
}
