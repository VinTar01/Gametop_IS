package gestioneCarrello;

public class SessionCarrelloBean
{
    private String idemail;
    private String codiceProdotto;
    
    @Override
    public String toString() {
        return "SessionCarrelloBean [idemail=" + this.idemail + ", codiceProdotto=" + this.codiceProdotto + ", getIdemail()=" + this.getIdemail() + ", getCodiceProdotto()=" + this.getCodiceProdotto() + ", getClass()=" + this.getClass() + ", hashCode()=" + this.hashCode() + ", toString()=" + super.toString() + "]";
    }
    
    public String getIdemail() {
        return this.idemail;
    }
    
    public void setIdemail(final String idemail) {
        this.idemail = idemail;
    }
    
    public String getCodiceProdotto() {
        return this.codiceProdotto;
    }
    
    public void setCodiceProdotto(final String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }
}