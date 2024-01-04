    package gestioneAcquisti;

import java.util.Date;

public class PagamentoBean {
   private int codiceAcquisto;
   private String email = "";
   private String codiceVestito = "";
   private Date data = null;
   private int importo = 0;

   public int getCodiceAcquisto() {
      return this.codiceAcquisto;
   }

   public void setCodiceAcquisto(int codiceAcquisto) {
      this.codiceAcquisto = codiceAcquisto;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getCodiceVestito() {
      return this.codiceVestito;
   }

   public void setCodiceVestito(String codiceVestito) {
      this.codiceVestito = codiceVestito;
   }

   public Date getData() {
      return this.data;
   }

   public void setData(Date data) {
      this.data = data;
   }

   public int getImporto() {
      return this.importo;
   }

   public void setImporto(int importo) {
      this.importo = importo;
   }
}