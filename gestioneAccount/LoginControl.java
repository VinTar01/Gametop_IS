package gestioneAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import it.unisa.utils.PasswordHasher;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

//gestione login
@WebServlet({ "/LoginControl" })
public class LoginControl extends HttpServlet
{
    private static final long serialVersionUID = 5201749135928085764L;
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final DataSource ds = (DataSource)this.getServletContext().getAttribute("DataSource");
        //creo un DAO LoginModelDS
        final LoginModelDS model = new LoginModelDS(ds);
        final PrintWriter out = response.getWriter();
        UtenteBean bean = new UtenteBean();
        try {
            //effettua una ricerca in base all'email passata alla request tramite form
            bean = model.doRetrieveByKey(request.getParameter("username"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        final String nome = bean.getNome();
        if (bean == null) {
            return;
        }
        //controlla se la passwoed passata nel form è uguale a quella del bean
        if (bean.getPassword().equals(PasswordHasher.hash(request.getParameter("password")))) {
            response.setStatus(200);
            System.out.println("sei entrato");
            //ottiene la sessione dalla request
            final HttpSession session = request.getSession();
            final String conto = "0";
            session.setAttribute("conto", (Object)conto);
            session.setMaxInactiveInterval(300);
            session.setAttribute("nome", (Object)nome);
            final String token = "ciaooo";
            session.setAttribute("token", (Object)token);
            session.setAttribute("email", (Object)bean.getEmail());
            session.setAttribute("Cliente", (Object)"1");
            response.encodeURL("index.jsp");
            return;
        }
        out.print("La password &egrave; errata!");
        response.setStatus(400);
    }
}