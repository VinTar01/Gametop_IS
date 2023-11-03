package gestioneAcquisti;

import java.util.Date;

public class PagamentoBean
{
    private int codiceAcquisto;
    private String email;
    private String codiceProdotto;
    private Date data;
    private int importo;
    
    public PagamentoBean() {
        this.email = "";
        this.codiceProdotto = "";
        this.data = null;
        this.importo = 0;
    }
    
    public int getCodiceAcquisto() {
        return this.codiceAcquisto;
    }
    
    public void setCodiceAcquisto(final int codiceAcquisto) {
        this.codiceAcquisto = codiceAcquisto;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getCodiceProdotto() {
        return this.codiceProdotto;
    }
    
    public void setCodiceProdotto(final String codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }
    
    public Date getData() {
        return this.data;
    }
    
    public void setData(final Date data) {
        this.data = data;
    }
    
    public int getImporto() {
        return this.importo;
    }
    
    public void setImporto(final int importo) {
        this.importo = importo;
    }
}