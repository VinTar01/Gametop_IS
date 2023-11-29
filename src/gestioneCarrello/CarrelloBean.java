package gestioneCarrello;

public class CarrelloBean
{
    private String idemail;
    private String password;
    private String codiceProdotto;
    
    public String getIdemail() {
        return this.idemail;
    }
    
    public void setIdemail(final String idemail) {
        this.idemail = idemail;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getcodiceProdotto() {
        return this.codiceProdotto;
    }
    
    public void setcodiceProdotto(final String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.codiceProdotto == null) ? 0 : this.codiceProdotto.hashCode());
        result = 31 * result + ((this.idemail == null) ? 0 : this.idemail.hashCode());
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
        final CarrelloBean other = (CarrelloBean)obj;
        if (this.codiceProdotto == null) {
            if (other.codiceProdotto != null) {
                return false;
            }
        }
        else if (!this.codiceProdotto.equals(other.codiceProdotto)) {
            return false;
        }
        if (this.idemail == null) {
            if (other.idemail != null) {
                return false;
            }
        }
        else if (!this.idemail.equals(other.idemail)) {
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