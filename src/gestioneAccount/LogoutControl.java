package gestioneAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/LogoutControl" })
public class LogoutControl extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ciao2");
        //ottiene la sessione corrente associata alla richiesta o ne crea una nuova se non esiste
        final HttpSession sessionOld = request.getSession(true);
        if (sessionOld != null) { //se la sessione esiste, la invalida
            sessionOld.invalidate();
        }
        this.getServletContext().getRequestDispatcher("/logout.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    }
}
