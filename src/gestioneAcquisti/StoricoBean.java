    package gestioneAcquisti;

public class StoricoBean {
   private String idemail;
   private String codiceVestito;

   public String toString() {
      return "SessionCarrelloBean [idemail=" + this.idemail + ", codiceVestito=" + this.codiceVestito + ", getIdemail()=" + this.getIdemail() + ", getCodiceVestito()=" + this.getCodiceVestito() + ", getClass()=" + this.getClass() + ", hashCode()=" + this.hashCode() + ", toString()=" + super.toString() + "]";
   }

   public String getIdemail() {
      return this.idemail;
   }

   public void setIdemail(String idemail) {
      this.idemail = idemail;
   }

   public String getCodiceVestito() {
      return this.codiceVestito;
   }

   public void setCodiceVestito(String codiceVestito) {
      this.codiceVestito = codiceVestito;
   }
}