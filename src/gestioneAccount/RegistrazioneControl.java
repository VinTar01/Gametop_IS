   package gestioneAccount;

import it.unisa.utils.PasswordHasher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet({"/RegistrazioneControl"})
public class RegistrazioneControl extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
      RegistrazioneModelDS model = new RegistrazioneModelDS(ds);
      PrintWriter out = response.getWriter();
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String email = request.getParameter("email");
      String indirizzo = request.getParameter("indirizzo");
      String password = PasswordHasher.hash(request.getParameter("password"));
      UtenteBean bean = new UtenteBean();
      if (nome != null && email != null) {
         if (request.getParameter("password") != null) {
            bean.setNome(nome);
            bean.setCognome(cognome);
            bean.setEmail(email);
            bean.setIndirizzo(indirizzo);
            bean.setPassword(password);

            try {
               model.doSave(bean);
               HttpSession session = request.getSession();
               String conto = "0";
               session.setAttribute("conto", conto);
               session.setAttribute("nome", nome);
               session.setAttribute("email", email);
               session.setAttribute("Cliente", "1");
               session.setMaxInactiveInterval(300);
            } catch (SQLException var14) {
               response.setStatus(400);
               return;
            }

            out.print("Utente creato con successo!");
            response.setStatus(200);
         } else {
            out.print("Le password non coincidono!");
            response.setStatus(400);
         }
      } else {
         out.print("L'utente esiste!");
         response.setStatus(400);
      }
   }
}
