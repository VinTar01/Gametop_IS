package gestioneAcquisti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/SpedizioneControl" })
public class SpedizioneControl extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        //ottengo la sessione attuale dalla request
        final HttpSession session = request.getSession();
        //ottengo i parametri inviati alla request tramute il form di inserimento dati spedizione
        final String nome = request.getParameter("nome");
        final String cognome = request.getParameter("cognome");
        final String indirizzo = request.getParameter("indirizzo");
        final String cap = request.getParameter("cap");
        final String comune = request.getParameter("comune");
        final String provincia = request.getParameter("provincia");
        //setto come attributi della session quelli ottenuti precedentemente
        session.setAttribute("nomeS", (Object)nome);
        session.setAttribute("cognomeS", (Object)cognome);
        session.setAttribute("indirizzoS", (Object)indirizzo);
        session.setAttribute("capS", (Object)cap);
        session.setAttribute("comuneS", (Object)comune);
        session.setAttribute("provinciaS", (Object)provincia);
        System.out.println("DATI SPEDIZIONE" + nome + "," + cognome + "," + indirizzo + "," + cap + "," + comune + "," + provincia);
        //i dati devono essere compilati
        if (nome.length() > 0 && cognome.length() > 0 && indirizzo.length() > 0 && cap.length() > 0 && comune.length() > 0 && provincia.length() > 0) {
            response.setStatus(200);
            return;
        }
        response.setStatus(400);
    }
}