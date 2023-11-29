package gestioneAccount;

import java.util.Iterator;
import java.util.ArrayList;

public class UtenteBean
{
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String password;
    
    /*sovrascrittura del metodo hashCode di Object per ottenere il codice hash di un oggetto
    il codice hash serve per identificare univocamente un oggetto, implementiamo un hashCode per un UtenteBean in base alle sue proprietà
    utilizzo il valore 31 come primo numero primo (comunemente usato in questi calcoli) e combina i valori hash
    delle proprietà cognome, email, indirizzo, nome e password.
    Se una di queste proprietà è null, il suo valore hash è trattato come zero.
    Il risultato finale è la combinazione di questi valori hash.
    In generale, quando si utilizza il metodo hashCode() in Java, 
    è importante garantire che se due oggetti sono uguali secondo il metodo equals(), 
    allora devono avere lo stesso valore hash. Ciò è importante quando si utilizzano 
    le collezioni basate su hash come HashMap o HashSet, 
    poiché il comportamento di queste collezioni si basa su queste due operazioni.*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.cognome == null) ? 0 : this.cognome.hashCode());
        result = 31 * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = 31 * result + ((this.indirizzo == null) ? 0 : this.indirizzo.hashCode());
        result = 31 * result + ((this.nome == null) ? 0 : this.nome.hashCode());
        result = 31 * result + ((this.password == null) ? 0 : this.password.hashCode());
        return result;
    }
    
    /*sovrascrittura di equals di Object per confrontare due UtenteBean.
    Controlla se l'oggetto passato è uguale all'oggetto this, se lo è return true, ogni oggetto è uguale a se stesso.
    Se l'oggetto passato è null, return false perchè nessun oggetto può essere uguale a null
    Se l'oggetto passato e this hanno classi diverse return false
    Se obj è un'istanza di UtenteBean e soddisfa i passi precedenti, il metodo effettua una serie di confronti tra le proprietà dell'oggetto corrente (this) e dell'oggetto passato (obj).
     Se le proprietà coincidono, l'oggetto è considerato uguale; altrimenti, se anche una sola proprietà è diversa, l'oggetto è considerato diverso
    */

    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final UtenteBean other = (UtenteBean)obj;
        if (this.cognome == null) {
            if (other.cognome != null) {
                return false;
            }
        }
        else if (!this.cognome.equals(other.cognome)) {
            return false;
        }
        if (this.email == null) {
            if (other.email != null) {
                return false;
            }
        }
        else if (!this.email.equals(other.email)) {
            return false;
        }
        if (this.indirizzo == null) {
            if (other.indirizzo != null) {
                return false;
            }
        }
        else if (!this.indirizzo.equals(other.indirizzo)) {
            return false;
        }
        if (this.nome == null) {
            if (other.nome != null) {
                return false;
            }
        }
        else if (!this.nome.equals(other.nome)) {
            return false;
        }
        if (this.password == null) {
            if (other.password != null) {
                return false;
            }
        }
        else if (!this.password.equals(other.password)) {
            return false;
        }
        return true;
    }

    //queste override vengono fatte perchè
    // in questo modo la comparazione si fa per contenuto e non se due oggetti riferiscono allo stesso in memoria
    
    //getters and setters
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
    public String getCognome() {
        return this.cognome;
    }
    
    public void setCognome(final String cognome) {
        this.cognome = cognome;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getIndirizzo() {
        return this.indirizzo;
    }
    
    public void setIndirizzo(final String indirizzo) {
        this.indirizzo = indirizzo;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    /*metodo per cercare un UtenteBean in un ArrayList di UtenteBean
      prendiamo come parametri l'arraylist in cui fare la ricerca ed un stringa che rappresenta l'username dell'UtenteBean da cercare
      scorredo tutti gli UtenteBean della lista, se trovo un utente che ha email uguale all'username passato, 
      ottengo l'indirizzo di quell'utente 
     */
    public Object fetchadd(final ArrayList<UtenteBean> userList, final String username) {
        for (final UtenteBean u : userList) {
            if (u.getEmail().equals(username)) {
                return u.getIndirizzo();
            }
        }
        return "";
    }
    
    public Object fetchemail(final ArrayList<UtenteBean> userList, final String username) {
        for (final UtenteBean u : userList) {
            if (u.getEmail().equals(username)) {
                return u.getEmail();
            }
        }
        return "";
    }
    
    public Object fetchname(final ArrayList<UtenteBean> userList, final String username) {
        for (final UtenteBean u : userList) {
            if (u.getEmail().equals(username)) {
                return u.getNome();
            }
        }
        return "";
    }
}