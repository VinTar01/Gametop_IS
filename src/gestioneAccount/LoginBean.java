package gestioneAccount;

public class LoginBean
{
    private String indirizzo;
    private String password;
    
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.indirizzo == null) ? 0 : this.indirizzo.hashCode());
        result = 31 * result + ((this.password == null) ? 0 : this.password.hashCode());
        return result;
    }
    
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
        final LoginBean other = (LoginBean)obj;
        if (this.indirizzo == null) {
            if (other.indirizzo != null) {
                return false;
            }
        }
        else if (!this.indirizzo.equals(other.indirizzo)) {
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
}
