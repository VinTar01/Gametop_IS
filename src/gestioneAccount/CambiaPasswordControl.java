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
import javax.sql.DataSource;

@WebServlet({"/CambiaPasswordControl"})
public class CambiaPasswordControl extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.print("Inizio Control");
      DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
      RegistrazioneModelDS model = new RegistrazioneModelDS(ds);
      LoginModelDS model1 = new LoginModelDS(ds);
      PrintWriter out = response.getWriter();
      String email = request.getParameter("email");
      String vecchiaPassword = PasswordHasher.hash(request.getParameter("vecchiaPassword"));
      String nuovaPassword = PasswordHasher.hash(request.getParameter("nuovaPassword"));
      String confermaPassword = PasswordHasher.hash(request.getParameter("confermaPassword"));
      if (email == null) {
         out.print("L'utente esiste!");
         response.setStatus(200);
      } else if (request.getParameter("vecchiaPassword") != null && request.getParameter("nuovaPassword") != null && request.getParameter("confermaPassword") != null) {
         try {
            new UtenteBean();
            UtenteBean bean = model1.doRetrieveByKey(email);
            if (!bean.getPassword().equals(vecchiaPassword)) {
               out.print("Le password non coincidono!");
               response.setStatus(200);
               return;
            }

            if (nuovaPassword.equals(confermaPassword)) {
               bean.setCognome(nuovaPassword);
               model.doUpdatePassword(bean);
            }
         } catch (SQLException var12) {
            response.setStatus(400);
            return;
         }

         out.print("Utente creato con successo!");
         response.setStatus(200);
      }
   }
}
